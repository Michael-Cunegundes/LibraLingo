// Substitua completamente o conteúdo do seu QuizService.java
package com.libras.backend.quiz;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.quiz.dto.OptionDTO;

import java.util.ArrayList;
import java.util.stream.Collectors;
import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.repository.quiz.PerguntaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {

    private static final Logger log = LoggerFactory.getLogger(QuizService.class);
    private final PerguntaRepository perguntaRepository;

    public QuizService(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public List<QuestaoDTO> listarPerguntas() {
        List<Pergunta> todas = perguntaRepository.findAll();
        return todas.stream().map(this::toDTO).toList();
    }

    private QuestaoDTO toDTO(Pergunta p) {
        List<String> promptList = new ArrayList<>();

        // Verificar se há múltiplas imagens
        if (p.getPrompt() != null && p.getPrompt().contains(",")) {
            // Dividir manualmente
            String[] partes = p.getPrompt().split(",");
            for (String parte : partes) {
                String parteLimpa = parte.trim();
                if (!parteLimpa.isEmpty()) {
                    promptList.add(parteLimpa);
                }
            }
        } else {
            promptList.add(p.getPrompt());
        }

        List<OptionDTO> ops = p.getOpcoes().stream()
                .map(o -> {
                    if (p.getTipo() == TipoPergunta.IMAGEM_PARA_TEXTO) {
                        return new OptionDTO(o.getTexto(), null);
                    } else {
                        return new OptionDTO(null, o.getImagemUrl());
                    }
                })
                .toList();

        return new QuestaoDTO(p.getId(), p.getTipo(), promptList, ops, p.getIndiceCorreto());
    }

    public ResultadoQuizDTO calculaResultado(List<RespostaQuizDTO> respostas) {
        int acertos = 0;
        int total = respostas.size();

        for (RespostaQuizDTO resposta : respostas) {
            Pergunta pergunta = perguntaRepository.findById(resposta.getPerguntaId()).orElse(null);
            if (pergunta != null && resposta.getOpcaoEscolhida().equals(pergunta.getIndiceCorreto())) {
                acertos++;
            }
        }

        String mensagem;
        double percentual = (acertos * 100.0) / total;

        if (percentual >= 80) {
            mensagem = String.format("Excelente! Você acertou %d de %d perguntas (%.0f%%). Continue assim!",
                    acertos, total, percentual);
        } else if (percentual >= 60) {
            mensagem = String.format("Bom trabalho! Você acertou %d de %d perguntas (%.0f%%). Pratique mais um pouco!",
                    acertos, total, percentual);
        } else {
            mensagem = String.format("Você acertou %d de %d perguntas (%.0f%%). Que tal revisar o conteúdo?",
                    acertos, total, percentual);
        }

        return new ResultadoQuizDTO(acertos, mensagem);
    }
}