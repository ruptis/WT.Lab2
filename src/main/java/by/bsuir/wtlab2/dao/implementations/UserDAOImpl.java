package by.bsuir.wtlab2.dao.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.dao.connection.DataSource;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.entitymapper.implementations.UserMapper;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.DAOUtil;
import by.bsuir.wtlab2.utils.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

    public static final String GET_BY_ID_QUERY = "SELECT * FROM user_view WHERE id = ?";
    private static final String GET_BY_USERNAME_QUERY = "SELECT * FROM user_view WHERE username = ?";
    private static final String GET_BY_EMAIL_QUERY = "SELECT * FROM user_view WHERE email = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM user_view ORDER BY registration_datetime";
    private static final String GET_ALL_PAGED_QUERY = "SELECT * FROM user_view ORDER BY registration_datetime LIMIT ? OFFSET ?";
    private static final String SAVE_QUERY = "INSERT INTO user (username, password, email, role_id, registration_datetime) VALUES (?, ?, ?, (SELECT id FROM role WHERE name = ?), ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET username = ?, password = ?, email = ?, role_id = (SELECT id FROM role WHERE name = ?), registration_datetime = ? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM user";

    private final UserMapper userMapper;
    private final DataSource dataSource;

    @Override
    public Optional<User> getById(long id) throws DAOException {
        Optional<User> user;
        try (Connection connection = dataSource.getConnection()) {
            user = DAOUtil.getEntity(connection, userMapper, GET_BY_ID_QUERY, id);
        } catch (SQLException e) {
            log.error("Cannot get user by id", e);
            throw new DAOException("Cannot get user by id", e);
        }
        return user;
    }

    @Override
    public Optional<User> getByUsername(String username) throws DAOException {
        Optional<User> user;
        try (Connection connection = dataSource.getConnection()) {
            user = DAOUtil.getEntity(connection, userMapper, GET_BY_USERNAME_QUERY, username);
        } catch (SQLException e) {
            log.error("Cannot get user by username", e);
            throw new DAOException("Cannot get user by username", e);
        }
        return user;
    }

    @Override
    public Optional<User> getByEmail(String email) throws DAOException {
        Optional<User> user;
        try (Connection connection = dataSource.getConnection()) {
            user = DAOUtil.getEntity(connection, userMapper, GET_BY_EMAIL_QUERY, email);
        } catch (SQLException e) {
            log.error("Cannot get user by email", e);
            throw new DAOException("Cannot get user by email", e);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> users;
        try (Connection connection = dataSource.getConnection()) {
            users = DAOUtil.getEntities(connection, userMapper, GET_ALL_QUERY);
        } catch (SQLException e) {
            log.error("Cannot get all users", e);
            throw new DAOException("Cannot get all users", e);
        }
        return users;
    }

    @Override
    public Page<User> getAll(int page, int pageSize) throws DAOException {
        Page<User> users;
        try (Connection connection = dataSource.getConnection()) {
            users = DAOUtil.getEntitiesPage(connection, userMapper, GET_ALL_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_QUERY));
        } catch (SQLException e) {
            log.error("Cannot get all users", e);
            throw new DAOException("Cannot get all users", e);
        }
        return users;
    }

    @Override
    public void save(User user) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            save(user, connection);
        } catch (SQLException e) {
            log.error("Cannot save user", e);
            throw new DAOException("Cannot save user", e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            update(user, connection);
        } catch (SQLException e) {
            log.error("Cannot update user", e);
            throw new DAOException("Cannot update user", e);
        }
    }

    private void save(User user, Connection connection) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            fillStatement(user, statement);
            statement.executeUpdate();
        }
    }

    private void update(User user, Connection connection) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            fillStatement(user, statement);
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        }
    }

    private void fillStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getRole().name());
        statement.setTimestamp(5, Timestamp.valueOf(user.getRegistrationDate()));
    }
}
