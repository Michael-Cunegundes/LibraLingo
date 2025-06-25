// src/main/java/com/libras/backend/model/quiz/Pergunta.java
package com.libras.backend.backend.model.quiz;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A URL do sinal não pode ficar em branco")
    private String sinalUrl;

    @NotNull(message = "O índice correto não pode ser nulo")
    @Min(value = 0, message = "Índice correto inválido")
    private Integer indiceCorreto;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "Deve haver ao menos uma opção")
    private List<Opcao> opcoes = new ArrayList<>();

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSinalUrl() { return sinalUrl; }
    public void setSinalUrl(String sinalUrl) { this.sinalUrl = sinalUrl; }

    public Integer getIndiceCorreto() { return indiceCorreto; }
    public void setIndiceCorreto(Integer indiceCorreto) { this.indiceCorreto = indiceCorreto; }

    public List<Opcao> getOpcoes() { return opcoes; }
    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes.clear();
        if (opcoes != null) {
            opcoes.forEach(o -> o.setPergunta(this));
            this.opcoes.addAll(opcoes);
        }
    }
}
