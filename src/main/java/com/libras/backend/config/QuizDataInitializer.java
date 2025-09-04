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
                "/images/mae1.png,/images/mae2.png",  // Adicionar esta imagem
                List.of("Pai", "Mãe", "Irmão", "Avó"),
                1, 3
        ));



        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Família",
                List.of("/images/familia1.png", "/images/casa1.png", "/images/carro1.png", "/images/eu1.png"),
                0, 3
        ));



        // 2. PAI
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/pai.png",  // Adicionar esta imagem
                List.of("Pai", "Tio", "Avô", "Primo"),
                0, 3
        ));

        // 3. IRMÃO/IRMÃ
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/irmao.png",  // Adicionar esta imagem
                List.of("Primo", "Amigo", "Irmão", "Sobrinho"),
                2, 3
        ));

        // 4. FAMÍLIA (conceito)

        // 5. FILHO/FILHA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Filho",
                List.of("/images/irmao.png", "/images/filho.png", "/images/pai.png", "/images/mae.png"),
                1, 3
        ));
    }

    private void criarNivel4_Alimentos() {
        log.info("🍎 Criando Nível 4: Alimentos...");

        // 1. ÁGUA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/agua.png",  // Adicionar esta imagem
                List.of("Leite", "Café", "Água", "Suco"),
                2, 4
        ));

        // 2. COMIDA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/comida.png",  // Adicionar esta imagem
                List.of("Bebida", "Comida", "Lanche", "Doce"),
                1, 4
        ));

        // 3. CAFÉ
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/cafe.png",  // Adicionar esta imagem
                List.of("Chá", "Água", "Leite", "Café"),
                3, 4
        ));

        // 4. PÃO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Pão",
                List.of("/images/bolo.png", "/images/pao.png", "/images/cafe.png", "/images/agua.png"),
                1, 4
        ));

        // 5. FRUTA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Fruta",
                List.of("/images/fruta.png", "/images/comida.png", "/images/agua.png", "/images/pao.png"),
                0, 4
        ));
    }

    private void criarNivel5_Lugares() {
        log.info("🏠 Criando Nível 5: Lugares...");

        // 1. ESCOLA
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/escola.png",  // Adicionar esta imagem
                List.of("Casa", "Escola", "Hospital", "Mercado"),
                1, 5
        ));

        // 2. HOSPITAL
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/hospital.png",  // Adicionar esta imagem
                List.of("Hospital", "Hotel", "Escola", "Banco"),
                0, 5
        ));

        // 3. MERCADO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/mercado.png",  // Adicionar esta imagem
                List.of("Farmácia", "Padaria", "Mercado", "Restaurante"),
                2, 5
        ));

        // 4. BANCO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Banco",
                List.of("/images/banco.png", "/images/escola.png", "/images/hospital.png", "/images/casa1.png"),
                0, 5
        ));

        // 5. TRABALHO
        perguntaService.salvar(criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Trabalho",
                List.of("/images/casa1.png", "/images/trabalho.png", "/images/escola.png", "/images/mercado.png"),
                1, 5
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