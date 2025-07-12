package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping({ "/api/quiz"})
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // GET /api/quiz/perguntas  (e /java/.../perguntas)
    @GetMapping("/perguntas")
    public List<QuestaoDTO> listarPerguntas() {
        return quizService.listarPerguntas();
    }


    // POST /api/quiz/respostas  (e /java/.../respostas)
    @PostMapping("/respostas")
    public ResultadoQuizDTO enviarRespostas(
            @Valid @RequestBody List<RespostaQuizDTO> respostas
    ) {
        return quizService.calculaResultado(respostas);
    }
}
