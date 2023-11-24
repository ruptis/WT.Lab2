package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    void addQuestion(String title, String text, long authorId, long topicId);

    void deleteQuestion(long id);

    void updateQuestion(long id, String title, String text, long topicId);

    Optional<Question> getQuestion(long id);

    List<Question> getAllQuestions(int page, int pageSize);

    List<Question> getAllQuestionsByTopic(long topicId, int page, int pageSize);

    List<Question> getAllQuestionsByAuthor(long authorId, int page, int pageSize);

    void addView(long id, long userId);
}
