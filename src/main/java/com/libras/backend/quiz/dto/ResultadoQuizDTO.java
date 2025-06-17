package com.libras.backend.quiz.dto;


public class ResultadoQuizDTO {
    private int pontuacao;
    private String mensagem;

    public ResultadoQuizDTO() { }

    public ResultadoQuizDTO(int pontuacao, String mensagem) {
        this.pontuacao = pontuacao;
        this.mensagem = mensagem;
    }

    // Getters e setters
    public int getPontuacao() { return pontuacao; }
    public void setPontuacao(int pontuacao) { this.pontuacao = pontuacao; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}
