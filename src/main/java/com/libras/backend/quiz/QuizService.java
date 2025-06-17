package com.libras.backend.quiz;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.quiz.dto.PerguntaDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.repository.quiz.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    private PerguntaRepository perguntaRepository;

    /**
     * Retorna a lista de PerguntaDTO para o front-end,
     * mapeando Pergunta (e suas Opcoes) para o DTO.
     */
    public List<PerguntaDTO> listarPerguntas() {
        List<Pergunta> todasEntidades = perguntaRepository.findAll();

        return todasEntidades.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private PerguntaDTO converterParaDTO(Pergunta p) {
        // Extrai somente o texto de cada Opcao
        List<String> opcoesTexto = p.getOpcoes()
                .stream()
                .map(o -> o.getTexto())
                .collect(Collectors.toList());
        return new PerguntaDTO(
                p.getId(),
                p.getSinalUrl(),
                opcoesTexto,
                p.getIndiceCorreto()
        );
    }

    /**
     * Recebe lista de RespostaQuizDTO (perguntaId + opcaoEscolhida)
     * e devolve um ResultadoQuizDTO calculado.
     */
    public ResultadoQuizDTO calculaResultado(List<RespostaQuizDTO> respostas) {
        int acertos = 0;
        for (RespostaQuizDTO r : respostas) {
            Long perguntaId = r.getPerguntaId();
            Integer opcaoEscolhida = r.getOpcaoEscolhida();

            // Busca a Pergunta no banco
            Pergunta p = perguntaRepository.findById(perguntaId).orElse(null);
            if (p != null && opcaoEscolhida != null
                    && opcaoEscolhida.equals(p.getIndiceCorreto())) {
                acertos++;
            }
        }
        String mensagem = String.format("Voce acertou %d de %d!", acertos, respostas.size());
        return new ResultadoQuizDTO(acertos, mensagem);
    }
}
