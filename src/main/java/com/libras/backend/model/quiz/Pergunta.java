package com.libras.backend.model.quiz;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.libras.backend.model.quiz.Opcao;
import com.libras.backend.model.quiz.TipoPergunta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nível não pode ser nulo")
    private Integer level;               // ← NOVO CAMPO

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo da pergunta não pode ser nulo")
    private TipoPergunta tipo;

    @NotBlank(message = "O prompt não pode ficar em branco")
    private String prompt;

    @NotNull
    @Min(0)
    private Integer indiceCorreto;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "Deve haver ao menos uma opção")
    @JsonManagedReference
    private List<Opcao> opcoes = new ArrayList<>();

    // getters e setters existentes…
    public void setId(Long id) {
        this.id = id;
    }

    // ▶ GETTER / SETTER para 'level'
    public Integer getLevel() {
        return level;
    }

    public void setTipo(TipoPergunta tipo) {
        this.tipo = tipo;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setIndiceCorreto(Integer indiceCorreto) {
        this.indiceCorreto = indiceCorreto;
    }

    public void setOpcoes(List<Opcao> opcoes) {
        this.opcoes.clear();
        if (opcoes != null) {
            opcoes.forEach(o -> o.setPergunta(this));
            this.opcoes.addAll(opcoes);
        }
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIndiceCorreto() {
        return indiceCorreto;
    }

    public List<Opcao> getOpcoes() {
        return opcoes;
    }

    public Long getId() {
        return id;
    }

    public TipoPergunta getTipo() {
        return tipo;
    }

    public String getPrompt() {
        return prompt;
    }
}
