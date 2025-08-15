package com.libras.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendLibrasApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendLibrasApplication.class, args);
    }

    // ✅ REMOVIDO o @Bean dataLoader antigo que estava causando erro
    // A inicialização agora é feita pelo QuizDataInitializer.java
}