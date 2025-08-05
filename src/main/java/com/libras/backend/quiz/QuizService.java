package com.libras.backend.quiz;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.quiz.dto.OptionDTO;
import com.libras.backend.quiz.dto.PerguntaDTO;
import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.repository.quiz.PerguntaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    private final PerguntaRepository perguntaRepository;

    public QuizService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public List<QuestaoDTO> listarPerguntas() {
        List<Pergunta> todas = perguntaRepository.findAll();

        // Filtra as duas partes de “bom dia”
        List<Pergunta> partesBomDia = todas.stream()
                .filter(p -> p.getTipo() == TipoPergunta.IMAGEM_PARA_TEXTO
                        && p.getPrompt().contains("bomdiapart"))
                .toList();

        if (partesBomDia.size() >= 2) {
            Pergunta p0 = partesBomDia.get(0);
            Pergunta p1 = partesBomDia.get(1);

            // Opções (texto) de uma delas
            List<OptionDTO> ops = p0.getOpcoes().stream()
                    .map(o -> new OptionDTO(o.getTexto(), null))
                    .toList();

            // Questão única com ambos os prompts
            QuestaoDTO bomDia = new QuestaoDTO(
                    p0.getId(),
                    TipoPergunta.IMAGEM_PARA_TEXTO,
                    List.of(p0.getPrompt(), p1.getPrompt()),
                    ops,
                    p0.getIndiceCorreto()
            );

            // Mapeia o resto normalmente
            List<QuestaoDTO> resto = todas.stream()
                    .filter(p -> !partesBomDia.contains(p))
                    .map(this::toDTO)
                    .toList();

            // Junta tudo
            List<QuestaoDTO> resultado = new ArrayList<>();
            resultado.add(bomDia);
            resultado.addAll(resto);
            return resultado;
        }

        // Se não tiver as duas partes, devolve tudo mapeado normalmente
        return todas.stream()
                .map(this::toDTO)
                .toList();
    }

    private QuestaoDTO toDTO(Pergunta p) {
        List<OptionDTO> ops = p.getOpcoes().stream()
                .map(o -> p.getTipo() == TipoPergunta.IMAGEM_PARA_TEXTO
                        ? new OptionDTO(o.getTexto(), null)
                        : new OptionDTO(null, o.getTexto()))
                .toList();

        return new QuestaoDTO(
                p.getId(),
                p.getTipo(),
                List.of(p.getPrompt()),      // questões simples continuam com lista de 1
                ops,
                p.getIndiceCorreto()
        );
    }

    // ←—— este método estava faltando
    public ResultadoQuizDTO calculaResultado(List<RespostaQuizDTO> respostas) {
        int acertos = 0;
        for (RespostaQuizDTO r : respostas) {
            Pergunta p = perguntaRepository.findById(r.getPerguntaId()).orElse(null);
            if (p != null && r.getOpcaoEscolhida().equals(p.getIndiceCorreto())) {
                acertos++;
            }
        }
        String mensagem = String.format("Você acertou %d de %d!", acertos, respostas.size());
        return new ResultadoQuizDTO(acertos, mensagem);
    }
}
