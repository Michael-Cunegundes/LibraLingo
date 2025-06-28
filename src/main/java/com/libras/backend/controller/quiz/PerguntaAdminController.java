package com.libras.backend.controller.quiz;

import com.libras.backend.backend.service.PerguntaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin/perguntas")
public class PerguntaAdminController {
    private final PerguntaService service;

    public PerguntaAdminController(PerguntaService service) {
        this.service = service;
    }

    @GetMapping
    public List<com.libras.backend.model.quiz.Pergunta> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.libras.backend.model.quiz.Pergunta> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<com.libras.backend.backend.model.quiz.Pergunta> criar(
            @Valid @RequestBody com.libras.backend.backend.model.quiz.Pergunta pergunta  // ◀— aqui
    ) {
        com.libras.backend.backend.model.quiz.Pergunta salvo = service.salvar(pergunta);
        URI uri = URI.create("/admin/perguntas/" + salvo.getId());
        return ResponseEntity.created(uri).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pergunta> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pergunta novaPergunta  // ◀— e aqui
    ) {
        return service.buscarPorId(id)
                .map(existente -> {
                    existente.setSinalUrl(novaPergunta.getSinalUrl());
                    existente.setIndiceCorreto(novaPergunta.getIndiceCorreto());
                    existente.getOpcoes().clear();
                    existente.getOpcoes().addAll(novaPergunta.getOpcoes());
                    // se você tiver relacionamento bidirecional, não esqueça de:
                    existente.getOpcoes()
                            .forEach(o -> o.setPergunta(existente));
                    Pergunta salvo = service.salvar(existente);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}