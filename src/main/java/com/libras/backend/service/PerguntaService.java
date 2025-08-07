package com.libras.backend.service;

import com.libras.backend.quiz.dto.OptionDTO;
import com.libras.backend.quiz.dto.QuestaoDTO;
import com.libras.backend.repository.quiz.PerguntaRepository;
import com.libras.backend.model.quiz.Pergunta;
import org.springframework.stereotype.Service;

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
                .map(p -> new QuestaoDTO(
                        p.getId(),
                        p.getTipo(),
                        // como o entity tem prompt como String, embrulhamos num singletonList
                        List.of(p.getPrompt()),
                        p.getOpcoes().stream()
                                .map(o -> new OptionDTO(o.getTexto(), o.getImagemUrl()))
                                .collect(Collectors.toList()),
                        p.getIndiceCorreto()
                ))
                .toList();
    }

    // 1) Listar todas
    public List<Pergunta> listarTodas() {
        return repo.findAll();
    }

    // 2) Buscar por id
    public Optional<Pergunta> buscarPorId(Long id) {
        return repo.findById(id);
    }

    // 3) Salvar (criar ou atualizar)
    public Pergunta salvar(Pergunta pergunta) {
        return repo.save(pergunta);
    }

    // 4) Deletar
    public void deletar(Long id) {
        repo.deleteById(id);
    }
}