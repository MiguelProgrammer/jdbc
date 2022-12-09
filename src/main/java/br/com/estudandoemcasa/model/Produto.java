package br.com.estudandoemcasa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Produto {

    private Integer id;
    private String nome;
    private String descricao;
}
