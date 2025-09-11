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

        // 1. OI (duas partes)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/oipart1.png,/images/oipart2.png",
                List.of("Bom dia", "Boa tarde", "Oi", "Tchau"),
                2, 1
        ));

        // 2. DESCULPA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/desculpa.png",
                List.of("Tchau", "Oi", "Obrigado", "Desculpa"),
                3, 1
        ));

        // 3. TCHAU
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/tchau1.png",
                List.of("Oi", "Bom dia", "Tchau", "Obrigado"),
                2, 1
        ));

        // 4. OBRIGADO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Obrigado",
                List.of("/images/obrigado2.png", "/images/idade.png", "/images/tchau1.png", "/images/bomdia4.png"),
                0, 1
        ));

        // 5. EU
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Eu",
                List.of("/images/eu1.png", "/images/ir1.png", "/images/trem1.png", "/images/carro1.png"),
                0, 1
        ));
    }

    private void criarNivel2_ConversasCotidianas() {
        log.info("🗣️ Criando Nível 2: Conversas Cotidianas...");

        // 1. BOM DIA (sequência)
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/bomdia1.png,/images/bomdia2.png,/images/bomdia3.png,/images/bomdia4.png",
                List.of("Bom dia", "Tudo bem?", "Prazer em conhecer", "Boa tarde"),
                0, 2
        ));


        // 2. CARRO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Carro",
                List.of("/images/moto1.png", "/images/onibus1.png", "/images/idade.png", "/images/carro1.png"),
                3, 2
        ));

        // 3. EU VOU
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/eu1.png,/images/ir1.png",
                List.of("Eu sou", "Tenho fome", "Eu vou", "Eu e você"),
                2, 2
        ));

        // 4. ÔNIBUS
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/onibus1.png,/images/onibus2.png",
                List.of("Carro", "Moto", "Ônibus", "Trem"),
                2, 2
        ));


        // 5. CASA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Casa",
                List.of("/images/gemini1.png", "/images/casa1.png", "/images/moto1.png", "/images/joia1.png"),
                1, 2
        ));
    }

    private void criarNivel3_Familia() {
        log.info("👨‍👩‍👧‍👦 Criando Nível 3: Família...");
        

        // 1. MÃE
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/mae1.png,/images/mae22.png",  // Adicionar esta imagem
                List.of("Pai", "Mãe", "Irmão", "Avó"),
                1, 3
        ));


        // 2. Tio
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Tio",
                List.of("/images/idade.png", "/images/comer1.png", "/images/tio1.png", "/images/quaseirmao1.png"),
                2, 3
        ));



        // 3. PAI
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/quasepai3.png,/images/mae22.png", // Adicionar esta imagem
                List.of("Pai", "Tio", "Avô", "Primo"),
                0, 3
        ));

        // 4. Amigo
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/amigo.png",  // Adicionar esta imagem
                List.of("Primo", "Amigo", "Irmão", "Conhecido"),
                1, 3
        ));

        // 5. Familia
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/familia1.png,/images/familia2.png",
                List.of("Irmaos", "Time", "Grupo", "Familia"),
                3, 3
        ));
    }

    private void criarNivel4_Alimentos() {
        log.info("🍎 Criando Nível 4: Alimentos...");

        // 1. ÁGUA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/agua1.png,/images/agua2.png",  // Adicionar esta imagem
                List.of("Leite", "Café", "Água", "Suco"),
                2, 4
        ));

        // 2. Restaurante
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/quaserestaurante1.png,/images/quaserestaurante2.png",  // Adicionar esta imagem
                List.of("Bebida", "Restaurante", "Cozinha", "Jantar"),
                1, 4
        ));

        // 3. CAFÉ
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/cafe1.png,/images/cafe2.png",  // Adicionar esta imagem
                List.of("Chá", "Bebida", "Leite", "Café"),
                3, 4
        ));

        // 4. Comer
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/comer1.png,/images/comer2.png",  // Adicionar esta imagem
                List.of("Comer", "Fome", "Venha", "Jantar"),
                0, 4
        ));

        // 5. Arroz
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Arroz",
                List.of("/images/quasepao1.png", "/images/oipart1.png", "/images/arroz1.png", "/images/mae2.png"),
                2, 4
        ));
    }

    private void criarNivel5_Lugares() {
        log.info("🏠 Criando Nível 5: Lugares...");

        // 1. Casa
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/casa1.png",  // Adicionar esta imagem
                List.of("Casa", "Escola", "Hospital", "Mercado"),
                0, 5
        ));

        // 2. HOSPITAL
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/quasehospital1.png,/images/quasehospital2.png",  // Adicionar esta imagem
                List.of("Hospital", "Hotel", "Escola", "Banco"),
                0, 5
        ));

        // 3. Shopping
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Shopping",  // Adicionar esta imagem
                List.of("/images/idade.png", "/images/onibus1.png", "/images/moto1.png", "/images/shoping.png"),
                3, 5
        ));

        // 2. Banheiro
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/quasebanheiro1.png",  // Adicionar esta imagem
                List.of("Hospital", "Hotel", "Banheiro", "Banco"),
                2, 5
        ));

        // 5. Cinema
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Cinema",
                List.of("/images/cinema1.png", "/images/onibus2.png", "/images/pai1.png", "/images/quasepai2.png"),
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