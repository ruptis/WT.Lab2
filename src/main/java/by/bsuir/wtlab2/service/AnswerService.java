package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    void addAnswer(String text, long authorId, long questionId);

    void deleteAnswer(long id);

    void updateAnswer(long id, String text);

    Optional<Answer> getAnswer(long id);

    Page<Answer> getAllAnswers(int page, int pageSize);

    List<Answer> getAllAnswers();

    Page<Answer> getAllAnswersByQuestion(long questionId, int page, int pageSize);

    List<Answer> getAllAnswersByQuestion(long questionId);

    Page<Answer> getAllAnswersByAuthor(long authorId, int page, int pageSize);

    List<Answer> getAllAnswersByAuthor(long authorId);

    void upvote(long id, long userId);

    void downvote(long id, long userId);

    void unvote(long id, long userId);
}
