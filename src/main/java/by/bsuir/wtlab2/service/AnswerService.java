package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    void addAnswer(String text, long authorId, long questionId) throws ServiceException;

    Optional<Answer> getAnswer(long id) throws ServiceException;

    Page<Answer> getAllAnswers(int page, int pageSize) throws ServiceException;

    List<Answer> getAllAnswers() throws ServiceException;

    Page<Answer> getAllAnswersByQuestion(long questionId, int page, int pageSize) throws ServiceException;

    List<Answer> getAllAnswersByQuestion(long questionId) throws ServiceException;

    Page<Answer> getAllAnswersByAuthor(long authorId, int page, int pageSize) throws ServiceException;

    List<Answer> getAllAnswersByAuthor(long authorId) throws ServiceException;
}
