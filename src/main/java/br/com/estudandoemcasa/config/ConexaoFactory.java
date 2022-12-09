package br.com.estudandoemcasa.config;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log
public class ConexaoFactory {

    private String url;
    private String user;
    private String password;

    private static Connection conexao;

    public ConexaoFactory() {
        this.url = "jdbc:mysql://localhost/loja_virtual?useTimeZone=true&serverTimezone=UTC";
        this.user = "root";
        this.password = "41417852";
    }

    public Connection conecta() throws SQLException {

        if (conexao == null) {
            conexao = DriverManager.getConnection(this.url, user, password);
            log.info("Conectando ...");
        }
        return conexao;
    }
}
