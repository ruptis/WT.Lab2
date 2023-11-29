package by.bsuir.wtlab2.entitymapper.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.QuestionDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.User;
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
public final class AnswerMapper implements EntityMapper<Answer> {

    private final QuestionDAO questionDAO;
    private final UserDAO userDAO;

    @Override
    public Answer toEntity(ResultSet resultSet) throws SQLException, DAOException {
        long questionId = resultSet.getLong("question_id");
        long authorId = resultSet.getLong("author_id");

        Optional<Question> question = questionDAO.getById(questionId);
        Optional<User> author = userDAO.getById(authorId);
        return Answer.builder()
                .id(resultSet.getLong("id"))
                .text(resultSet.getString("text"))
                .question(question.orElseThrow(() -> new DAOException("Failed to get question by id")))
                .author(author.orElseThrow(() -> new DAOException("Failed to get user by id")))
                .reputation(resultSet.getInt("reputation"))
                .answerTime(resultSet.getTimestamp("answered_time").toLocalDateTime())
                .build();
    }

    @Override
    public List<Answer> toEntityList(ResultSet resultSet) throws SQLException, DAOException {
        List<Answer> answers = new ArrayList<>();
        while(resultSet.next()) {
            answers.add(toEntity(resultSet));
        }
        return answers;
    }

    @Override
    public Page<Answer> toEntityPage(ResultSet resultSet, int page, int pageSize, int total) throws SQLException, DAOException {
        return Page.<Answer>builder()
                .content(toEntityList(resultSet))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalElements(total)
                .build();
    }
}
