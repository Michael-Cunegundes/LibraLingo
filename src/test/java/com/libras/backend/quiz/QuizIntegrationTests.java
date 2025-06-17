package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.PerguntaDTO;
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
        ResponseEntity<PerguntaDTO[]> response = restTemplate.getForEntity("/quiz/perguntas", PerguntaDTO[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        PerguntaDTO[] body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.length).isGreaterThan(0);
    }

    @Test
    void respostasEndpointCalculaPontuacao() {
        ResponseEntity<PerguntaDTO[]> perguntasResp = restTemplate.getForEntity("/quiz/perguntas", PerguntaDTO[].class);
        PerguntaDTO[] perguntas = perguntasResp.getBody();
        assertThat(perguntas).isNotNull();

        List<RespostaQuizDTO> respostas = new ArrayList<>();
        for (PerguntaDTO p : perguntas) {
            respostas.add(new RespostaQuizDTO(p.getId(), p.getIndiceCorreto()));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<RespostaQuizDTO>> request = new HttpEntity<>(respostas, headers);

        ResponseEntity<ResultadoQuizDTO> resultadoResp = restTemplate.postForEntity("/quiz/respostas", request, ResultadoQuizDTO.class);
        assertThat(resultadoResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        ResultadoQuizDTO resultado = resultadoResp.getBody();
        assertThat(resultado).isNotNull();
        assertThat(resultado.getPontuacao()).isEqualTo(respostas.size());
    }
}
