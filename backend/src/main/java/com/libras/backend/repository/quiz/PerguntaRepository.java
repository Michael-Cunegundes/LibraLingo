// src/main/java/com/libras/backend/repository/quiz/PerguntaRepository.java
package com.libras.backend.backend.repository.quiz;

import com.libras.backend.backend.model.quiz.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
    // Método para verificar existência por URL de sinal
    boolean existsBySinalUrl(String sinalUrl);
}
