package com.libras.backend.model.quiz;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Opcao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O texto da opção não pode ficar em branco")
    private String texto;

    @ManyToOne
    @JoinColumn(name = "pergunta_id", nullable = false)
    @JsonBackReference
    private Pergunta pergunta;

    // Construtor padrão (necessário para JPA)
    public Opcao() {}

    // Construtor de conveniência
    public Opcao(String texto) {
        this.texto = texto;
    }

    // getters / setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Pergunta getPergunta() { return pergunta; }
    public void setPergunta(Pergunta pergunta) { this.pergunta = pergunta; }
}