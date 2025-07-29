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

        // NÍVEL 1 (IMAGEM→TEXTO)
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/oi.png",
                List.of("Oi", "Tchau", "Bom dia"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/tchau.png",
                List.of("Tchau", "Oi", "Boa noite"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/bomdia1.png",
                List.of("Bom dia", "Boa noite", "Obrigado"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/bomdia2.png",
                List.of("Bom dia", "Tchau", "Oi"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/obrigado1.png",
                List.of("Obrigado", "Desculpa", "Por favor"), 0));

        // NÍVEL 2 (TEXTO→IMAGEM)
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Tchau",
                List.of("/images/tchau.png", "/images/oi.png", "/images/bomdia1.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Boa noite",
                List.of("/images/boanoite1.png", "/images/boanoite2.png", "/images/bomdia2.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Obrigado",
                List.of("/images/obrigado1.png", "/images/obrigado2.png", "/images/obrigado3.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Por favor",
                List.of("/images/porfavor1.png", "/images/porfavor2.png", "/images/porfavor3.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Desculpa",
                List.of("/images/desculpa1.png", "/images/desculpa2.png", "/images/desculpa3.png"), 0));

        // NÍVEL 3 (IMAGEM→TEXTO)
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/prazer.png",
                List.of("Prazer", "Oi", "Bom dia"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/onibus.png",
                List.of("Ônibus", "Carro", "Trem"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/agua.png",
                List.of("Água", "Comida", "Fome"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/cafe.png",
                List.of("Café", "Chá", "Leite"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/trem.png",
                List.of("Trem", "Carro", "Ônibus"), 0));

        // NÍVEL 4 (TEXTO→IMAGEM)
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Prazer",
                List.of("/images/prazer.png", "/images/agua.png", "/images/cafe.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Ônibus",
                List.of("/images/onibus.png", "/images/trem.png", "/images/carro.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Água",
                List.of("/images/agua.png", "/images/comida.png", "/images/fome.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Café",
                List.of("/images/cafe.png", "/images/cha.png", "/images/leite.png"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Comida",
                List.of("/images/comida.png", "/images/agua.png", "/images/fome.png"), 0));

        // NÍVEL 5 (IMAGEM→TEXTO)
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/fome.png",
                List.of("Fome", "Sede", "Sono"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/sono.png",
                List.of("Sono", "Fome", "Sede"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/cha.png",
                List.of("Chá", "Café", "Leite"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/leite.png",
                List.of("Leite", "Água", "Café"), 0));
        perguntaService.salvar(cria(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/desculpa1.png",
                List.of("Desculpa", "Obrigado", "Por favor"), 0));
    }


    private Pergunta cria(
            TipoPergunta tipo,
            String prompt,
            List<String> opcoes,
            int indiceCorreto
    ) {
        Pergunta p = new Pergunta();
        p.setTipo(tipo);
        p.setPrompt(prompt);
        p.setIndiceCorreto(indiceCorreto);
        p.setOpcoes(opcoes.stream()
                .map(Opcao::new)
                .toList()
        );
        return p;
    }
}