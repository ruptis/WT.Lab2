package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Optional<Question> addQuestion(String title, String text, long authorId, long topicId);

    boolean deleteQuestion(long id);

    Optional<Question> updateQuestion(long id, String title, String text, long topicId);

    Optional<Question> getQuestion(long id);

    Page<Question> getAllQuestions(int page, int pageSize);

    List<Question> getAllQuestions();

    Page<Question> getAllQuestionsByTopic(long topicId, int page, int pageSize);

    List<Question> getAllQuestionsByTopic(long topicId);

    Page<Question> getAllQuestionsByAuthor(long authorId, int page, int pageSize);

    List<Question> getAllQuestionsByAuthor(long authorId);

    void addView(long id, long userId);
}
