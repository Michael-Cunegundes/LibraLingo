package com.libras.backend.config;

import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.service.PerguntaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuizDataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(QuizDataInitializer.class);
    private final PerguntaService perguntaService;

    public QuizDataInitializer(PerguntaService perguntaService) {
        this.perguntaService = perguntaService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("🚀 Inicializando dados do quiz...");

        if (!perguntaService.listarTodas().isEmpty()) {
            log.info("✅ Dados já existem, pulando inicialização");
            return;
        }

        criarPerguntasNivel1();
        log.info("✅ Dados do quiz inicializados com sucesso!");
    }

    private void criarPerguntasNivel1() {
        log.info("📚 Criando perguntas do nível 1...");

        // Pergunta 1: OI (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/oi.png",
                List.of("Oi", "Tchau", "Obrigado", "Por favor"),
                0,  // "Oi" é a opção correta (índice 0)
                1   // Nível 1
        ));

        // Pergunta 2: OBRIGADO (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/obrigado.png",
                List.of("Tchau", "Obrigado", "Desculpa", "Oi"),
                1,  // "Obrigado" é a opção correta (índice 1)
                1
        ));

        // Pergunta 3: TCHAU (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/tchau.png",
                List.of("Oi", "Obrigado", "Tchau", "Bom dia"),
                2,  // "Tchau" é a opção correta (índice 2)
                1
        ));

        // Pergunta 4: Obrigado (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Obrigado",
                List.of("/images/obrigado.png", "/images/onibus.png", "/images/tchau.png", "/images/sol.png"),
                0,  // "/images/por-favor.png" é a opção correta (índice 1)
                1
        ));

        // Pergunta 5: BOM DIA (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/bomdia.png",
                List.of("Boa tarde", "Boa noite", "Bom dia", "Oi"),
                2,  // "Bom dia" é a opção correta (índice 2)
                1
        ));

        log.info("✅ 5 perguntas do nível 1 criadas");
    }

    private Pergunta criarPergunta(
            TipoPergunta tipo,
            String prompt,
            List<String> opcoes,
            int indiceCorreto,
            int level
    ) {
        Pergunta pergunta = new Pergunta();
        pergunta.setLevel(level);
        pergunta.setTipo(tipo);
        pergunta.setPrompt(prompt);
        pergunta.setIndiceCorreto(indiceCorreto);

        // Mapeia as opções baseado no tipo da pergunta
        List<Opcao> opcoesMapeadas = opcoes.stream().map(valor -> {
            Opcao opcao = new Opcao();
            if (tipo == TipoPergunta.TEXTO_PARA_IMAGEM) {
                // Para TEXTO→IMAGEM, o valor é uma URL de imagem
                opcao.setTexto(null);
                opcao.setImagemUrl(valor);
            } else {
                // Para IMAGEM→TEXTO, o valor é texto
                opcao.setTexto(valor);
                opcao.setImagemUrl(null);
            }
            return opcao;
        }).toList();

        pergunta.setOpcoes(opcoesMapeadas);
        return pergunta;
    }
}