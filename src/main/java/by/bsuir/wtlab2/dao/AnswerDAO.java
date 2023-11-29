package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface AnswerDAO {
    Optional<Answer> getById(long id) throws DAOException;
    List<Answer> getAll() throws DAOException;
    Page<Answer> getAll(int page, int pageSize) throws DAOException;
    List<Answer> getAllByQuestionId(long questionId) throws DAOException;
    Page<Answer> getAllByQuestionId(long questionId, int page, int pageSize) throws DAOException;
    List<Answer> getAllByAuthorId(long authorId) throws DAOException;
    Page<Answer> getAllByAuthorId(long authorId, int page, int pageSize) throws DAOException;
    void save(Answer answer) throws DAOException;
    void update(Answer answer) throws DAOException;
    boolean delete(long id) throws DAOException;
}
