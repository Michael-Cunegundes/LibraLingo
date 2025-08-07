package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.service.PerguntaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuizDataInitializer implements ApplicationRunner {
    private final PerguntaService perguntaService;

    public QuizDataInitializer(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!perguntaService.listarTodas().isEmpty()) return;

        // Nível 1
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/oi.png",
                List.of("Oi","Tchau","Bom dia"),
                0,
                1        // ← nivel 1
        ));

        // Nível 2
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Obrigado",
                List.of("/images/obrigado.png"),
                0,
                2        // ← nivel 2
        ));

    }


    private Pergunta cria(
            TipoPergunta tipo,
            String prompt,
            List<String> opcoes,
            int indiceCorreto,
            int level    // ← novo parâmetro
    ) {
        Pergunta p = new Pergunta();
        p.setLevel(level);          // ← definindo nível
        p.setTipo(tipo);
        p.setPrompt(prompt);
        p.setIndiceCorreto(indiceCorreto);
        p.setOpcoes(opcoes.stream()
                .map(o -> new Opcao(o))
                .collect(Collectors.toList()));
        return p;
    }
}