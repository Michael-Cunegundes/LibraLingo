package com.libras.backend.quiz;

import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.quiz.dto.*;
import com.libras.backend.repository.quiz.PerguntaRepository;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizService {

    private final PerguntaRepository perguntaRepository;

    public QuizService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public List<QuestaoDTO> listarPerguntas() {
        return perguntaRepository.findAll().stream()
                .map(p -> {
                    List<OptionDTO> ops = p.getOpcoes().stream()
                            .map(o -> {
                                if (p.getTipo() == TipoPergunta.IMAGEM_PARA_TEXTO) {
                                    return new OptionDTO(o.getTexto(), null);
                                } else {
                                    return new OptionDTO(null, o.getTexto());
                                }
                            })
                            .toList();

                    return new QuestaoDTO(
                            p.getId(),
                            p.getTipo(),
                            p.getPrompt(),
                            ops,
                            p.getIndiceCorreto()
                    );
                })
                .toList();
    }

    public ResultadoQuizDTO calculaResultado(List<RespostaQuizDTO> respostas) {
        int acertos = 0;
        for (RespostaQuizDTO r : respostas) {
            // r.getOpcaoEscolhida() agora sempre virá não nulo
            Pergunta p = perguntaRepository.findById(r.getPerguntaId()).orElse(null);
            if (p != null && r.getOpcaoEscolhida().equals(p.getIndiceCorreto())) {
                acertos++;
            }
        }
        String mensagem = String.format("Você acertou %d de %d!", acertos, respostas.size());
        return new ResultadoQuizDTO(acertos, mensagem);
    }
}
