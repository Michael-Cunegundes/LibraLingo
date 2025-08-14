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
        log.info("=== INICIANDO SEED DE DADOS LIBRALINGO ===");

        List<Pergunta> existentes = perguntaService.listarTodas();
        if (!existentes.isEmpty()) {
            log.info("Base já possui {} perguntas. Pulando seed.", existentes.size());
            return;
        }

        log.info("Criando dados para Nível 1 - Cumprimentos...");

        try {
            criarNivel1();
            log.info("=== SEED NÍVEL 1 CONCLUÍDO COM SUCESSO ===");
            log.info("Total de perguntas criadas: {}", perguntaService.listarTodas().size());

        } catch (Exception e) {
            log.error("ERRO durante seed de dados: {}", e.getMessage(), e);
            throw new RuntimeException("Falha no seed de dados", e);
        }
    }

    private void criarNivel1() {
        log.info("📝 Criando questões do Nível 1...");

        // Questão 1: IMAGEM→TEXTO (Oi)
        Pergunta q1 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/oi.png",
                List.of("Oi", "Tchau", "Bom dia", "Boa noite"),
                0, // "Oi" está no índice 0
                1
        );
        perguntaService.salvar(q1);
        log.info("✅ Questão 1 criada: Oi (IMAGEM→TEXTO)");

        // Questão 2: IMAGEM→TEXTO (Bom dia)
        Pergunta q2 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/bomdia.png",
                List.of("Boa noite", "Bom dia", "Obrigado", "Tchau"),
                1, // "Bom dia" está no índice 1
                1
        );
        perguntaService.salvar(q2);
        log.info("✅ Questão 2 criada: Bom dia (IMAGEM→TEXTO)");

        // Questão 3: IMAGEM→TEXTO (Boa noite)
        Pergunta q3 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/boanoite.png",
                List.of("Bom dia", "Oi", "Boa noite", "Obrigado"),
                2, // "Boa noite" está no índice 2
                1
        );
        perguntaService.salvar(q3);
        log.info("✅ Questão 3 criada: Boa noite (IMAGEM→TEXTO)");

        // Questão 4: IMAGEM→TEXTO (Obrigado)
        Pergunta q4 = criarPergunta(
                TipoPergunta.IMAGEM_PARA_TEXTO,
                "/images/obrigado.png",
                List.of("Tchau", "Oi", "Bom dia", "Obrigado"),
                3, // "Obrigado" está no índice 3
                1
        );
        perguntaService.salvar(q4);
        log.info("✅ Questão 4 criada: Obrigado (IMAGEM→TEXTO)");

        // Questão 5: TEXTO→IMAGEM (Tchau)
        Pergunta q5 = criarPergunta(
                TipoPergunta.TEXTO_PARA_IMAGEM,
                "Tchau",
                List.of("/images/tchau.png", "/images/oi.png", "/images/bomdia.png", "/images/obrigado.png"),
                0, // "/images/tchau.png" está no índice 0
                1
        );
        perguntaService.salvar(q5);
        log.info("✅ Questão 5 criada: Tchau (TEXTO→IMAGEM)");
    }

    private Pergunta criarPergunta(TipoPergunta tipo, String prompt, List<String> opcoes,
                                   int indiceCorreto, int level) {

        log.debug("Criando pergunta - Nível: {}, Tipo: {}, Prompt: {}", level, tipo, prompt);

        Pergunta p = new Pergunta();
        p.setLevel(level);
        p.setTipo(tipo);
        p.setPrompt(prompt);
        p.setIndiceCorreto(indiceCorreto);
        p.setOpcoes(mapearOpcoes(tipo, opcoes));

        return p;
    }

    private List<Opcao> mapearOpcoes(TipoPergunta tipo, List<String> valores) {
        return valores.stream().map(valor -> {
            Opcao opcao = new Opcao();

            if (tipo == TipoPergunta.TEXTO_PARA_IMAGEM) {
                // Para TEXTO→IMAGEM, valor é URL da imagem
                opcao.setTexto(null);
                opcao.setImagemUrl(valor);
                log.debug("   → Opção imagem: {}", valor);
            } else {
                // Para IMAGEM→TEXTO, valor é o texto
                opcao.setTexto(valor);
                opcao.setImagemUrl(null);
                log.debug("   → Opção texto: {}", valor);
            }

            return opcao;
        }).toList();
    }
}