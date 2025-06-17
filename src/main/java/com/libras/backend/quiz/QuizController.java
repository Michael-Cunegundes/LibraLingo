package com.libras.backend.quiz;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// IMPORTAÇÕES CORRETAS PARA OS DTOs:
import com.libras.backend.quiz.dto.PerguntaDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    /**
     * GET /quiz
     * Retorna a lista de PerguntaDTO (sinalUrl + opções) para o front-end.
     */
    @GetMapping
    public ResponseEntity<List<PerguntaDTO>> getPerguntas() {
        List<PerguntaDTO> todas = quizService.listarPerguntas();
        return ResponseEntity.ok(todas);
    }

    /**
     * GET /quiz/perguntas
     * Alias para {@link #getPerguntas()} utilizado nos testes de integração.
     */
    @GetMapping("/perguntas")
    public ResponseEntity<List<PerguntaDTO>> getPerguntasAlias() {
        return getPerguntas();
    }

    /**
     * POST /quiz/submit
     * Recebe um JSON array de RespostaQuizDTO e devolve o ResultadoQuizDTO
     */
    @PostMapping("/submit")
    public ResponseEntity<ResultadoQuizDTO> submitRespostas(
            @Valid @RequestBody List<RespostaQuizDTO> respostas) {
        ResultadoQuizDTO resultado = quizService.calculaResultado(respostas);
        return ResponseEntity.ok(resultado);
    }

    /**
     * POST /quiz/respostas
     * Alias para {@link #submitRespostas(List)} utilizado nos testes.
     */
    @PostMapping("/respostas")
    public ResponseEntity<ResultadoQuizDTO> submitRespostasAlias(
            @Valid @RequestBody List<RespostaQuizDTO> respostas) {
        return submitRespostas(respostas);
    }
}