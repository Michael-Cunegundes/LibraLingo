package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.service.PerguntaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizDataInitializer implements ApplicationRunner {
    private final PerguntaService perguntaService;

    public QuizDataInitializer(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (!perguntaService.listarTodas().isEmpty()) return;

// nível 1
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/oi.png",
                List.of("Oi", "Tchau", "Bom dia", "Boa noite"), // 4 opções
                0,
                1
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


    private List<Opcao> mapOpcoes(TipoPergunta tipo, List<String> valores) {
        return valores.stream().map(v -> {
            Opcao o = new Opcao();
            if (tipo == TipoPergunta.TEXTO_PARA_IMAGEM) {
                o.setTexto(null);
                o.setImagemUrl(v);    // aqui o valor é URL
            } else { // IMAGEM_PARA_TEXTO
                o.setTexto(v);        // aqui o valor é o texto
                o.setImagemUrl(null);
            }
            return o;
        }).toList();
    }

    private Pergunta cria(
            TipoPergunta tipo,
            String prompt,
            List<String> opcoes,
            int indiceCorreto,
            int level
    ) {
        Pergunta p = new Pergunta();
        p.setLevel(level);
        p.setTipo(tipo);
        p.setPrompt(prompt);
        p.setIndiceCorreto(indiceCorreto);

        p.setOpcoes(mapOpcoes(tipo, opcoes)); // ← usa o mapeamento condicional
        return p;
    }

}
