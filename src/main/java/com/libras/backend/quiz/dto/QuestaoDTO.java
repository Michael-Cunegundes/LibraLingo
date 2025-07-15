package com.libras.backend.quiz.dto;

import com.libras.backend.model.quiz.TipoPergunta;

import java.util.List;

public class QuestaoDTO {
    private final Long id;
    private final TipoPergunta tipo;
    private List<String> prompt;
    private final List<OptionDTO> opcoes;
    private final Integer indiceCorreto;

    // construtores, getters e setters

    public QuestaoDTO(Long id, TipoPergunta tipo, List<String> prompt, List<OptionDTO> opcoes, Integer indiceCorreto) {
        this.id = id;
        this.tipo = tipo;
        this.prompt = prompt;
        this.opcoes = opcoes;
        this.indiceCorreto = indiceCorreto;
    }


    public Long getId() {
        return id;
    }

    public TipoPergunta getTipo() {
        return tipo;
    }

    public List<String> getPrompt() {
        return prompt;
    }

    public List<OptionDTO> getOpcoes() {
        return opcoes;
    }

    public Integer getIndiceCorreto() {
        return indiceCorreto;
    }
}


