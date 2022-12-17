package br.com.estudandoemcasa.repository;

import br.com.estudandoemcasa.config.ConexaoFactory;
import br.com.estudandoemcasa.model.Categoria;
import br.com.estudandoemcasa.model.Produto;
import lombok.Getter;
import lombok.extern.java.Log;
import lombok.var;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Getter
public class CategoriaRepository {

    private final ConexaoFactory conexaoFactory;
    private ResultSet resultSet;
    private PreparedStatement prepState;

    public CategoriaRepository() throws SQLException {
        this.conexaoFactory = new ConexaoFactory();
    }

    public List<Categoria> list() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();
        String queryList = "select c.id, c.nome, p.id, p.nome, p.descricao " +
                "from categoria c, produto p where c.id = p.categoria_id";

        try (var ts = conexaoFactory
                .conecta()
                .prepareStatement(queryList)) {
            prepState = ts;
            prepState.execute();
            resultSet = prepState.getResultSet();

            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {

                produtos.add(new Produto(resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getInt(1)));

                var id = resultSet.getInt(1);

                categorias.add(new Categoria(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        produtos.stream().filter(pr -> pr.getId().equals(id)).collect(Collectors.toList())));
            }

            return categorias;
        }
    }

    public Categoria getCategoria(Integer id) throws SQLException {

        String query = "select c.id, c.nome, p.id, p.nome, p.descricao " +
                " from categoria c, produto p where c.id = p.categoria_id and c.id = ?";

        try (var ts = conexaoFactory
                .conecta()
                .prepareStatement(query)) {
            prepState = ts;
            prepState.setInt(1, id);
            prepState.execute();
            resultSet = prepState.getResultSet();
            resultSet.next();

            List<Produto> produtos = new ArrayList<>();
            produtos.add(new Produto(resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getInt(6)));

            return new Categoria(resultSet.getInt(1),resultSet.getString(2), produtos);
        }
    }

    public Integer insertCategoria(String nome) throws SQLException {

        try (var ts = conexaoFactory
                .conecta()
                .prepareStatement("insert into categoria (nome) values (?)",
                        Statement.RETURN_GENERATED_KEYS)) {

            prepState = ts;
            prepState.setString(1, nome);
            prepState.execute();
            resultSet = prepState.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public Boolean delete(Integer id) throws SQLException {
        try (var ts =
                     conexaoFactory
                             .conecta().prepareStatement("delete from categoria where id = ?")) {
            prepState = ts;
            prepState.setInt(1, id);
            return prepState.executeUpdate() > 0;
        }
    }

    public List<Categoria> listEmptyProd() throws SQLException {

        List<Categoria> categorias = new ArrayList<>();
        String queryList = "select * from categoria";

        try (var ts = conexaoFactory
                .conecta()
                .prepareStatement(queryList)) {
            prepState = ts;
            prepState.execute();
            resultSet = prepState.getResultSet();

            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {

                categorias.add(new Categoria(
                        resultSet.getInt(1),
                        resultSet.getString(2), produtos));
            }

            return categorias;
        }
    }
}
