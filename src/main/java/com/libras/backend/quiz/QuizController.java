package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.service.PerguntaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;
    private final PerguntaService perguntaService;

    public QuizController(QuizService quizService, PerguntaService perguntaService) {
        this.quizService = quizService;
        this.perguntaService = perguntaService;
    }

    @GetMapping("/levels/{level}/questions")
    public List<QuestaoDTO> porNivel(@PathVariable Integer level) {
        return perguntaService.listarPorNivel(level);
    }

    @GetMapping("/perguntas")
    public List<QuestaoDTO> listarPerguntas() {
        return quizService.listarPerguntas();
    }

    @PostMapping("/respostas")
    public ResultadoQuizDTO enviarRespostas(@Valid @RequestBody List<RespostaQuizDTO> respostas) {
        return quizService.calculaResultado(respostas);
    }
}
