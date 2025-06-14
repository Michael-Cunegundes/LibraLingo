// src/main/java/com/libras/backend/config/QuizDataInitializer.java
package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.repository.quiz.PerguntaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class QuizDataInitializer {

    @Bean
    public CommandLineRunner populaPerguntas(PerguntaRepository perguntaRepository) {
        return args -> {
            // Cria as perguntas padrão
            Pergunta p1 = new Pergunta();
            p1.setSinalUrl("https://dominio.com/sinal1.jpg");
            p1.setIndiceCorreto(0);
            p1.getOpcoes().add(new Opcao("Opção A"));
            p1.getOpcoes().add(new Opcao("Opção B"));
            p1.getOpcoes().add(new Opcao("Opção C"));
            p1.getOpcoes().add(new Opcao("Opção D"));
            p1.getOpcoes().forEach(o -> o.setPergunta(p1));

            Pergunta p2 = new Pergunta();
            p2.setSinalUrl("https://dominio.com/sinal2.jpg");
            p2.setIndiceCorreto(1);
            p2.getOpcoes().add(new Opcao("Opção A"));
            p2.getOpcoes().add(new Opcao("Opção B"));
            p2.getOpcoes().add(new Opcao("Opção C"));
            p2.getOpcoes().add(new Opcao("Opção D"));
            p2.getOpcoes().forEach(o -> o.setPergunta(p2));

            // Lista de padrão
            List<Pergunta> padrao = List.of(p1, p2);

            // Para cada pergunta padrão, só salva se ainda não existir
            for (Pergunta p : padrao) {
                if (!perguntaRepository.existsBySinalUrl(p.getSinalUrl())) {
                    perguntaRepository.save(p);
                }
            }
        };
    }
}
