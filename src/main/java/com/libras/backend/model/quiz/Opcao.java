// src/main/java/com/libras/backend/model/quiz/Opcao.java
package com.libras.backend.model.quiz;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Opcao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O texto da opção não pode ficar em branco")
    private String texto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pergunta_id")
    private Pergunta pergunta;

    public Opcao() {}
    public Opcao(String texto) { this.texto = texto; }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public Pergunta getPergunta() { return pergunta; }
    public void setPergunta(Pergunta pergunta) { this.pergunta = pergunta; }
}
