package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.service.PerguntaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class QuizDataInitializer implements ApplicationRunner {

    private final PerguntaService perguntaService;

    public QuizDataInitializer(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @Override
    public void run(ApplicationArguments args) {
        // Exemplo de pergunta 1
        Pergunta p1 = new Pergunta();
        p1.setSinalUrl("/sinais/oQuarto.js");
        p1.setIndiceCorreto(1);
        // Usa o novo construtor de conveniência
        Opcao o1a = new Opcao("um");
        Opcao o1b = new Opcao("quatro");
        Opcao o1c = new Opcao("dez");
        p1.setOpcoes(Arrays.asList(o1a, o1b, o1c));
        perguntaService.salvar(p1);

        // Exemplo de pergunta 2
        Pergunta p2 = new Pergunta();
        p2.setSinalUrl("/sinais/ondeEstaMinhaMao.js");
        p2.setIndiceCorreto(0);
        Opcao o2a = new Opcao("mão");
        Opcao o2b = new Opcao("braço");
        Opcao o2c = new Opcao("ombro");
        p2.setOpcoes(Arrays.asList(o2a, o2b, o2c));
        perguntaService.salvar(p2);

        // Adicione quantas perguntas quiser do mesmo jeito…
    }
}
