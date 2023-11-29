package by.bsuir.wtlab2.dao.connection.pool;

import by.bsuir.wtlab2.application.di.Destroyable;
import by.bsuir.wtlab2.dao.connection.ConnectionFactory;
import by.bsuir.wtlab2.exception.ConnectionException;
import by.bsuir.wtlab2.exception.DAOConfigurationException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class ConnectionPool implements Destroyable {

    private final ConnectionFactory connectionFactory;
    private final BlockingQueue<PooledConnection> connections;

    public ConnectionPool(ConnectionFactory connectionFactory, int poolSize) throws DAOConfigurationException {
        this.connectionFactory = connectionFactory;
        this.connections = new ArrayBlockingQueue<>(poolSize);
        initialize(poolSize);
    }

    private void initialize(int poolSize) throws DAOConfigurationException {
        for (int i = 0; i < poolSize; i++) {
            try {
                connections.put(createConnection());
            } catch (InterruptedException e) {
                log.error("Failed to initialize connection pool", e);
                Thread.currentThread().interrupt();
                throw new DAOConfigurationException("Failed to initialize connection pool", e);
            } catch (ConnectionException e) {
                log.error("Failed to create connection", e);
                throw new DAOConfigurationException("Failed to create connection", e);
            }
        }
        log.info("Connection pool initialized");
    }

    private PooledConnection createConnection() throws ConnectionException {
        return new PooledConnection(this, connectionFactory.createConnection());
    }

    public Connection getConnection() throws ConnectionException {
        try {
            return connections.take();
        } catch (InterruptedException e) {
            log.error("Failed to get connection", e);
            Thread.currentThread().interrupt();
            throw new ConnectionException("Failed to get connection", e);
        }
    }

    void releaseConnection(PooledConnection connection) throws ConnectionException {
        try {
            connections.put(connection);
        } catch (InterruptedException e) {
            log.error("Failed to release connection", e);
            Thread.currentThread().interrupt();
            throw new ConnectionException("Failed to release connection", e);
        }
    }

    @Override
    public void destroy() {
        for (PooledConnection connection : connections) {
            try {
                connection.destroy();
            } catch (ConnectionException e) {
                log.error("Failed to destroy connection", e);
            }
        }
        log.info("Connection pool destroyed");
    }
}
