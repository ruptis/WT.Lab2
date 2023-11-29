package by.bsuir.wtlab2.dao.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.TopicDAO;
import by.bsuir.wtlab2.dao.connection.DataSource;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.entitymapper.implementations.TopicMapper;
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
public class TopicDAOImpl implements TopicDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM topic_view WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM topic_view ORDER BY creation_time";
    private static final String GET_ALL_PAGED_QUERY = "SELECT * FROM topic_view ORDER BY creation_time LIMIT ? OFFSET ?";
    private static final String SAVE_QUERY = "INSERT INTO topic (name, creator_id, creation_time) VALUES (?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE topic SET name = ?, creator_id = ?, creation_time = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM topic WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM topic";

    private final TopicMapper topicMapper;
    private final DataSource dataSource;

    @Override
    public Optional<Topic> getById(long id) throws DAOException {
        Optional<Topic> topic;
        try (Connection connection = dataSource.getConnection()) {
            topic = DAOUtil.getEntity(connection, topicMapper, GET_BY_ID_QUERY, id);
        } catch (SQLException e) {
            log.error("Failed to get topic by id", e);
            throw new DAOException("Failed to get topic by id", e);
        }
        return topic;
    }

    @Override
    public List<Topic> getAll() throws DAOException {
        List<Topic> topics;
        try (Connection connection = dataSource.getConnection()) {
            topics = DAOUtil.getEntities(connection, topicMapper, GET_ALL_QUERY);
        } catch (SQLException e) {
            log.error("Failed to get all topics", e);
            throw new DAOException("Failed to get all topics", e);
        }
        return topics;
    }

    @Override
    public Page<Topic> getAll(int page, int pageSize) throws DAOException {
        Page<Topic> topicPage;
        try (Connection connection = dataSource.getConnection()) {
            topicPage = DAOUtil.getEntitiesPage(connection, topicMapper, GET_ALL_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_QUERY));
        } catch (SQLException e) {
            log.error("Failed to get all topics", e);
            throw new DAOException("Failed to get all topics", e);
        }
        return topicPage;
    }

    @Override
    public void save(Topic topic) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            save(topic, connection);
        } catch (SQLException e) {
            log.error("Failed to save topic", e);
            throw new DAOException("Failed to save topic", e);
        }
    }

    @Override
    public void update(Topic topic) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            update(topic, connection);
        } catch (SQLException e) {
            log.error("Failed to update topic", e);
            throw new DAOException("Failed to update topic", e);
        }
    }

    @Override
    public boolean delete(long id) throws DAOException {
        boolean deleted;
        try (Connection connection = dataSource.getConnection()) {
            deleted = delete(id, connection);
        } catch (SQLException e) {
            log.error("Failed to delete topic", e);
            throw new DAOException("Failed to delete topic", e);
        }
        return deleted;
    }

    private boolean delete(long id, Connection connection) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private void save(Topic topic, Connection connection) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            fillStatement(topic, statement);
            statement.executeUpdate();
        }
    }

    private static void fillStatement(Topic topic, PreparedStatement statement) throws SQLException {
        statement.setString(1, topic.getName());
        statement.setLong(2, topic.getAuthor().getId());
        statement.setTimestamp(3, Timestamp.valueOf(topic.getCreationTime()));
    }

    private void update(Topic topic, Connection connection) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            fillStatement(topic, statement);
            statement.setLong(4, topic.getId());
            statement.executeUpdate();
        }
    }
}
