package br.com.estudandoemcasa.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

    private Integer id;
    private String nome;
    private List<Produto> produtos;


}
