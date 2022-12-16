package br.com.estudandoemcasa.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.extern.java.Log;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log
public class ConexaoFactory {
    private DataSource dataSource;

    private final String url;
    private final String user;
    private final String password;
    private ComboPooledDataSource pooledDataSource;

    public ConexaoFactory() {
        this.user = "root";
        this.password = "41417852";
        this.url = "jdbc:mysql://localhost/loja_virtual?useTimeZone=true&serverTimezone=UTC";
    }

    public Connection conecta() throws SQLException {
        try {
            if (pooledDataSource == null || pooledDataSource.getAllUsers().size() <= 7) {
                pooledDataSource = new ComboPooledDataSource();
                pooledDataSource.setUser(this.user);
                pooledDataSource.setPassword(this.password);
                pooledDataSource.setJdbcUrl(this.url);

                configuraParamPooled(pooledDataSource);
                pooledDataSource.setMaxPoolSize(7);

                dataSource = pooledDataSource;
                log.info("Conectando ...");
            }
        } catch (Exception e) {
            log.info("Erro ao se conectar. " + e.getMessage());
        }
        return dataSource.getConnection();
    }

    /**
     * https://gist.github.com/rponte/1083425
     * @param pooledDataSource
     */
    private void configuraParamPooled(ComboPooledDataSource pooledDataSource) {
        pooledDataSource.setMaxIdleTime(60);
        pooledDataSource.setMaxStatementsPerConnection(10);
        pooledDataSource.setIdleConnectionTestPeriod(60);
        pooledDataSource.setCheckoutTimeout(5000);
        pooledDataSource.setBreakAfterAcquireFailure(false);
        pooledDataSource.setMaxIdleTimeExcessConnections(30);
        pooledDataSource.setNumHelperThreads(3);

    }
}
