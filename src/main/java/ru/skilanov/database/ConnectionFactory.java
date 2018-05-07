package ru.skilanov.database;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Фабрика соединений.
 */
public class ConnectionFactory {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/servlets";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "root";

    private static ConnectionFactory connectionFactory;
    private BasicDataSource ds;

    /**
     * Конструктор.
     */
    private ConnectionFactory() {
        ds = new BasicDataSource();
        ds.setDriverClassName(DRIVER);
        ds.setUsername(LOGIN);
        ds.setPassword(PASSWORD);
        ds.setUrl(URL);

        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);

    }

    /**
     * Создаем соединение.
     *
     * @return ConnectionFactory
     */
    public static ConnectionFactory getInstance() {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
            return connectionFactory;
        } else {
            return connectionFactory;
        }
    }

    /**
     * Подключаемся.
     *
     * @return Connection
     */
    public Connection getConnection() {
        try {
            return this.ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
