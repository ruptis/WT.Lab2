package by.bsuir.wtlab2.entitymapper.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.AnswerDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.entity.Vote;
import by.bsuir.wtlab2.entitymapper.EntityMapper;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class VoteMapper implements EntityMapper<Vote> {

    private final AnswerDAO answerDAO;
    private final UserDAO userDAO;

    @Override
    public Vote toEntity(ResultSet resultSet) throws SQLException, DAOException {
        long answerId = resultSet.getLong("answer_id");
        long userId = resultSet.getLong("user_id");

        Optional<Answer> answer = answerDAO.getById(answerId);
        Optional<User> user = userDAO.getById(userId);
        return Vote.builder()
                .answer(answer.orElseThrow(() -> new DAOException("Failed to get answer by id")))
                .user(user.orElseThrow(() -> new DAOException("Failed to get user by id")))
                .change(resultSet.getInt("reputation_change"))
                .build();
    }

    @Override
    public List<Vote> toEntityList(ResultSet resultSet) throws SQLException, DAOException {
        List<Vote> votes = new ArrayList<>();
        while(resultSet.next()) {
            votes.add(toEntity(resultSet));
        }
        return votes;
    }

    @Override
    public Page<Vote> toEntityPage(ResultSet resultSet, int page, int pageSize, int total) throws SQLException, DAOException {
        return Page.<Vote>builder()
                .content(toEntityList(resultSet))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalElements(total)
                .build();
    }
}
