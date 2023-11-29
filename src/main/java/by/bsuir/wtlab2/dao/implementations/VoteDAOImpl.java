package by.bsuir.wtlab2.dao.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.VoteDAO;
import by.bsuir.wtlab2.dao.connection.DataSource;
import by.bsuir.wtlab2.entity.Vote;
import by.bsuir.wtlab2.entitymapper.implementations.VoteMapper;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.DAOUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class VoteDAOImpl implements VoteDAO {

    private static final String GET_QUERY = "SELECT * FROM reputation_history WHERE answer_id = ? AND user_id = ?";
    private static final String SAVE_QUERY = "INSERT INTO reputation_history (answer_id, user_id, reputation_change, change_time) VALUES (?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM reputation_history WHERE answer_id = ? AND user_id = ?";

    private final VoteMapper voteMapper;
    private final DataSource dataSource;

    @Override
    public Optional<Vote> get(long answerId, long userId) throws DAOException {
        Optional<Vote> vote;
        try (Connection connection = dataSource.getConnection()) {
            vote = DAOUtil.getEntity(connection, voteMapper, GET_QUERY, answerId, userId);
        } catch (SQLException e) {
            log.error("Failed to get vote by id", e);
            throw new DAOException("Failed to get vote by id", e);
        }
        return vote;
    }

    @Override
    public void save(Vote vote) throws DAOException {
        try (Connection connection = dataSource.getConnection()) {
            if (get(vote.getAnswer().getId(), vote.getUser().getId()).isEmpty()) {
                save(vote, connection);
            }
        } catch (SQLException e) {
            log.error("Cannot save vote", e);
            throw new DAOException("Cannot save vote", e);
        }
    }

    @Override
    public boolean delete(Vote vote) throws DAOException {
        boolean deleted;
        try (Connection connection = dataSource.getConnection()) {
            deleted = delete(vote, connection);
        } catch (SQLException e) {
            log.error("Cannot delete vote", e);
            throw new DAOException("Cannot delete vote", e);
        }
        return deleted;
    }

    private boolean delete(Vote vote, Connection connection) throws SQLException {
        boolean deleted;
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, vote.getAnswer().getId());
            statement.setLong(2, vote.getUser().getId());
            deleted = statement.executeUpdate() > 0;
        }
        return deleted;
    }

    private static void save(Vote vote, Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_QUERY)) {
            statement.setLong(1, vote.getAnswer().getId());
            statement.setLong(2, vote.getUser().getId());
            statement.setInt(3, vote.getChange());
            statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            statement.executeUpdate();
        }
    }
}
