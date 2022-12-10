package br.com.estudandoemcasa.config;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;

@Log
public class ConexaoFactory {

    private final String url;
    private final String user;
    private final String password;

    private Connection conexao;

    public ConexaoFactory() {
        this.url = "jdbc:mysql://localhost/loja_virtual?useTimeZone=true&serverTimezone=UTC";
        this.user = "root";
        this.password = "41417852";
    }

    public Connection conecta() {
        try {
            if (conexao == null) {
                conexao = DriverManager.getConnection(this.url, user, password);
                log.info("Conectando ...");
            }
        } catch(Exception e){
            log.info("Erro ao se conectar. " + e.getMessage());
        }
        return conexao;
    }
}
