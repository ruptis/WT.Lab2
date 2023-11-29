package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    void addQuestion(String title, String text, long authorId, long topicId) throws ServiceException;

    boolean deleteQuestion(long id) throws ServiceException;

    Optional<Question> updateQuestion(long id, String title, String text, long topicId) throws ServiceException;

    Optional<Question> getQuestion(long id) throws ServiceException;

    Page<Question> getAllQuestions(int page, int pageSize) throws ServiceException;

    List<Question> getAllQuestions() throws ServiceException;

    Page<Question> getAllQuestionsByTopic(long topicId, int page, int pageSize) throws ServiceException;

    List<Question> getAllQuestionsByTopic(long topicId) throws ServiceException;

    Page<Question> getAllQuestionsByAuthor(long authorId, int page, int pageSize) throws ServiceException;

    List<Question> getAllQuestionsByAuthor(long authorId) throws ServiceException;

    void addView(long questionId, long userId) throws ServiceException;
}
