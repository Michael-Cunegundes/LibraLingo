package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.repository.quiz.PerguntaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuizDataInitializerTest {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private CommandLineRunner populaPerguntas;

    @Test
    void existingQuestionsAreKept() throws Exception {
        // adiciona uma pergunta manualmente
        Pergunta p = new Pergunta();
        p.setSinalUrl("preexistente");
        p.setIndiceCorreto(0);
        p.getOpcoes().add(new Opcao("A"));
        p.getOpcoes().forEach(o -> o.setPergunta(p));
        perguntaRepository.save(p);

        long countBefore = perguntaRepository.count();

        // Executa novamente o inicializador
        populaPerguntas.run(new String[]{});

        long countAfter = perguntaRepository.count();
        assertThat(countAfter).isEqualTo(countBefore);
        assertThat(perguntaRepository.findById(p.getId())).isPresent();
    }
}
