package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerService {
    void addAnswer(String text, long authorId, long questionId);

    void deleteAnswer(long id);

    void updateAnswer(long id, String text);

    Optional<Answer> getAnswer(long id);

    List<Answer> getAllAnswers(int page, int pageSize);

    List<Answer> getAllAnswersByQuestion(long questionId, int page, int pageSize);

    List<Answer> getAllAnswersByAuthor(long authorId, int page, int pageSize);

    void upvote(long id, long userId);

    void downvote(long id, long userId);

    void unvote(long id, long userId);
}
