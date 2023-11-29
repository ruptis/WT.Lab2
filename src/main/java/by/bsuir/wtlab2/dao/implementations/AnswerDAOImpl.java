package by.bsuir.wtlab2.dao.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.AnswerDAO;
import by.bsuir.wtlab2.dao.connection.DataSource;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entitymapper.implementations.AnswerMapper;
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
public class AnswerDAOImpl implements AnswerDAO {

    private static final String GET_BY_ID_QUERY = "SELECT * FROM answer_view WHERE id = ?";
    private static final String GET_ALL_QUERY = "SELECT * FROM answer_view ORDER BY answered_time";
    private static final String GET_ALL_PAGED_QUERY = "SELECT * FROM answer_view ORDER BY answered_time LIMIT ? OFFSET ?";
    private static final String GET_ALL_BY_QUESTION_ID_QUERY = "SELECT * FROM answer_view WHERE question_id = ? ORDER BY answered_time";
    private static final String GET_ALL_BY_QUESTION_ID_PAGED_QUERY = "SELECT * FROM answer_view WHERE question_id = ? ORDER BY answered_time LIMIT ? OFFSET ?";
    private static final String GET_ALL_BY_AUTHOR_ID_QUERY = "SELECT * FROM answer_view WHERE author_id = ? ORDER BY answered_time";
    private static final String GET_ALL_BY_AUTHOR_ID_PAGED_QUERY = "SELECT * FROM answer_view WHERE author_id = ? ORDER BY answered_time LIMIT ? OFFSET ?";
    private static final String SAVE_QUERY = "INSERT INTO answer (question_id, author_id, text, answered_time) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE answer SET question_id = ?, author_id = ?, text = ?, answered_time = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM answer WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM answer";
    private static final String COUNT_BY_QUESTION_ID_QUERY = "SELECT COUNT(*) FROM answer WHERE question_id = ?";
    private static final String COUNT_BY_AUTHOR_ID_QUERY = "SELECT COUNT(*) FROM answer WHERE author_id = ?";

    private final AnswerMapper answerMapper;
    private final DataSource dataSource;

    @Override
    public Optional<Answer> getById(long id) throws DAOException {
        Optional<Answer> answer;
        try (Connection connection = dataSource.getConnection()) {
            answer = DAOUtil.getEntity(connection, answerMapper, GET_BY_ID_QUERY, id);
        } catch (SQLException e) {
            log.error("Cannot get answer by id", e);
            throw new DAOException("Cannot get answer by id", e);
        }
        return answer;
    }

    @Override
    public List<Answer> getAll() throws DAOException {
        List<Answer> answers;
        try (Connection connection = dataSource.getConnection()) {
            answers = DAOUtil.getEntities(connection, answerMapper, GET_ALL_QUERY);
        } catch (SQLException e) {
            log.error("Cannot get all answers", e);
            throw new DAOException("Cannot get all answers", e);
        }
        return answers;
    }

    @Override
    public Page<Answer> getAll(int page, int pageSize) throws DAOException {
        Page<Answer> answers;
        try (Connection connection = dataSource.getConnection()) {
            answers = DAOUtil.getEntitiesPage(connection, answerMapper, GET_ALL_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_QUERY));
        } catch (SQLException e) {
            log.error("Cannot get all answers", e);
            throw new DAOException("Cannot get all answers", e);
        }
        return answers;
    }

    @Override
    public List<Answer> getAllByQuestionId(long questionId) throws DAOException {
        List<Answer> answers;
        try (Connection connection = dataSource.getConnection()) {
            answers = DAOUtil.getEntities(connection, answerMapper, GET_ALL_BY_QUESTION_ID_QUERY, questionId);
        } catch (SQLException e) {
            log.error("Cannot get all answers by question id", e);
            throw new DAOException("Cannot get all answers by question id", e);
        }
        return answers;
    }

    @Override
    public Page<Answer> getAllByQuestionId(long questionId, int page, int pageSize) throws DAOException {
        Page<Answer> answers;
        try (Connection connection = dataSource.getConnection()) {
            answers = DAOUtil.getEntitiesPage(connection, answerMapper, GET_ALL_BY_QUESTION_ID_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_BY_QUESTION_ID_QUERY, questionId), questionId);
        } catch (SQLException e) {
            log.error("Cannot get all answers by question id", e);
            throw new DAOException("Cannot get all answers by question id", e);
        }
        return answers;
    }

    @Override
    public List<Answer> getAllByAuthorId(long authorId) throws DAOException {
        List<Answer> answers;
        try (Connection connection = dataSource.getConnection()) {
            answers = DAOUtil.getEntities(connection, answerMapper, GET_ALL_BY_AUTHOR_ID_QUERY, authorId);
        } catch (SQLException e) {
            log.error("Cannot get all answers by author id", e);
            throw new DAOException("Cannot get all answers by author id", e);
        }
        return answers;
    }

    @Override
    public Page<Answer> getAllByAuthorId(long authorId, int page, int pageSize) throws DAOException {
        Page<Answer> answers;
        try (Connection connection = dataSource.getConnection()) {
            answers = DAOUtil.getEntitiesPage(connection, answerMapper, GET_ALL_BY_AUTHOR_ID_PAGED_QUERY, page, pageSize, DAOUtil.count(COUNT_BY_AUTHOR_ID_QUERY, authorId), authorId);
        } catch (SQLException e) {
            log.error("Cannot get all answers by author id", e);
            throw new DAOException("Cannot get all answers by author id", e);
        }
        return answers;
    }

    @Override
    public void save(Answer answer) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            save(answer, connection);
        } catch (SQLException e) {
            log.error("Cannot save answer", e);
            throw new DAOException("Cannot save answer", e);
        }
    }

    @Override
    public void update(Answer answer) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            update(answer, connection);
        } catch (SQLException e) {
            log.error("Cannot update answer", e);
            throw new DAOException("Cannot update answer", e);
        }
    }

    @Override
    public boolean delete(long id) throws DAOException {
        boolean deleted;
        try (Connection connection = dataSource.getConnection()) {
            deleted = delete(id, connection);
        } catch (SQLException e) {
            log.error("Cannot delete answer", e);
            throw new DAOException("Cannot delete answer", e);
        }
        return deleted;
    }

    private boolean delete(long id, Connection connection) throws SQLException {
        boolean deleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, id);
            deleted = statement.executeUpdate() > 0;
        }
        return deleted;
    }

    private void save(Answer answer, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            fillStatement(answer, statement);
            statement.executeUpdate();
        }
    }

    private static void fillStatement(Answer answer, PreparedStatement statement) throws SQLException {
        statement.setLong(1, answer.getQuestion().getId());
        statement.setLong(2, answer.getAuthor().getId());
        statement.setString(3, answer.getText());
        statement.setTimestamp(4, Timestamp.valueOf(answer.getAnswerTime()));
    }

    private void update(Answer answer, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            fillStatement(answer, statement);
            statement.setLong(5, answer.getId());
            statement.executeUpdate();
        }
    }
}
