package by.bsuir.wtlab2.utils;

import by.bsuir.wtlab2.entitymapper.EntityMapper;
import by.bsuir.wtlab2.exception.DAOException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
public final class DAOUtil {
    private DAOUtil() {
    }

    public static <T> List<T> getEntities(Connection connection, EntityMapper<T> mapper, String query, Object... params) throws DAOException {
        List<T> entities;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            entities = getEntities(statement, mapper);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return entities;
    }

    private static <T> List<T> getEntities(PreparedStatement statement, EntityMapper<T> mapper) throws DAOException {
        List<T> entities;
        try (ResultSet resultSet = statement.executeQuery()) {
            entities = mapper.toEntityList(resultSet);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return entities;
    }

    public static <T> Page<T> getEntitiesPage(Connection connection, EntityMapper<T> mapper, String query, int page, int pageSize, CountConsumer<Connection> total, Object... params) throws DAOException {
        Page<T> entities;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            statement.setInt(params.length + 1, pageSize);
            statement.setInt(params.length + 2, (page - 1) * pageSize);
            entities = getEntitiesPage(statement, mapper, page, pageSize, total.count(connection));
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return entities;
    }

    private static <T> Page<T> getEntitiesPage(PreparedStatement statement, EntityMapper<T> mapper, int page, int pageSize, int total) throws DAOException {
        Page<T> entities;
        try (ResultSet resultSet = statement.executeQuery()) {
            entities = mapper.toEntityPage(resultSet, page, pageSize, total);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return entities;
    }

    public static <T> Optional<T> getEntity(Connection connection, EntityMapper<T> mapper, String query, Object... params) throws DAOException {
        Optional<T> entity;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            entity = getEntity(statement, mapper);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return entity;
    }

    private static <T> Optional<T> getEntity(PreparedStatement statement, EntityMapper<T> mapper) throws DAOException {
        Optional<T> entity;
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                entity = Optional.of(mapper.toEntity(resultSet));
            } else {
                entity = Optional.empty();
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return entity;
    }

    public static int count(Connection connection, String query, Object... params) throws DAOException {
        int count;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            count = count(statement);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return count;
    }

    private static int count(PreparedStatement statement) throws DAOException {
        int count;
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            } else {
                throw new DAOException("Failed to get count");
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DAOException(e);
        }
        return count;
    }

    public static CountConsumer<Connection> count(String query, Object... params) {
        return connection -> count(connection, query, params);
    }
}
