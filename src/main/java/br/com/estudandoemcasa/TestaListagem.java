package br.com.estudandoemcasa;

import br.com.estudandoemcasa.config.ConexaoFactory;
import br.com.estudandoemcasa.model.Produto;
import lombok.extern.java.Log;

import java.sql.*;

@Log
public class TestaListagem {

    public static void main(String[] args) throws SQLException {

        ConexaoFactory conexao = new ConexaoFactory();

        String querySelect = "select * from produto";

        Statement statement = conexao.conecta().createStatement();
        statement.execute(querySelect);
        ResultSet resultSet = statement.getResultSet();

        while (resultSet.next()) {

            Integer id =  resultSet.getInt("id");
            String nome =  resultSet.getString("nome");
            String descricao = resultSet.getString("descricao");

            Produto produto = new Produto(id, nome, descricao);
            System.out.println(produto);

        }

        conexao.conecta().close();
    }
}
