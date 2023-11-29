package by.bsuir.wtlab2.entitymapper.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.constants.Role;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.entitymapper.EntityMapper;
import by.bsuir.wtlab2.utils.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public final class UserMapper implements EntityMapper<User> {

    public User toEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .role(Role.valueOf(resultSet.getString("role")))
                .reputation(resultSet.getInt("reputation"))
                .questionsCount(resultSet.getInt("questions"))
                .answersCount(resultSet.getInt("answers"))
                .registrationDate(resultSet.getTimestamp("registration_datetime").toLocalDateTime())
                .build();
    }

    public List<User> toEntityList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            users.add(toEntity(resultSet));
        }
        return users;
    }

    public Page<User> toEntityPage(ResultSet resultSet, int page, int pageSize, int total) throws SQLException {
        return Page.<User>builder()
                .content(toEntityList(resultSet))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalElements(total)
                .build();
    }
}
