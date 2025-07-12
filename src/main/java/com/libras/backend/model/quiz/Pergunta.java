package com.libras.backend.model.quiz;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pergunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "A URL do sinal não pode ficar em branco")

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo da pergunta não pode ser nulo")
    private TipoPergunta tipo;

    @NotBlank(message = "O prompt não pode ficar em branco")
    private String prompt;         // URL da imagem ou texto puro

    @NotNull
    @Min(0)
    private Integer indiceCorreto;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "Deve haver ao menos uma opção")
    @JsonManagedReference
    private List<Opcao> opcoes = new ArrayList<>();

    public TipoPergunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPergunta tipo) {
        this.tipo = tipo;
    }

    // GETTER / SETTER para 'prompt'
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

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