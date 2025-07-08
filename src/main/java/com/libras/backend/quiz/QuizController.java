package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.PerguntaDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4300")
@RestController
@RequestMapping("/java/com/libras/backend/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping            // GET /quiz
    public List<PerguntaDTO> getPerguntas() {
        return quizService.listarPerguntas();
    }

    @GetMapping("/perguntas")
    public ResponseEntity<List<PerguntaDTO>> listarPerguntas() {
        return ResponseEntity.ok(quizService.listarPerguntas());
    }

    @PostMapping("/submit")
    public ResultadoQuizDTO submitRespostas(
            @Valid @RequestBody List<RespostaQuizDTO> respostas
    ) {
        return quizService.calculaResultado(respostas);
    }

    @PostMapping("/respostas")
    public ResultadoQuizDTO submitRespostasAlias(
            @Valid @RequestBody List<RespostaQuizDTO> respostas
    ) {
        return quizService.calculaResultado(respostas);
    }
}
