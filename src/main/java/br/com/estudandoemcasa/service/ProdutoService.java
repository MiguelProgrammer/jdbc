package br.com.estudandoemcasa.service;


import br.com.estudandoemcasa.model.Produto;
import br.com.estudandoemcasa.repository.ProdutoRepository;
import lombok.extern.java.Log;

import java.sql.SQLException;
import java.util.List;

@Log
public class ProdutoService  {
    private ProdutoRepository repository = new ProdutoRepository();

    public ProdutoService() throws SQLException {
    }


    public List<Produto> list() throws SQLException {
        log.info("\nservice\nMétodo Lista Produtos\n");
        return this.repository.list();
    }

    public Produto getProduto(Integer id) throws SQLException {
        log.info("\nservice\nMétodo Busca Produto\n");
        return this.repository.getProduto(id);
    }
}