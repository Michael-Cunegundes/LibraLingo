package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.service.PerguntaService;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class QuizDataInitializer implements ApplicationRunner {

    private final PerguntaService perguntaService;

    public QuizDataInitializer(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @Override
    public void run(ApplicationArguments args) {

        if (!perguntaService.listarTodas().isEmpty()) {
            return;
        }

// IMAGEM → TEXTO
        Pergunta p1 = new Pergunta();
        p1.setTipo(TipoPergunta.IMAGEM_PARA_TEXTO);
        p1.setPrompt("/images/oi.png");
        p1.setIndiceCorreto(0);
        p1.setOpcoes(List.of(
                new Opcao("Oi"),
                new Opcao("Tchau"),
                new Opcao("Bom dia")
        ));
        perguntaService.salvar(p1);

        // TEXTO → IMAGEM
        Pergunta p2 = new Pergunta();
        p2.setTipo(TipoPergunta.TEXTO_PARA_IMAGEM);
        p2.setPrompt("Obrigado");
        p2.setIndiceCorreto(2);
        p2.setOpcoes(List.of(
                new Opcao("/images/obrigado1.png"),
                new Opcao("/images/obrigado2.png"),
                new Opcao("/images/obrigado3.png")
        ));
        perguntaService.salvar(p2);

        // Adicione quantas perguntas quiser do mesmo jeito…
    }
}