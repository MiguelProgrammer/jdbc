package br.com.estudandoemcasa.repository;

import br.com.estudandoemcasa.config.ConexaoFactory;
import br.com.estudandoemcasa.model.Produto;
import lombok.Getter;
import lombok.extern.java.Log;

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
    private final Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public ProdutoRepository() throws SQLException {
        this.conexaoFactory = new ConexaoFactory();
        statement = this.getConexaoFactory().conecta().createStatement();
    }

    public List<Produto> list() {

        List<Produto> produtos = new ArrayList<>();

        try {

            statement.execute("select * from produto");
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                Produto produto = new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"));
                produtos.add(produto);
            }

        } catch (Exception e) {
            log.info("Erro ao listar Produtos. " + e.getMessage());
        }
        return produtos;
    }

    public Produto getProduto(Integer id)  {

        Produto produto = null;

        try {

            preparedStatement = statement
                    .getConnection()
                    .prepareStatement("select * from produto where id = ?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                produto = new Produto(
                        resultSet.getInt("id"),
                        resultSet.getString("nome"),
                        resultSet.getString("descricao"));
            }

        } catch (Exception e) {
            log.info("Erro ao buscar Produto. " + e.getMessage());
        }
        return produto;
    }

    public Integer insertProduto(String nome, String descricao) throws SQLException {

        Integer lastId = null;
        try {

            preparedStatement = statement
                    .getConnection()
                    .prepareStatement("insert into produto (nome, descricao) values (?,?)",
                            Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, descricao);
            preparedStatement.execute();
            resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                lastId = resultSet.getInt(1);
            }
        } catch (Exception e) {
            log.info("Erro ao inserir novo produto. " + e.getMessage());
        }
        return lastId;
    }

    public Boolean delete(Integer id) throws SQLException {

        Boolean aBoolean = null;

        try {

            preparedStatement = statement
                    .getConnection()
                    .prepareStatement("delete  from produto where id = ?");

            preparedStatement.setInt(1, id);
            aBoolean =  preparedStatement.executeUpdate() > 0;

        } catch (Exception e) {
            log.info("Erro ao buscar Produto. " + e.getMessage());
        }
        return aBoolean;
    }
}
