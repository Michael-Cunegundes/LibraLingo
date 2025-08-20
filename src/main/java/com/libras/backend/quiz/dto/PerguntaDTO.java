package com.libras.backend.quiz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

public class PerguntaDTO {

    @NotNull
    private Long id;

    @NotBlank(message = "A URL do sinal não pode estar em branco")
    private String sinalUrl;

    @Size(min = 2, message = "Devem vir pelo menos 2 opções")
    private List<@NotBlank String> opcoes;

    @NotNull(message = "O índice correto deve ser informado")
    private Integer indiceCorreto;


    public PerguntaDTO() { }

    public PerguntaDTO(Long id, String sinalUrl, List<String> opcoes, Integer indiceCorreto) {
        this.id = id;
        this.sinalUrl = sinalUrl;
        this.opcoes = opcoes;
        this.indiceCorreto = indiceCorreto;
    }

    // Getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSinalUrl() { return sinalUrl; }
    public void setSinalUrl(String sinalUrl) { this.sinalUrl = sinalUrl; }

    public List<String> getOpcoes() { return opcoes; }
    public void setOpcoes(List<String> opcoes) { this.opcoes = opcoes; }

    public Integer getIndiceCorreto() { return indiceCorreto; }
    public void setIndiceCorreto(Integer indiceCorreto) { this.indiceCorreto = indiceCorreto; }
}