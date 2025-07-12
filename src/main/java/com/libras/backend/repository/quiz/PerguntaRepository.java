package com.libras.backend.repository.quiz;

import com.libras.backend.model.quiz.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    boolean existsByPrompt(String prompt);
}