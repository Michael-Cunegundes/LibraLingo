package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QuizIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void perguntasEndpointRetornaLista() {
        ResponseEntity<QuestaoDTO[]> response =
                restTemplate.getForEntity("/api/quiz/perguntas", QuestaoDTO[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        QuestaoDTO[] questoes = response.getBody();
        assertThat(questoes).isNotNull().isNotEmpty();

        QuestaoDTO q = questoes[0];
        assertThat(q.getTipo()).isNotNull();
        assertThat(q.getPrompt()).isNotEmpty();
        assertThat(q.getOpcoes()).isNotEmpty();
        assertThat(q.getIndiceCorreto()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void respostasEndpointCalculaPontuacao() {
        ResponseEntity<QuestaoDTO[]> perguntasResp =
                restTemplate.getForEntity("/api/quiz/perguntas", QuestaoDTO[].class);
        QuestaoDTO[] questoes = perguntasResp.getBody();
        assertThat(questoes).isNotNull();

        List<RespostaQuizDTO> respostas = new ArrayList<>();
        for (QuestaoDTO q : questoes) {
            respostas.add(new RespostaQuizDTO(q.getId(), q.getIndiceCorreto()));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<RespostaQuizDTO>> request = new HttpEntity<>(respostas, headers);

        ResponseEntity<ResultadoQuizDTO> resultadoResp =
                restTemplate.postForEntity("/api/quiz/respostas", request, ResultadoQuizDTO.class);

        assertThat(resultadoResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        ResultadoQuizDTO resultado = resultadoResp.getBody();
        assertThat(resultado).isNotNull();
        assertThat(resultado.getPontuacao()).isEqualTo(respostas.size());
        assertThat(resultado.getMensagem()).contains(String.valueOf(respostas.size()));
    }
}