package com.libras.backend.model.quiz;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Opcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // REMOVER @NotBlank aqui
    @Column(nullable = true)
    private String texto;

    @Column(nullable = true)
    private String imagemUrl;

    @ManyToOne
    @JoinColumn(name = "pergunta_id", nullable = false)
    @JsonBackReference
    private Pergunta pergunta;

    public Opcao() {}
    public Opcao(String texto) { this.texto = texto; }

    // getters / setters …
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public Pergunta getPergunta() { return pergunta; }
    public void setPergunta(Pergunta pergunta) { this.pergunta = pergunta; }
}
