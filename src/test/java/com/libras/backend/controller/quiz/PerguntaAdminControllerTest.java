package com.libras.backend.controller.quiz;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.Pergunta;
import com.libras.backend.service.PerguntaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PerguntaAdminController.class)
class PerguntaAdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PerguntaService service;

    private Pergunta exemploPergunta() {
        Pergunta p = new Pergunta();
        p.setId(1L);
        p.setSinalUrl("http://url");
        p.setIndiceCorreto(2);
        p.getOpcoes().addAll(List.of(
                new Opcao("A"),
                new Opcao("B"),
                new Opcao("C"),
                new Opcao("D")
        ));
        p.getOpcoes().forEach(o -> o.setPergunta(p));
        return p;
    }

    @Test
    @DisplayName("GET /admin/perguntas → lista todas")
    void deveListarTodasAsPerguntas() throws Exception {
        List<Pergunta> lista = List.of(exemploPergunta());
        given(service.listarTodas()).willReturn(lista);

        mockMvc.perform(get("/admin/perguntas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].sinalUrl").value("http://url"));
    }

    @Test
    @DisplayName("GET /admin/perguntas/1 → retorna 200 e a pergunta")
    void deveBuscarPerguntaPorId_quandoExistir() throws Exception {
        Pergunta p = exemploPergunta();
        given(service.buscarPorId(1L)).willReturn(Optional.of(p));

        mockMvc.perform(get("/admin/perguntas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.indiceCorreto").value(2));
    }

    @Test
    @DisplayName("GET /admin/perguntas/999 → retorna 404")
    void retorna404_quandoPerguntaNaoExistir() throws Exception {
        given(service.buscarPorId(999L)).willReturn(Optional.empty());

        mockMvc.perform(get("/admin/perguntas/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /admin/perguntas → cria nova pergunta e retorna 201")
    void deveCriarNovaPergunta() throws Exception {
        Pergunta input = exemploPergunta();
        input.setId(null);

        Pergunta salvo = exemploPergunta();
        salvo.setId(42L);

        given(service.salvar(any(Pergunta.class))).willReturn(salvo);

        mockMvc.perform(post("/admin/perguntas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/admin/perguntas/42"))
                .andExpect(jsonPath("$.id").value(42));
    }

    @Test
    @DisplayName("PUT /admin/perguntas/1 → atualiza e retorna 200")
    void deveAtualizarPerguntaExistente() throws Exception {
        Pergunta existente = exemploPergunta();
        Pergunta atualizada = exemploPergunta();
        atualizada.setSinalUrl("http://nova-url");
        atualizada.setIndiceCorreto(1);

        given(service.buscarPorId(1L)).willReturn(Optional.of(existente));
        given(service.salvar(any(Pergunta.class))).willReturn(atualizada);

        mockMvc.perform(put("/admin/perguntas/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(atualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sinalUrl").value("http://nova-url"))
                .andExpect(jsonPath("$.indiceCorreto").value(1));
    }

    @Test
    @DisplayName("PUT /admin/perguntas/999 → retorna 404")
    void retorna404_quandoAtualizaPerguntaInexistente() throws Exception {
        Pergunta p = exemploPergunta();
        given(service.buscarPorId(999L)).willReturn(Optional.empty());

        mockMvc.perform(put("/admin/perguntas/{id}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(p)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /admin/perguntas/1 → retorna 204")
    void deveDeletarPerguntaExistente() throws Exception {
        given(service.buscarPorId(1L)).willReturn(Optional.of(exemploPergunta()));
        doNothing().when(service).deletar(1L);

        mockMvc.perform(delete("/admin/perguntas/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /admin/perguntas/999 → retorna 404")
    void retorna404_quandoDeletaPerguntaInexistente() throws Exception {
        given(service.buscarPorId(999L)).willReturn(Optional.empty());

        mockMvc.perform(delete("/admin/perguntas/{id}", 999L))
                .andExpect(status().isNotFound());
    }
}
