package by.bsuir.wtlab2.dao.connection.implementations;

import by.bsuir.wtlab2.dao.connection.ConnectionFactory;
import by.bsuir.wtlab2.exception.ConnectionException;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@RequiredArgsConstructor
public class ConnectionFactoryImpl implements ConnectionFactory {

    private final String url;
    private final Properties properties;

    @Override
    public Connection createConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(url, properties);
        } catch (SQLException e) {
            throw new ConnectionException("Cannot create connection", e);
        }
    }
}
