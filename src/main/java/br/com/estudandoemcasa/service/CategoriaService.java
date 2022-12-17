package br.com.estudandoemcasa.service;


import br.com.estudandoemcasa.model.Categoria;
import br.com.estudandoemcasa.model.Produto;
import br.com.estudandoemcasa.repository.CategoriaRepository;
import br.com.estudandoemcasa.repository.ProdutoRepository;
import lombok.extern.java.Log;

import java.sql.SQLException;
import java.util.List;

@Log
public class CategoriaService {
    private final CategoriaRepository repository = new CategoriaRepository();

    public CategoriaService() throws SQLException {
        /**
         *
         * @throws SQLException
         */
    }

    public List<Categoria> list() throws SQLException {
        log.info("\nservice\nMétodo Lista Categorias\n");
        if(!this.repository.list().isEmpty()) {
            return this.repository.list();
        }
        return this.repository.listEmptyProd();
    }

    public Categoria getCategoria(Integer id) {
        log.info("\nservice\nMétodo Busca Categoria\n");
        try {
            return this.repository.getCategoria(id);
        } catch (Exception e) {
            log.info("Categoria não encontrada.");
        }
        return null;
    }

    public Integer insertCategoria(String nome) throws SQLException {
        log.info("\nservice\nMétodo Insere Categoria\n");
        return this.repository.insertCategoria(nome);
    }

    public Boolean deleteCategoria(Integer id) throws SQLException {
        log.info("\nservice\nMétodo Deleta Categoria\n");
        if (this.getCategoria(id).getId() > 0) {
            return this.repository.delete(id);
        }
        return Boolean.FALSE;
    }

}
