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

@Log
@Getter
public class ProdutoRepository {

    private final ConexaoFactory conexaoFactory;
    private ResultSet resultSet;
    private PreparedStatement prepState;

    public ProdutoRepository() throws SQLException {
        this.conexaoFactory = new ConexaoFactory();
        this.conexaoFactory.conecta().setAutoCommit(false);
    }

    public List<Produto> list() throws SQLException {

        List<Produto> produtos = new ArrayList<>();
        String queryList = "select * from produto";
        try(var ts= conexaoFactory.conecta().prepareStatement(queryList)) {
            prepState = ts;
            prepState.execute();
            resultSet = prepState.getResultSet();

            while (resultSet.next()) {
                produtos.add(new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao")));
            }

            return produtos;
        }
    }

    public Produto getProduto(Integer id) throws SQLException {

        Produto produto = new Produto();
        String queryWhere = "select * from produto where id = ?";
        try(var ts = this.conexaoFactory.conecta().prepareStatement(queryWhere)) {
            prepState = ts;
            prepState.setInt(1, id);
            prepState.execute();
            resultSet = prepState.getResultSet();
            while (resultSet.next()) {

                produto = new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"));
            }
            return produto;
        }
    }

    public Integer insertProduto(String nome, String descricao) throws SQLException {

        String queryInsert = "insert into produto (nome, descricao) values (?,?)";
        try(var ts = this.conexaoFactory
                    .conecta().prepareStatement(queryInsert, Statement.RETURN_GENERATED_KEYS)){

            prepState = ts;
            prepState.setString(1, nome);
            prepState.setString(2, descricao);
            prepState.execute();
            resultSet = prepState.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);
        }
    }

    public Boolean delete(Integer id) throws SQLException {

        String queryDelete = "delete  from produto where id = ?";

        try(var ts = this.conexaoFactory.conecta().prepareStatement(queryDelete)){
            prepState = ts;
            prepState.setInt(1, id);
            return prepState.executeUpdate() > 0;
        }
    }
}
