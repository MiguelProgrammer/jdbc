package br.com.estudandoemcasa.repository;

import br.com.estudandoemcasa.config.ConexaoFactory;
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
import java.util.Optional;

@Log
@Getter
public class ProdutoRepository {

    private final ConexaoFactory conexaoFactory;
    private ResultSet resultSet;
    private PreparedStatement prepState;

    private String query;

    public ProdutoRepository() {
        this.conexaoFactory = new ConexaoFactory();
    }

    public List<Produto> list() {

        List<Produto> produtos = new ArrayList<>();
        String queryList = "select * from produto";
        try {

            prepState = this.conexaoFactory.conecta().prepareStatement(queryList);
            prepState.executeQuery();
            resultSet = prepState.getResultSet();

            while (resultSet.next()) {
                produtos.add(new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao")));
            }

        } catch (Exception e) {
            log.info("Erro ao listar Produtos. " + e.getMessage());
        }
        return produtos;
    }

    public Produto getProduto(Integer id) {

        Optional<Produto> optionalProd = Optional.empty();
        String queryWhere = "select * from produto where id = ?";
        try {

            prepState = this.conexaoFactory.conecta().prepareStatement(queryWhere);
            prepState.setInt(1, id);
            prepState.executeQuery().next();

            optionalProd = Optional.of(new Produto(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getString("descricao")));

        } catch (Exception e) {
            log.info("Erro ao buscar Produto. " + e.getMessage());
        }
        return optionalProd.orElse(null);
    }

    public Integer insertProduto(String nome, String descricao) {

        Integer lastId = null;
        String queryInsert = "insert into produto (nome, descricao) values (?,?)";
        var IdCriado = Statement.RETURN_GENERATED_KEYS;
        try {
            prepState = this.conexaoFactory
                    .conecta().prepareStatement(queryInsert,IdCriado);

            prepState.setString(1, nome);
            prepState.setString(2, descricao);
            prepState.execute();

            while (prepState.getGeneratedKeys().next()) {
                lastId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            log.info("Erro ao inserir novo produto. " + e.getMessage());
        }
        return lastId;
    }

    public Boolean delete(Integer id) throws SQLException {

        String queryDelete = "delete  from produto where id = ?";

        try {
            prepState = this.conexaoFactory.conecta().prepareStatement(queryDelete);
            prepState.setInt(1, id);
        } catch (Exception e) {
            log.info("Erro ao buscar Produto. " + e.getMessage());
        }
        return prepState.executeUpdate() > 0;
    }
}
