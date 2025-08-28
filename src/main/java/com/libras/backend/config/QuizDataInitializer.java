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
        log.info("🚀 Inicializando dados do LibraLingo MVP...");

        if (!perguntaService.listarTodas().isEmpty()) {
            log.info("✅ Dados já existem, pulando inicialização");
            return;
        }

        // Criar apenas os 2 primeiros níveis para o MVP
        criarNivel1_CumprimentosBasicos();
        criarNivel2_Numeros();

        log.info("✅ LibraLingo MVP inicializado: 2 níveis com 10 perguntas total!");
    }

    private void criarNivel1_CumprimentosBasicos() {
        log.info("👋 Criando Nível 1: Cumprimentos Básicos...");

        // 1. OI (duas partes) - IMAGEM → TEXTO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/oipart1.png,/images/oipart2.png", // ✅ SEM ESPAÇOS após a vírgula
                List.of("Bom dia", "Boa tarde", "Oi", "Tchau"),
                2, // "Oi" é correto (índice 2)
                1
        ));

        // 2. DESCULPA - IMAGEM → TEXTO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/desculpa.png",
                List.of("Tchau", "Oi", "Obrigado", "Desculpa"),
                3, // "Desculpa" é correto
                1
        ));

        // 3. TCHAU - IMAGEM → TEXTO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/tchau1.png",
                List.of("Oi", "Bom dia", "Tchau", "Obrigado"),
                2, // "Tchau" é correto
                1
        ));

        // 4. OBRIGADO - TEXTO → IMAGEM
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Obrigado",
                List.of("/images/obrigado.png", "/images/idade.png", "/images/tchau1.png", "/images/bomdia4.png"),
                0, // Primeira imagem é correta
                1
        ));

        // 5. EU - TEXTO → IMAGEM
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Eu",
                List.of("/images/eu1.png", "/images/ir1.png", "/images/trem1.png", "/images/carro1.png"),
                0, // Primeira imagem é correta
                1
        ));

        log.info("✅ Nível 1 concluído: 5 perguntas de cumprimentos");
    }

    private void criarNivel2_Numeros() {
        log.info("🔢 Criando Nível 2: Números em LIBRAS...");

        // 1. UM - IMAGEM → TEXTO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/um.png",
                List.of("Zero", "Um", "Dois", "Três"),
                1, // "Um" é correto
                2
        ));

        // 2. DOIS - IMAGEM → TEXTO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/dois.png",
                List.of("Um", "Dois", "Três", "Quatro"),
                1, // "Dois" é correto
                2
        ));

        // 3. TRÊS - IMAGEM → TEXTO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/tres.png",
                List.of("Dois", "Três", "Quatro", "Cinco"),
                1, // "Três" é correto
                2
        ));

        // 4. QUATRO - TEXTO → IMAGEM
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Quatro",
                List.of("/images/nivel2/quatro.png", "/images/nivel2/tres.png", "/images/nivel2/cinco.png", "/images/nivel2/um.png"),
                0, // Primeira imagem é correta
                2
        ));

        // 5. CINCO - TEXTO → IMAGEM
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Cinco",
                List.of("/images/nivel2/cinco.png", "/images/nivel2/quatro.png", "/images/nivel2/dois.png", "/images/nivel2/tres.png"),
                0, // Primeira imagem é correta
                2
        ));

        log.info("✅ Nível 2 concluído: 5 perguntas sobre números");
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

        List<Opcao> opcoesMapeadas = opcoes.stream().map(valor -> {
            Opcao opcao = new Opcao();
            if (tipo == TipoPergunta.TEXTO_PARA_IMAGEM) {
                opcao.setTexto(null);
                opcao.setImagemUrl(valor);
            } else {
                opcao.setTexto(valor);
                opcao.setImagemUrl(null);
            }
            return opcao;
        }).toList();

        pergunta.setOpcoes(opcoesMapeadas);
        return pergunta;
    }
}