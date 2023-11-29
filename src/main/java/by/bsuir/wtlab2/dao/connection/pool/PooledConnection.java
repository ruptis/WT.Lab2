package by.bsuir.wtlab2.dao.connection.pool;

import by.bsuir.wtlab2.exception.ConnectionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
public final class PooledConnection implements Connection {

    private final ConnectionPool pool;
    private final Connection connection;

    void destroy() throws ConnectionException {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error("Failed to close connection", e);
            throw new ConnectionException("Failed to close connection", e);
        }
    }

    @Override
    public Statement createStatement() throws ConnectionException {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            log.error("Failed to create statement", e);
            throw new ConnectionException("Failed to create statement", e);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws ConnectionException {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            log.error("Failed to prepare statement", e);
            throw new ConnectionException("Failed to prepare statement", e);
        }
    }

    @Override
    public CallableStatement prepareCall(String sql) throws ConnectionException {
        try {
            return connection.prepareCall(sql);
        } catch (SQLException e) {
            log.error("Failed to prepare call", e);
            throw new ConnectionException("Failed to prepare call", e);
        }
    }

    @Override
    public String nativeSQL(String sql) throws ConnectionException {
        try {
            return connection.nativeSQL(sql);
        } catch (SQLException e) {
            log.error("Failed to get native SQL", e);
            throw new ConnectionException("Failed to get native SQL", e);
        }
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws ConnectionException {
        try {
            connection.setAutoCommit(autoCommit);
        } catch (SQLException e) {
            log.error("Failed to set auto commit", e);
            throw new ConnectionException("Failed to set auto commit", e);
        }
    }

    @Override
    public boolean getAutoCommit() throws ConnectionException {
        try {
            return connection.getAutoCommit();
        } catch (SQLException e) {
            log.error("Failed to get auto commit", e);
            throw new ConnectionException("Failed to get auto commit", e);
        }
    }

    @Override
    public void commit() throws ConnectionException {
        try {
            connection.commit();
        } catch (SQLException e) {
            log.error("Failed to commit", e);
            throw new ConnectionException("Failed to commit", e);
        }
    }

    @Override
    public void rollback() throws ConnectionException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.error("Failed to rollback", e);
            throw new ConnectionException("Failed to rollback", e);
        }
    }

    @Override
    public void close() throws ConnectionException {
        pool.releaseConnection(this);
    }

    @Override
    public boolean isClosed() throws ConnectionException {
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            log.error("Failed to check if connection is closed", e);
            throw new ConnectionException("Failed to check if connection is closed", e);
        }
    }

    @Override
    public DatabaseMetaData getMetaData() throws ConnectionException {
        try {
            return connection.getMetaData();
        } catch (SQLException e) {
            log.error("Failed to get meta data", e);
            throw new ConnectionException("Failed to get meta data", e);
        }
    }

    @Override
    public void setReadOnly(boolean readOnly) throws ConnectionException {
        try {
            connection.setReadOnly(readOnly);
        } catch (SQLException e) {
            log.error("Failed to set read only", e);
            throw new ConnectionException("Failed to set read only", e);
        }
    }

    @Override
    public boolean isReadOnly() throws ConnectionException {
        try {
            return connection.isReadOnly();
        } catch (SQLException e) {
            log.error("Failed to check if connection is read only", e);
            throw new ConnectionException("Failed to check if connection is read only", e);
        }
    }

    @Override
    public void setCatalog(String catalog) throws ConnectionException {
        try {
            connection.setCatalog(catalog);
        } catch (SQLException e) {
            log.error("Failed to set catalog", e);
            throw new ConnectionException("Failed to set catalog", e);
        }
    }

    @Override
    public String getCatalog() throws ConnectionException {
        try {
            return connection.getCatalog();
        } catch (SQLException e) {
            log.error("Failed to get catalog", e);
            throw new ConnectionException("Failed to get catalog", e);
        }
    }

    @Override
    public void setTransactionIsolation(int level) throws ConnectionException {
        try {
            connection.setTransactionIsolation(level);
        } catch (SQLException e) {
            log.error("Failed to set transaction isolation", e);
            throw new ConnectionException("Failed to set transaction isolation", e);
        }
    }

    @Override
    public int getTransactionIsolation() throws ConnectionException {
        try {
            return connection.getTransactionIsolation();
        } catch (SQLException e) {
            log.error("Failed to get transaction isolation", e);
            throw new ConnectionException("Failed to get transaction isolation", e);
        }
    }

    @Override
    public SQLWarning getWarnings() throws ConnectionException {
        try {
            return connection.getWarnings();
        } catch (SQLException e) {
            log.error("Failed to get warnings", e);
            throw new ConnectionException("Failed to get warnings", e);
        }
    }

    @Override
    public void clearWarnings() throws ConnectionException {
        try {
            connection.clearWarnings();
        } catch (SQLException e) {
            log.error("Failed to clear warnings", e);
            throw new ConnectionException("Failed to clear warnings", e);
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws ConnectionException {
        try {
            return connection.createStatement(resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            log.error("Failed to create statement", e);
            throw new ConnectionException("Failed to create statement", e);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws ConnectionException {
        try {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            log.error("Failed to prepare statement", e);
            throw new ConnectionException("Failed to prepare statement", e);
        }
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws ConnectionException {
        try {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
        } catch (SQLException e) {
            log.error("Failed to prepare call", e);
            throw new ConnectionException("Failed to prepare call", e);
        }
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws ConnectionException {
        try {
            return connection.getTypeMap();
        } catch (SQLException e) {
            log.error("Failed to get type map", e);
            throw new ConnectionException("Failed to get type map", e);
        }
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws ConnectionException {
        try {
            connection.setTypeMap(map);
        } catch (SQLException e) {
            log.error("Failed to set type map", e);
            throw new ConnectionException("Failed to set type map", e);
        }
    }

    @Override
    public void setHoldability(int holdability) throws ConnectionException {
        try {
            connection.setHoldability(holdability);
        } catch (SQLException e) {
            log.error("Failed to set holdability", e);
            throw new ConnectionException("Failed to set holdability", e);
        }
    }

    @Override
    public int getHoldability() throws ConnectionException {
        try {
            return connection.getHoldability();
        } catch (SQLException e) {
            log.error("Failed to get holdability", e);
            throw new ConnectionException("Failed to get holdability", e);
        }
    }

    @Override
    public Savepoint setSavepoint() throws ConnectionException {
        try {
            return connection.setSavepoint();
        } catch (SQLException e) {
            log.error("Failed to set savepoint", e);
            throw new ConnectionException("Failed to set savepoint", e);
        }
    }

    @Override
    public Savepoint setSavepoint(String name) throws ConnectionException {
        try {
            return connection.setSavepoint(name);
        } catch (SQLException e) {
            log.error("Failed to set savepoint", e);
            throw new ConnectionException("Failed to set savepoint", e);
        }
    }

    @Override
    public void rollback(Savepoint savepoint) throws ConnectionException {
        try {
            connection.rollback(savepoint);
        } catch (SQLException e) {
            log.error("Failed to rollback", e);
            throw new ConnectionException("Failed to rollback", e);
        }
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws ConnectionException {
        try {
            connection.releaseSavepoint(savepoint);
        } catch (SQLException e) {
            log.error("Failed to release savepoint", e);
            throw new ConnectionException("Failed to release savepoint", e);
        }
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws ConnectionException {
        try {
            return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (SQLException e) {
            log.error("Failed to create statement", e);
            throw new ConnectionException("Failed to create statement", e);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws ConnectionException {
        try {
            return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (SQLException e) {
            log.error("Failed to prepare statement", e);
            throw new ConnectionException("Failed to prepare statement", e);
        }
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws ConnectionException {
        try {
            return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
        } catch (SQLException e) {
            log.error("Failed to prepare call", e);
            throw new ConnectionException("Failed to prepare call", e);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws ConnectionException {
        try {
            return connection.prepareStatement(sql, autoGeneratedKeys);
        } catch (SQLException e) {
            log.error("Failed to prepare statement", e);
            throw new ConnectionException("Failed to prepare statement", e);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws ConnectionException {
        try {
            return connection.prepareStatement(sql, columnIndexes);
        } catch (SQLException e) {
            log.error("Failed to prepare statement", e);
            throw new ConnectionException("Failed to prepare statement", e);
        }
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws ConnectionException {
        try {
            return connection.prepareStatement(sql, columnNames);
        } catch (SQLException e) {
            log.error("Failed to prepare statement", e);
            throw new ConnectionException("Failed to prepare statement", e);
        }
    }

    @Override
    public Clob createClob() throws ConnectionException {
        try {
            return connection.createClob();
        } catch (SQLException e) {
            log.error("Failed to create clob", e);
            throw new ConnectionException("Failed to create clob", e);
        }
    }

    @Override
    public Blob createBlob() throws ConnectionException {
        try {
            return connection.createBlob();
        } catch (SQLException e) {
            log.error("Failed to create blob", e);
            throw new ConnectionException("Failed to create blob", e);
        }
    }

    @Override
    public NClob createNClob() throws ConnectionException {
        try {
            return connection.createNClob();
        } catch (SQLException e) {
            log.error("Failed to create nclob", e);
            throw new ConnectionException("Failed to create nclob", e);
        }
    }

    @Override
    public SQLXML createSQLXML() throws ConnectionException {
        try {
            return connection.createSQLXML();
        } catch (SQLException e) {
            log.error("Failed to create sqlxml", e);
            throw new ConnectionException("Failed to create sqlxml", e);
        }
    }

    @Override
    public boolean isValid(int timeout) throws ConnectionException {
        try {
            return connection.isValid(timeout);
        } catch (SQLException e) {
            log.error("Failed to check if connection is valid", e);
            throw new ConnectionException("Failed to check if connection is valid", e);
        }
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        connection.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        connection.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws ConnectionException {
        try {
            return connection.getClientInfo(name);
        } catch (SQLException e) {
            log.error("Failed to get client info", e);
            throw new ConnectionException("Failed to get client info", e);
        }
    }

    @Override
    public Properties getClientInfo() throws ConnectionException {
        try {
            return connection.getClientInfo();
        } catch (SQLException e) {
            log.error("Failed to get client info", e);
            throw new ConnectionException("Failed to get client info", e);
        }
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws ConnectionException {
        try {
            return connection.createArrayOf(typeName, elements);
        } catch (SQLException e) {
            log.error("Failed to create array of", e);
            throw new ConnectionException("Failed to create array of", e);
        }
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws ConnectionException {
        try {
            return connection.createStruct(typeName, attributes);
        } catch (SQLException e) {
            log.error("Failed to create struct", e);
            throw new ConnectionException("Failed to create struct", e);
        }
    }

    @Override
    public void setSchema(String schema) throws ConnectionException {
        try {
            connection.setSchema(schema);
        } catch (SQLException e) {
            log.error("Failed to set schema", e);
            throw new ConnectionException("Failed to set schema", e);
        }
    }

    @Override
    public String getSchema() throws ConnectionException {
        try {
            return connection.getSchema();
        } catch (SQLException e) {
            log.error("Failed to get schema", e);
            throw new ConnectionException("Failed to get schema", e);
        }
    }

    @Override
    public void abort(Executor executor) throws ConnectionException {
        try {
            connection.abort(executor);
        } catch (SQLException e) {
            log.error("Failed to abort", e);
            throw new ConnectionException("Failed to abort", e);
        }
    }
    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws ConnectionException {
        try {
            connection.setNetworkTimeout(executor, milliseconds);
        } catch (SQLException e) {
            log.error("Failed to set network timeout", e);
            throw new ConnectionException("Failed to set network timeout", e);
        }
    }

    @Override
    public int getNetworkTimeout() throws ConnectionException {
        try {
            return connection.getNetworkTimeout();
        } catch (SQLException e) {
            log.error("Failed to get network timeout", e);
            throw new ConnectionException("Failed to get network timeout", e);
        }
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws ConnectionException {
        try {
            return connection.unwrap(iface);
        } catch (SQLException e) {
            log.error("Failed to unwrap", e);
            throw new ConnectionException("Failed to unwrap", e);
        }
    }
    @Override
    public boolean isWrapperFor(Class<?> iface) throws ConnectionException {
        try {
            return connection.isWrapperFor(iface);
        } catch (SQLException e) {
            log.error("Failed to check if is wrapper for", e);
            throw new ConnectionException("Failed to check if is wrapper for", e);
        }
    }
}
