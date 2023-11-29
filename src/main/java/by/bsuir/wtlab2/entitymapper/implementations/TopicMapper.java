package by.bsuir.wtlab2.entitymapper.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.UserDAO;
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
public class TopicMapper implements EntityMapper<Topic> {

    private final UserDAO userDAO;

    @Override
    public Topic toEntity(ResultSet resultSet) throws SQLException, DAOException {
        long creatorId = resultSet.getLong("creator_id");

        Optional<User> creator = userDAO.getById(creatorId);
        return Topic.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .author(creator.orElseThrow(() -> new DAOException("Failed to get user by id")))
                .questionsCount(resultSet.getInt("questions"))
                .creationTime(resultSet.getTimestamp("creation_time").toLocalDateTime())
                .build();
    }

    @Override
    public List<Topic> toEntityList(ResultSet resultSet) throws SQLException, DAOException {
        List<Topic> topics = new ArrayList<>();
        while(resultSet.next()) {
            topics.add(toEntity(resultSet));
        }
        return topics;
    }

    @Override
    public Page<Topic> toEntityPage(ResultSet resultSet, int page, int pageSize, int total) throws SQLException, DAOException {
        return Page.<Topic>builder()
                .content(toEntityList(resultSet))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalElements(total)
                .build();
    }
}
