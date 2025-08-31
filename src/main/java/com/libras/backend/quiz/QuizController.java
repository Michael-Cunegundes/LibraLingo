package com.libras.backend.quiz;

import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.quiz.dto.RespostaQuizDTO;
import com.libras.backend.quiz.dto.ResultadoQuizDTO;
import com.libras.backend.service.PerguntaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private static final Logger log = LoggerFactory.getLogger(QuizController.class);
    private final QuizService quizService;
    private final PerguntaService perguntaService;

    public QuizController(QuizService quizService, PerguntaService perguntaService) {
        this.quizService = quizService;
        this.perguntaService = perguntaService;
        log.info("🎯 QuizController inicializado");
    }

    @GetMapping("/levels/{level}/questions")
    public ResponseEntity<List<QuestaoDTO>> buscarQuestoesPorNivel(@PathVariable Integer level) {
        log.info("🎮 === SOLICITAÇÃO QUIZ NÍVEL {} ===", level);

        try {
            List<QuestaoDTO> questoes = perguntaService.listarPorNivel(level);

            // DEBUG: Verificar o que está sendo retornado
            for (QuestaoDTO q : questoes) {
                log.info("Questão ID {}: tipo={}, prompt={}",
                        q.getId(), q.getTipo(), q.getPrompt());

                // Se for a primeira pergunta do nível 1, log detalhado
                if (level == 1 && q.getId() == 1) {
                    log.info("🔍 DEBUG Pergunta 1: ");
                    log.info("  - Tipo: {}", q.getTipo());
                    log.info("  - Prompt lista: {}", q.getPrompt());
                    log.info("  - Quantidade de imagens: {}", q.getPrompt().size());
                    for (int i = 0; i < q.getPrompt().size(); i++) {
                        log.info("  - Imagem {}: {}", i+1, q.getPrompt().get(i));
                    }
                }
            }

            return ResponseEntity.ok(questoes);
        } catch (Exception e) {
            log.error("❌ Erro ao buscar questões do nível {}: {}", level, e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/respostas")
    public ResponseEntity<ResultadoQuizDTO> calcularResultado(
            @Valid @RequestBody List<RespostaQuizDTO> respostas) {

        log.info("🏆 === CALCULANDO RESULTADO === [{}]", LocalDateTime.now());
        log.info("📝 Recebidas {} respostas", respostas.size());

        try {
            // Validação
            if (respostas.isEmpty() || respostas.size() > 10) {
                log.warn("⚠️ Número inválido de respostas: {}", respostas.size());
                return ResponseEntity.badRequest().build();
            }

            // Log das respostas (sem expor gabarito) - CORRIGIDO
            AtomicInteger index = new AtomicInteger(1);
            respostas.forEach(resposta ->
                    log.debug("   Resposta {}: Pergunta {} → Opção {}",
                            index.getAndIncrement(),
                            resposta.getPerguntaId(),
                            resposta.getOpcaoEscolhida())
            );

            ResultadoQuizDTO resultado = quizService.calculaResultado(respostas);

            // Métricas básicas
            int acertos = resultado.getPontuacao();
            int total = respostas.size();
            double percentual = (acertos * 100.0) / total;

            log.info("🎯 RESULTADO: {}/{} acertos ({:.1f}%)", acertos, total, percentual);

            // Log de qualidade da sessão
            if (percentual >= 80) {
                log.info("🌟 Excelente performance!");
            } else if (percentual >= 60) {
                log.info("👍 Boa performance!");
            } else {
                log.info("📚 Pode melhorar - sugerindo revisão");
            }

            return ResponseEntity.ok(resultado);

        } catch (Exception e) {
            log.error("❌ Erro ao calcular resultado: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    // Endpoint adicional para debug (desenvolvimento)
    @GetMapping("/perguntas")
    public ResponseEntity<List<QuestaoDTO>> listarTodasPerguntas() {
        log.debug("🔍 Listando todas as perguntas (DEBUG)");

        try {
            List<QuestaoDTO> todasPerguntas = quizService.listarPerguntas();
            log.debug("📋 Total de perguntas no sistema: {}", todasPerguntas.size());
            return ResponseEntity.ok(todasPerguntas);

        } catch (Exception e) {
            log.error("❌ Erro ao listar perguntas: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    // Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        log.debug("💚 Health check solicitado");
        return ResponseEntity.ok("LibraLingo Quiz API - Funcionando! 🚀");
    }
}