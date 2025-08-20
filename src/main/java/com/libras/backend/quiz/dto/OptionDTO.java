package com.libras.backend.quiz.dto;

public class OptionDTO {

    private String texto;      // só para IMAGEM→TEXTO
    private String imagemUrl;  // só para TEXTO→IMAGEM

    // construtores, getters e setters

    public OptionDTO (String texto, String imagemUrl){

        this.texto = texto;
        this.imagemUrl = imagemUrl;

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }
}
