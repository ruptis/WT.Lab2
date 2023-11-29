package by.bsuir.wtlab2.entitymapper;

import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface EntityMapper<T> {
    T toEntity(ResultSet resultSet) throws SQLException, DAOException;
    List<T> toEntityList(ResultSet resultSet) throws SQLException, DAOException;
    Page<T> toEntityPage(ResultSet resultSet, int page, int pageSize, int total) throws SQLException, DAOException;
}
