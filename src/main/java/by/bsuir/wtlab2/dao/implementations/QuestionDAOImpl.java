package by.bsuir.wtlab2.dao.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.QuestionDAO;
import by.bsuir.wtlab2.dao.connection.DataSource;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entitymapper.implementations.QuestionMapper;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.DAOUtil;
import by.bsuir.wtlab2.utils.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class QuestionDAOImpl implements QuestionDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM question_view WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM question_view ORDER BY last_update_time";
    private static final String GET_ALL_PAGED_QUERY = "SELECT * FROM question_view ORDER BY last_update_time LIMIT ? OFFSET ?";
    private static final String GET_ALL_BY_TOPIC_ID_QUERY = "SELECT * FROM question_view WHERE topic_id = ? ORDER BY last_update_time";
    private static final String GET_ALL_BY_TOPIC_ID_PAGED_QUERY = "SELECT * FROM question_view WHERE topic_id = ? ORDER BY last_update_time LIMIT ? OFFSET ?";
    private static final String GET_ALL_BY_AUTHOR_ID_QUERY = "SELECT * FROM question_view WHERE author_id = ? ORDER BY last_update_time";
    private static final String GET_ALL_BY_AUTHOR_ID_PAGED_QUERY = "SELECT * FROM question_view WHERE author_id = ? ORDER BY last_update_time LIMIT ? OFFSET ?";
    private static final String SAVE_QUERY = "INSERT INTO question (topic_id, author_id, title, text, asked_time, last_update_time) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE question SET topic_id = ?, author_id = ?, title = ?, text = ?, asked_time = ?, last_update_time = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM question WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM question";
    private static final String COUNT_BY_TOPIC_ID_QUERY = "SELECT COUNT(*) FROM question WHERE topic_id = ?";
    private static final String COUNT_BY_AUTHOR_ID_QUERY = "SELECT COUNT(*) FROM question WHERE author_id = ?";

    private final QuestionMapper questionMapper;
    private final DataSource dataSource;

    @Override
    public Optional<Question> getById(long id) throws DAOException {
        Optional<Question> question;
        try (Connection connection = dataSource.getConnection()) {
            question = DAOUtil.getEntity(connection, questionMapper, GET_BY_ID_QUERY, id);
        } catch (SQLException e) {
            log.error("Failed to get question by id", e);
            throw new DAOException("Failed to get question by id", e);
        }
        return question;
    }

    @Override
    public List<Question> getAll() throws DAOException {
        List<Question> questions;
        try (Connection connection = dataSource.getConnection()) {
            questions = DAOUtil.getEntities(connection, questionMapper, GET_ALL_QUERY);
        } catch (SQLException e) {
            log.error("Failed to get all questions", e);
            throw new DAOException("Failed to get all questions", e);
        }
        return questions;
    }

    @Override
    public Page<Question> getAll(int page, int pageSize) throws DAOException {
        Page<Question> questions;
        try (Connection connection = dataSource.getConnection()) {
            questions = DAOUtil.getEntitiesPage(connection, questionMapper, GET_ALL_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_QUERY));
        } catch (SQLException e) {
            log.error("Failed to get all questions", e);
            throw new DAOException("Failed to get all questions", e);
        }
        return questions;
    }

    @Override
    public List<Question> getAllByTopicId(long topicId) throws DAOException {
        List<Question> questions;
        try (Connection connection = dataSource.getConnection()) {
            questions = DAOUtil.getEntities(connection, questionMapper, GET_ALL_BY_TOPIC_ID_QUERY, topicId);
        } catch (SQLException e) {
            log.error("Failed to get all questions by topic id", e);
            throw new DAOException("Failed to get all questions by topic id", e);
        }
        return questions;
    }

    @Override
    public Page<Question> getAllByTopicId(long topicId, int page, int pageSize) throws DAOException {
        Page<Question> questions;
        try (Connection connection = dataSource.getConnection()) {
            questions = DAOUtil.getEntitiesPage(connection, questionMapper, GET_ALL_BY_TOPIC_ID_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_BY_TOPIC_ID_QUERY, topicId), topicId);
        } catch (SQLException e) {
            log.error("Failed to get all questions by topic id", e);
            throw new DAOException("Failed to get all questions by topic id", e);
        }
        return questions;
    }

    @Override
    public List<Question> getAllByAuthorId(long authorId) throws DAOException {
        List<Question> questions;
        try (Connection connection = dataSource.getConnection()) {
            questions = DAOUtil.getEntities(connection, questionMapper, GET_ALL_BY_AUTHOR_ID_QUERY, authorId);
        } catch (SQLException e) {
            log.error("Failed to get all questions by author id", e);
            throw new DAOException("Failed to get all questions by author id", e);
        }
        return questions;
    }

    @Override
    public Page<Question> getAllByAuthorId(long authorId, int page, int pageSize) throws DAOException {
        Page<Question> questions;
        try (Connection connection = dataSource.getConnection()) {
            questions = DAOUtil.getEntitiesPage(connection, questionMapper, GET_ALL_BY_AUTHOR_ID_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_BY_AUTHOR_ID_QUERY, authorId), authorId);
        } catch (SQLException e) {
            log.error("Failed to get all questions by author id", e);
            throw new DAOException("Failed to get all questions by author id", e);
        }
        return questions;
    }

    @Override
    public void save(Question question) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            save(question, connection);
        } catch (SQLException e) {
            log.error("Failed to save question", e);
            throw new DAOException("Failed to save question", e);
        }
    }

    @Override
    public void update(Question question) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            update(question, connection);
        } catch (SQLException e) {
            log.error("Failed to update question", e);
            throw new DAOException("Failed to update question", e);
        }
    }

    @Override
    public boolean delete(long id) throws DAOException {
        boolean deleted;
        try (Connection connection = dataSource.getConnection()) {
            deleted = delete(id, connection);
        } catch (SQLException e) {
            log.error("Failed to delete question", e);
            throw new DAOException("Failed to delete question", e);
        }
        return deleted;
    }

    private void save(Question question, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            fillStatement(question, statement);
            statement.executeUpdate();
        }
    }

    private void update(Question question, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            fillStatement(question, statement);
            statement.setLong(7, question.getId());
            statement.executeUpdate();
        }
    }

    private void fillStatement(Question question, PreparedStatement statement) throws SQLException {
        statement.setLong(1, question.getTopic().getId());
        statement.setLong(2, question.getAuthor().getId());
        statement.setString(3, question.getTitle());
        statement.setString(4, question.getText());
        statement.setTimestamp(5, java.sql.Timestamp.valueOf(question.getAskTime()));
        statement.setTimestamp(6, java.sql.Timestamp.valueOf(question.getLastUpdateTime()));
    }

    private boolean delete(long id, Connection connection) throws SQLException {
        boolean deleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            deleted = statement.executeUpdate() > 0;
        }
        return deleted;
    }
}
