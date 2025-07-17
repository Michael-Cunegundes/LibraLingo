package com.libras.backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.repository.quiz.PerguntaRepository;

import java.util.List;

@SpringBootApplication
public class BackendLibrasApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLibrasApplication.class, args);
    }

    @Bean
    CommandLineRunner dataLoader(PerguntaRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                // 1ª parte do “bom dia”
                Pergunta p1 = new Pergunta();
                p1.setTipo(TipoPergunta.IMAGEM_PARA_TEXTO);
                p1.setPrompt("/images/bomdiapart1.png");
                p1.setIndiceCorreto(2);
                p1.setOpcoes(List.of(
                        new Opcao("Oi"),
                        new Opcao("Tchau"),
                        new Opcao("Bom dia"),
                        new Opcao("Boa noite")
                ));
                repo.save(p1);

                // 2ª parte do “bom dia”
                Pergunta p2 = new Pergunta();
                p2.setTipo(TipoPergunta.IMAGEM_PARA_TEXTO);
                p2.setPrompt("/images/bomdiapart2.png");
                p2.setIndiceCorreto(0);
                p2.setOpcoes(List.of(
                        new Opcao("Bom dia"),
                        new Opcao("Oi"),
                        new Opcao("Obrigado"),
                        new Opcao("Tchau")
                ));
                repo.save(p2);
            }
        };
    }
}
