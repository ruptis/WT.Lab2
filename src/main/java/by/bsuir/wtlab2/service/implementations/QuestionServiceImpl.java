package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.service.TopicService;
import by.bsuir.wtlab2.service.UserService;
import by.bsuir.wtlab2.utils.Page;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final List<Question> questions = new ArrayList<>();

    private final UserService userService;
    private final TopicService topicService;

    @Override
    public Optional<Question> addQuestion(String title, String text, long authorId, long topicId) {
        User author = userService.getUserById(authorId).orElseThrow();
        Topic topic = topicService.getTopic(topicId).orElseThrow();
        Question question = Question.builder()
                .id(questions.size() + 1)
                .title(title)
                .text(text)
                .author(author)
                .topic(topic)
                .askTime(LocalDateTime.now())
                .build();
        questions.add(question);
        return Optional.of(question);
    }

    @Override
    public boolean deleteQuestion(long id) {
        return false;
    }

    @Override
    public Optional<Question> updateQuestion(long id, String title, String text, long topicId) {
        return Optional.empty();
    }

    @Override
    public Optional<Question> getQuestion(long id) {
        return questions.stream()
                .filter(question -> question.getId() == id)
                .findFirst();
    }

    @Override
    public Page<Question> getAllQuestions(int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, questions.size());
        return Page.<Question>builder()
                .content(questions.subList(fromIndex, toIndex))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalPages((questions.size() + pageSize - 1) / pageSize)
                .totalElements(questions.size())
                .build();
    }

    @Override
    public List<Question> getAllQuestions() {
        return questions;
    }

    @Override
    public Page<Question> getAllQuestionsByTopic(long topicId, int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, questions.size());
        List<Question> filteredQuestions = questions.stream()
                .filter(question -> question.getTopic().getId() == topicId)
                .toList();
        return Page.<Question>builder()
                .content(filteredQuestions.subList(fromIndex, toIndex))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalPages((filteredQuestions.size() + pageSize - 1) / pageSize)
                .totalElements(filteredQuestions.size())
                .build();
    }

    @Override
    public List<Question> getAllQuestionsByTopic(long topicId) {
        return questions.stream()
                .filter(question -> question.getTopic().getId() == topicId)
                .toList();
    }

    @Override
    public Page<Question> getAllQuestionsByAuthor(long authorId, int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, questions.size());
        List<Question> filteredQuestions = questions.stream()
                .filter(question -> question.getAuthor().getId() == authorId)
                .toList();
        return Page.<Question>builder()
                .content(filteredQuestions.subList(fromIndex, toIndex))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalPages((filteredQuestions.size() + pageSize - 1) / pageSize)
                .totalElements(filteredQuestions.size())
                .build();
    }

    @Override
    public List<Question> getAllQuestionsByAuthor(long authorId) {
        return questions.stream()
                .filter(question -> question.getAuthor().getId() == authorId)
                .toList();
    }

    @Override
    public void addView(long id, long userId) {
        questions.stream()
                .filter(question -> question.getId() == id)
                .findFirst()
                .ifPresent(question -> question.setViewsCount(question.getViewsCount() + 1));
    }
}
