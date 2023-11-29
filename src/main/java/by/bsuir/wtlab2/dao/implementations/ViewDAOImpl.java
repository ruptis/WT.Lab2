package by.bsuir.wtlab2.dao.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.ViewDAO;
import by.bsuir.wtlab2.dao.connection.DataSource;
import by.bsuir.wtlab2.exception.DAOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class ViewDAOImpl implements ViewDAO {

    public static final String EXISTS_QUERY = "SELECT EXISTS(SELECT * FROM views WHERE question_id = ? AND user_id = ?)";
    public static final String SAVE_QUERY = "INSERT INTO views (question_id, user_id) VALUES (?, ?)";

    private final DataSource dataSource;

    @Override
    public boolean exists(long questionId, long userId) throws DAOException {
        boolean exists;
        try (Connection connection = dataSource.getConnection()) {
            exists = exists(connection, questionId, userId);
        } catch (SQLException e) {
            log.error("Cannot check if view exists", e);
            throw new DAOException("Cannot check if view exists", e);
        }
        return exists;
    }

    @Override
    public void save(long questionId, long userId) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            if (!exists(connection, questionId, userId)) {
                save(questionId, userId, connection);
            }
        } catch (SQLException e) {
            log.error("Cannot save view", e);
            throw new DAOException("Cannot save view", e);
        }
    }

    private static void save(long questionId, long userId, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            statement.setLong(1, questionId);
            statement.setLong(2, userId);
            statement.executeUpdate();
        }
    }

    private boolean exists(Connection connection, long questionId, long userId) throws SQLException {
        boolean exists;
        try (PreparedStatement statement = connection.prepareStatement(EXISTS_QUERY)) {
            statement.setLong(1, questionId);
            statement.setLong(2, userId);
            try (var resultSet = statement.executeQuery()) {
                resultSet.next();
                exists = resultSet.getBoolean(1);
            }
        }
        return exists;
    }
}
