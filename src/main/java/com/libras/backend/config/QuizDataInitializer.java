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
        log.info("🚀 Inicializando dados do LibraLingo...");

        if (!perguntaService.listarTodas().isEmpty()) {
            log.info("✅ Dados já existem, pulando inicialização");
            return;
        }

        // Criação de todos os 5 níveis
        criarNivel1_NumerosECores();
        criarNivel2_Cumprimentos();
        criarNivel3_Familia();
        criarNivel4_Alimentos();
        criarNivel5_Lugares();

        log.info("✅ LibraLingo inicializado com sucesso! 25 perguntas em 5 níveis criadas.");
    }

    // ====================================
    // NÍVEL 1: NÚMEROS E CORES (5 perguntas)
    // ====================================
    private void criarNivel1_NumerosECores() {
        log.info("🔢 Criando Nível 1: Números e Cores...");

        // 1. UM (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/oi.png",
                List.of("Um", "Dois", "Três", "Quatro"),
                0, // "Um" é correto
                1
        ));

        // 2. DOIS (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/dois.png",
                List.of("Um", "Dois", "Três", "Cinco"),
                1, // "Dois" é correto
                1
        ));

        // 3. VERMELHO (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/vermelho.png",
                List.of("Azul", "Vermelho", "Verde", "Amarelo"),
                1, // "Vermelho" é correto
                1
        ));

        // 4. TRÊS (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Três",
                List.of("/images/nivel1/tres.png", "/images/nivel1/um.png", "/images/nivel1/dois.png", "/images/nivel1/vermelho.png"),
                0, // Primeira imagem é correta
                1
        ));

        // 5. AZUL (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Azul",
                List.of("/images/nivel1/azul.png", "/images/nivel1/vermelho.png", "/images/nivel1/um.png", "/images/nivel1/dois.png"),
                0, // Primeira imagem é correta
                1
        ));

        log.info("✅ Nível 1 concluído: 5 perguntas sobre números e cores");
    }

    // ====================================
    // NÍVEL 2: CUMPRIMENTOS (5 perguntas)
    // ====================================
    private void criarNivel2_Cumprimentos() {
        log.info("👋 Criando Nível 2: Cumprimentos...");

        // 1. OI (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/oi.png",
                List.of("Oi", "Tchau", "Obrigado", "Desculpa"),
                0, // "Oi" é correto
                2
        ));

        // 2. TCHAU (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/tchau.png",
                List.of("Oi", "Tchau", "Por favor", "Obrigado"),
                1, // "Tchau" é correto
                2
        ));

        // 3. OBRIGADO (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/obrigado.png",
                List.of("Desculpa", "Obrigado", "Oi", "Tchau"),
                1, // "Obrigado" é correto
                2
        ));

        // 4. POR FAVOR (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Por favor",
                List.of("/images/nivel2/por-favor.png", "/images/nivel2/oi.png", "/images/nivel2/tchau.png", "/images/nivel2/obrigado.png"),
                0, // Primeira imagem é correta
                2
        ));

        // 5. DESCULPA (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Desculpa",
                List.of("/images/nivel2/desculpa.png", "/images/nivel2/por-favor.png", "/images/nivel2/oi.png", "/images/nivel2/tchau.png"),
                0, // Primeira imagem é correta
                2
        ));

        log.info("✅ Nível 2 concluído: 5 perguntas sobre cumprimentos");
    }

    // ====================================
    // NÍVEL 3: FAMÍLIA (5 perguntas)
    // ====================================
    private void criarNivel3_Familia() {
        log.info("👨‍👩‍👧‍👦 Criando Nível 3: Família...");

        // 1. PAI (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/pai.png",
                List.of("Pai", "Mãe", "Filho", "Irmão"),
                0, // "Pai" é correto
                3
        ));

        // 2. MÃE (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/mae.png",
                List.of("Pai", "Mãe", "Família", "Irmão"),
                1, // "Mãe" é correto
                3
        ));

        // 3. FAMÍLIA (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/familia.png",
                List.of("Pai", "Mãe", "Família", "Filho"),
                2, // "Família" é correto
                3
        ));

        // 4. FILHO (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Filho",
                List.of("/images/nivel3/filho.png", "/images/nivel3/pai.png", "/images/nivel3/mae.png", "/images/nivel3/irmao.png"),
                0, // Primeira imagem é correta
                3
        ));

        // 5. IRMÃO (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Irmão",
                List.of("/images/nivel3/irmao.png", "/images/nivel3/filho.png", "/images/nivel3/pai.png", "/images/nivel3/familia.png"),
                0, // Primeira imagem é correta
                3
        ));

        log.info("✅ Nível 3 concluído: 5 perguntas sobre família");
    }

    // ====================================
    // NÍVEL 4: ALIMENTOS (5 perguntas)
    // ====================================
    private void criarNivel4_Alimentos() {
        log.info("🍎 Criando Nível 4: Alimentos...");

        // 1. ÁGUA (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/agua.png",
                List.of("Água", "Leite", "Café", "Comida"),
                0, // "Água" é correto
                4
        ));

        // 2. COMIDA (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/comida.png",
                List.of("Água", "Comida", "Pão", "Leite"),
                1, // "Comida" é correto
                4
        ));

        // 3. CAFÉ (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/cafe.png",
                List.of("Leite", "Água", "Café", "Pão"),
                2, // "Café" é correto
                4
        ));

        // 4. PÃO (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Pão",
                List.of("/images/nivel4/pao.png", "/images/nivel4/agua.png", "/images/nivel4/leite.png", "/images/nivel4/cafe.png"),
                0, // Primeira imagem é correta
                4
        ));

        // 5. LEITE (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Leite",
                List.of("/images/nivel4/leite.png", "/images/nivel4/pao.png", "/images/nivel4/agua.png", "/images/nivel4/comida.png"),
                0, // Primeira imagem é correta
                4
        ));

        log.info("✅ Nível 4 concluído: 5 perguntas sobre alimentos");
    }

    // ====================================
    // NÍVEL 5: LUGARES (5 perguntas)
    // ====================================
    private void criarNivel5_Lugares() {
        log.info("🏠 Criando Nível 5: Lugares...");

        // 1. CASA (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/casa.png",
                List.of("Casa", "Escola", "Hospital", "Mercado"),
                0, // "Casa" é correto
                5
        ));

        // 2. ESCOLA (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/escola.png",
                List.of("Casa", "Escola", "Trabalho", "Hospital"),
                1, // "Escola" é correto
                5
        ));

        // 3. HOSPITAL (Imagem → Texto)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/hospital.png",
                List.of("Mercado", "Trabalho", "Hospital", "Casa"),
                2, // "Hospital" é correto
                5
        ));

        // 4. TRABALHO (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Trabalho",
                List.of("/images/nivel5/trabalho.png", "/images/nivel5/casa.png", "/images/nivel5/escola.png", "/images/nivel5/mercado.png"),
                0, // Primeira imagem é correta
                5
        ));

        // 5. MERCADO (Texto → Imagem)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Mercado",
                List.of("/images/nivel5/mercado.png", "/images/nivel5/trabalho.png", "/images/nivel5/hospital.png", "/images/nivel5/escola.png"),
                0, // Primeira imagem é correta
                5
        ));

        log.info("✅ Nível 5 concluído: 5 perguntas sobre lugares");
    }

    // ====================================
    // MÉTODO AUXILIAR PARA CRIAR PERGUNTAS
    // ====================================
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