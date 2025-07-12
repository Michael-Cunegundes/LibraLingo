package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.repository.quiz.PerguntaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QuizDataInitializerTest {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private ApplicationRunner populaPerguntas;

    @Test
    void existingQuestionsAreKept() throws Exception {
        // prepara uma pergunta “manual”
        Pergunta p = new Pergunta();
        p.setTipo(TipoPergunta.IMAGEM_PARA_TEXTO);
        p.setPrompt("preexistente");
        p.setIndiceCorreto(0);
        // usa o setter para já vincular a Opcao à Pergunta
        p.setOpcoes(Collections.singletonList(new Opcao("A")));
        perguntaRepository.save(p);

        long countBefore = perguntaRepository.count();

        // executa o initializer
        populaPerguntas.run(new DefaultApplicationArguments());

        long countAfter = perguntaRepository.count();
        // deve manter exatamente a mesma quantidade
        assertThat(countAfter).isEqualTo(countBefore);
        // e manter a pergunta que já existia
        assertThat(perguntaRepository.findById(p.getId())).isPresent();
    }
}
