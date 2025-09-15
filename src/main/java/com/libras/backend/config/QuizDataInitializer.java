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

        // Criar todos os 5 níveis
        criarNivel1_CumprimentosBasicos();
        criarNivel2_ConversasCotidianas();
        criarNivel3_Familia();
        criarNivel4_Alimentos();
        criarNivel5_Lugares();

        log.info("✅ LibraLingo inicializado: 5 níveis com 25 perguntas total!");
    }

    private void criarNivel1_CumprimentosBasicos() {
        log.info("👋 Criando Nível 1: Cumprimentos Básicos...");

        // 1. OI
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/oi1.png,/images/nivel1/oi12.png",
                List.of("Bom dia", "Boa tarde", "Oi", "Tudo bem?"),
                2, 1
        ));

        // 2. DESCULPA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/desculpa1.png",
                List.of("Tchau", "Oi", "Obrigado", "Desculpa"),
                3, 1
        ));

        // 3. TCHAU
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel1/tchau1.png",
                List.of("Oi", "Bom noite", "Tchau", "Obrigado"),
                2, 1
        ));

        // 4. OBRIGADO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Obrigado",
                List.of("/images/nivel1/obrigado1.png", "/images/idade.png", "/images/tchau1.png", "/images/bomdia4.png"),
                0, 1
        ));

        // 5. EU
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Eu",
                List.of("/images/null/mae.png", "/images/nivel1/eu1.png", "/images/null/trem.png", "/images/null/quasepao1.png"),
                1, 1
        ));
    }

    private void criarNivel2_ConversasCotidianas() {
        log.info("🗣️ Criando Nível 2: Conversas Cotidianas...");

        // 1. BOM DIA (sequência)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/bomdia2.png,/images/nivel2/bomdia22.png,/images/nivel2/bomdia23.png,/images/nivel2/bomdia24.png",
                List.of("Bom dia", "Tudo bem?", "Prazer em conhecer", "Boa tarde"),
                0, 2
        ));


        // 2. CARRO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Carro",
                List.of("/images/nivel2/moto2.png", "/images/nivel2/onibus2.png", "/images/nivel2/trem2.png", "/images/nivel2/carro2.png"),
                3, 2
        ));

        // 3. EU VOU
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/eu2.png,/images/nivel2/ir2.png",
                List.of("Eu sou", "Estou com fome", "Eu vou", "Eu e você"),
                2, 2
        ));

        // 4. ÔNIBUS
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel2/onibus2.png,/images/nivel2/onibus22.png",
                List.of("Carro", "Moto", "Ônibus", "Trem"),
                2, 2
        ));


        // 5. Idade
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Idade",
                List.of("/images/nivel2/gemini2.png", "/images/nivel2/idade2.png", "/images/nivel2/cinema2.png", "/images/nivel2/cafe2.png"),
                1, 2
        ));
    }

    private void criarNivel3_Familia() {
        log.info("👨‍👩‍👧‍👦 Criando Nível 3: Família...");
        

        // 1. MÃE
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/mae3.png,/images/nivel3/mae32.png",  // Adicionar esta imagem
                List.of("Pai", "Mãe", "Irmão", "Avó"),
                1, 3
        ));


        // 2. Tio
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Tio",
                List.of("/images/null/obrigado.png", "/images/null/joia.png", "/images/nivel3/tio3.png", "/images/null/quaseirmao.png"),
                2, 3
        ));



        // 3. PAI
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/pai3.png,/images/nivel3/mae32.png", // Adicionar esta imagem
                List.of("Pai", "Tio", "Avô", "Primo"),
                0, 3
        ));

        // 4. Amigo
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/amigo3.png",  // Adicionar esta imagem
                List.of("Primo", "Amigo", "Irmão", "Conhecido"),
                1, 3
        ));

        // 5. Familia
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel3/familia31.png,/images/nivel3/familia32.png",
                List.of("Irmãos", "Time", "Grupo", "Familia"),
                3, 3
        ));
    }

    private void criarNivel4_Alimentos() {
        log.info("🍎 Criando Nível 4: Alimentos...");

        // 1. ÁGUA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/agua41.png,/images/nivel4/agua42.png",  // Adicionar esta imagem
                List.of("Leite", "Café", "Água", "Suco"),
                2, 4
        ));

        // 2. Restaurante
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/restaurante41.png,/images/nivel4/restaurante42.png",  // Adicionar esta imagem
                List.of("Bebida", "Restaurante", "Cozinha", "Jantar"),
                1, 4
        ));

        // 3. CAFÉ
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/cafe41.png,/images/nivel4/cafe42.png",  // Adicionar esta imagem
                List.of("Chá", "Bebida", "Leite", "Café"),
                3, 4
        ));

        // 4. Comer
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel4/comer41.png,/images/nivel4/comer42.png",  // Adicionar esta imagem
                List.of("Comer", "Fome", "Vem", "Jantar"),
                0, 4
        ));

        // 5. Arroz
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Arroz",
                List.of("/images/null/quasepao1.png", "/images/null/shoping.png", "/images/nivel4/arroz4.png", "/images/null/bomdia.png"),
                2, 4
        ));
    }

    private void criarNivel5_Lugares() {
        log.info("🏠 Criando Nível 5: Lugares...");

        // 1. Casa
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/casa5.png",  // Adicionar esta imagem
                List.of("Casa", "Escola", "Hospital", "Mercado"),
                0, 5
        ));

        // 2. HOSPITAL
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/hospital51.png,/images/nivel5/hospital52.png",  // Adicionar esta imagem
                List.of("Hospital", "Hotel", "Escola", "Banco"),
                0, 5
        ));

        // 3. Shopping
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Shopping",  // Adicionar esta imagem
                List.of("/images/null/idade.png", "/images/null/trem.png", "/images/null/moto.png", "/images/nivel5/shoping5.png"),
                3, 5
        ));

        // 2. Banheiro
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/nivel5/banheiro5.png",  // Adicionar esta imagem
                List.of("Hospital", "Hotel", "Banheiro", "Banco"),
                2, 5
        ));

        // 5. Cinema
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Cinema",
                List.of("/images/nivel5/cinema5.png", "/images/null/onibus.png", "/images/null/pai.png", "/images/quasepai2.png"),
                0, 5
        ));
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