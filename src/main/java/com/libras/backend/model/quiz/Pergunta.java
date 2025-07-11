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

    @NotBlank(message = "A URL do sinal não pode ficar em branco")
//    private String sinalUrl;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoPergunta tipo;

    @NotBlank(message = "O prompt não pode ficar em branco")
    private String prompt;         // URL da imagem ou texto puro

    @NotNull
    @Min(0)
    private Integer indiceCorreto;

//    @NotNull(message = "O índice correto não pode ser nulo")
//    @Min(value = 0, message = "Índice correto inválido")
//    private Integer indiceCorreto;

    @OneToMany(mappedBy = "pergunta", cascade = CascadeType.ALL, orphanRemoval = true)
    @Size(min = 1, message = "Deve haver ao menos uma opção")
    @JsonManagedReference
    private List<Opcao> opcoes = new ArrayList<>();

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

//    public String getSinalUrl() { return sinalUrl; }
//    public void setSinalUrl(String sinalUrl) { this.sinalUrl = sinalUrl; }

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