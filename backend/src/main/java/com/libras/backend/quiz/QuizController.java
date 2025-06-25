package com.libras.backend.backend.quiz;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;  // veja que importamos CrossOrigin
import java.util.List;
import com.libras.backend.backend.quiz.dto.PerguntaDTO;
import com.libras.backend.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.backend.quiz.dto.ResultadoQuizDTO;

@CrossOrigin(origins = "http://localhost:4300")  // ◀️ libera chamadas do Angular dev server
@RestController
@RequestMapping("/java/com/libras/backend/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<List<PerguntaDTO>> getPerguntas() {
        return ResponseEntity.ok(quizService.listarPerguntas());
    }

    @GetMapping("/perguntas")
    public ResponseEntity<List<PerguntaDTO>> getPerguntasAlias() {
        return getPerguntas();
    }

    @PostMapping("/submit")
    public ResponseEntity<ResultadoQuizDTO> submitRespostas(
            @Valid @RequestBody List<RespostaQuizDTO> respostas
    ) {
        return ResponseEntity.ok(quizService.calculaResultado(respostas));
    }

    @PostMapping("/respostas")
    public ResponseEntity<ResultadoQuizDTO> submitRespostasAlias(
            @Valid @RequestBody List<RespostaQuizDTO> respostas
    ) {
        return submitRespostas(respostas);
    }
}
