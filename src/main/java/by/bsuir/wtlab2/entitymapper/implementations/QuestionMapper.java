package by.bsuir.wtlab2.entitymapper.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.TopicDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.Topic;
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
public final class QuestionMapper implements EntityMapper<Question> {

    private final TopicDAO topicDAO;
    private final UserDAO userDAO;

    public Question toEntity(ResultSet resultSet) throws SQLException, DAOException {
        int topicId = resultSet.getInt("topic_id");
        long authorId = resultSet.getLong("author_id");

        Optional<Topic> topic = topicDAO.getById(topicId);
        Optional<User> author = userDAO.getById(authorId);
        return Question.builder()
                .id(resultSet.getLong("id"))
                .text(resultSet.getString("text"))
                .title(resultSet.getString("title"))
                .topic(topic.orElseThrow(() -> new DAOException("Failed to get topic by id")))
                .author(author.orElseThrow(() -> new DAOException("Failed to get user by id")))
                .viewsCount(resultSet.getInt("views"))
                .answersCount(resultSet.getInt("answers"))
                .askTime(resultSet.getTimestamp("asked_time").toLocalDateTime())
                .lastUpdateTime(resultSet.getTimestamp("last_update_time").toLocalDateTime())
                .build();
    }

    public List<Question> toEntityList(ResultSet resultSet) throws SQLException, DAOException {
        List<Question> questions = new ArrayList<>();
        while(resultSet.next()) {
            questions.add(toEntity(resultSet));
        }
        return questions;
    }

    public Page<Question> toEntityPage(ResultSet resultSet, int page, int pageSize, int total) throws SQLException, DAOException {
        return Page.<Question>builder()
                .content(toEntityList(resultSet))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalElements(total)
                .build();
    }
}
