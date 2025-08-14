package com.libras.backend.quiz.dto;

import jakarta.validation.constraints.NotNull;

public class RespostaQuizDTO {

    @NotNull(message = "O ID da pergunta é obrigatório")
    private Long perguntaId;

    @NotNull(message = "Você deve escolher uma opção")
    private Integer opcaoEscolhida;

    public RespostaQuizDTO() { }

    public RespostaQuizDTO(Long perguntaId, Integer opcaoEscolhida) {
        this.perguntaId = perguntaId;
        this.opcaoEscolhida = opcaoEscolhida;
    }

    public Long getPerguntaId() { return perguntaId; }
    public void setPerguntaId(Long perguntaId) { this.perguntaId = perguntaId; }

    public Integer getOpcaoEscolhida() { return opcaoEscolhida; }
    public void setOpcaoEscolhida(Integer opcaoEscolhida) { this.opcaoEscolhida = opcaoEscolhida; }
}