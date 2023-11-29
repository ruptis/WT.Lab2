package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface QuestionDAO {
    Optional<Question> getById(long id) throws DAOException;
    List<Question> getAll() throws DAOException;
    Page<Question> getAll(int page, int pageSize) throws DAOException;
    List<Question> getAllByTopicId(long topicId) throws DAOException;
    Page<Question> getAllByTopicId(long topicId, int page, int pageSize) throws DAOException;
    List<Question> getAllByAuthorId(long authorId) throws DAOException;
    Page<Question> getAllByAuthorId(long authorId, int page, int pageSize) throws DAOException;
    void save(Question question) throws DAOException;
    void update(Question question) throws DAOException;
    boolean delete(long id) throws DAOException;
}
