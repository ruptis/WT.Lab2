package by.bsuir.wtlab2.dao.connection.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.connection.ConnectionFactory;
import by.bsuir.wtlab2.dao.connection.DAOProperties;
import by.bsuir.wtlab2.dao.connection.DataSource;
import by.bsuir.wtlab2.dao.connection.pool.ConnectionPool;
import by.bsuir.wtlab2.exception.ConnectionException;
import by.bsuir.wtlab2.exception.DAOConfigurationException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.Properties;

@Slf4j
@Singleton
public class PooledDataSource implements DataSource {

    private final ConnectionPool connectionPool;

    public PooledDataSource(DAOProperties daoProperties) throws DAOConfigurationException {
        loadDriver(daoProperties);
        Properties properties = getProperties(daoProperties);
        ConnectionFactory connectionFactory = new ConnectionFactoryImpl(daoProperties.getUrl(), properties);
        connectionPool = new ConnectionPool(connectionFactory, daoProperties.getPoolSize());
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        return connectionPool.getConnection();
    }

    private static Properties getProperties(DAOProperties daoProperties) throws DAOConfigurationException {
        Properties properties = new Properties();
        properties.setProperty("user", daoProperties.getUser());
        properties.setProperty("password", daoProperties.getPassword());
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("autoReconnect", "true");
        return properties;
    }

    private static void loadDriver(DAOProperties daoProperties) throws DAOConfigurationException {
        try {
            Class.forName(daoProperties.getDriver());
        } catch (ClassNotFoundException e) {
            log.error("Failed to load driver", e);
            throw new DAOConfigurationException("Failed to load driver", e);
        }
    }
}
