// src/main/java/com/libras/backend/service/PerguntaService.java
package com.libras.backend.service;

import com.libras.backend.model.quiz.TipoPergunta;
import com.libras.backend.quiz.dto.OptionDTO;
import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.repository.quiz.PerguntaRepository;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerguntaService {

    private final PerguntaRepository repo;

    public PerguntaService(PerguntaRepository repo) {
        this.repo = repo;
    }

    public List<QuestaoDTO> listarPorNivel(Integer level) {
        return repo.findByLevel(level)
                .stream()
                .map(this::converterParaDTO)  // ← USAR MÉTODO PRÓPRIO
                .toList();
    }

    // MÉTODO PRIVADO PARA CONVERTER PERGUNTA → DTO
    private QuestaoDTO converterParaDTO(Pergunta p) {
        List<String> promptList = new ArrayList<>();

        // LÓGICA CORRETA PARA MÚLTIPLAS IMAGENS
        if (p.getTipo() == TipoPergunta.IMAGEM_PARA_TEXTO && p.getPrompt() != null) {
            // Verificar se tem vírgula (múltiplas imagens)
            if (p.getPrompt().contains(",")) {
                // Dividir por vírgula e adicionar cada imagem
                String[] imagens = p.getPrompt().split(",");
                for (String imagem : imagens) {
                    String imagemLimpa = imagem.trim();
                    if (!imagemLimpa.isEmpty()) {
                        promptList.add(imagemLimpa);
                    }
                }
            } else {
                // Uma única imagem
                promptList.add(p.getPrompt().trim());
            }
        } else {
            // Para TEXTO_PARA_IMAGEM, é sempre texto simples
            promptList.add(p.getPrompt());
        }

        // Converter opções
        List<OptionDTO> opcoes = p.getOpcoes().stream()
                .map(o -> new OptionDTO(o.getTexto(), o.getImagemUrl()))
                .collect(Collectors.toList());

        return new QuestaoDTO(
                p.getId(),
                p.getTipo(),
                promptList,  // ← AGORA ESTÁ CORRETO!
                opcoes,
                p.getIndiceCorreto()
        );
    }

    // OUTROS MÉTODOS MANTIDOS
    public List<Pergunta> listarTodas() {
        return repo.findAll();
    }

    public Optional<Pergunta> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public Pergunta salvar(Pergunta pergunta) {
        return repo.save(pergunta);
    }

    public void deletar(Long id) {
        repo.deleteById(id);
    }
}