// src/main/java/com/libras/backend/quiz/QuizService.java
package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.PerguntaDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.repository.quiz.PerguntaRepository;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final PerguntaRepository perguntaRepository;

    public QuizService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    /** Retorna exatamente a estrutura esperada pelos testes (incluindo indiceCorreto) */
    public List<PerguntaDTO> listarPerguntas() {
        return perguntaRepository.findAll().stream()
                .map(p -> new PerguntaDTO(
                        p.getId(),
                        p.getSinalUrl(),
                        p.getOpcoes()
                                .stream()
                                .map(o -> o.getTexto())
                                .collect(Collectors.toList()),
                        p.getIndiceCorreto()
                ))
                .collect(Collectors.toList());
    }

    /** Calcula a pontuação a partir das respostas enviadas */
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
