package br.com.estudandoemcasa.service;


import br.com.estudandoemcasa.model.Produto;
import br.com.estudandoemcasa.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import java.sql.SQLException;
import java.util.List;

@Log
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoService  {
    private ProdutoRepository repository;

    public List<Produto> list() {
        log.info("\nservice\nMétodo Lista Produtos\n");
        return this.repository.list();
    }
    public Produto getProduto(Integer id) {
        log.info("\nservice\nMétodo Busca Produto\n");
        return this.repository.getProduto(id);
    }
    public Integer insertProduto(String nome, String descricao) {
        log.info("\nservice\nMétodo Insere Produto\n");
        return this.repository.insertProduto(nome, descricao);
    }
    public Boolean deleteProduto(Integer id) throws SQLException {
        log.info("\nservice\nMétodo Deleta Produto\n");
        return this.repository.delete(id);
    }

}
