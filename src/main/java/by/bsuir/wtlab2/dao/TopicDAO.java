package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface TopicDAO {
    Optional<Topic> getById(long id) throws DAOException;
    List<Topic> getAll() throws DAOException;
    Page<Topic> getAll(int page, int pageSize) throws DAOException;
    void save(Topic topic) throws DAOException;
    void update(Topic topic) throws DAOException;
    boolean delete(long id) throws DAOException;
}
