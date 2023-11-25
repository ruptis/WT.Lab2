package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.service.AnswerService;
import by.bsuir.wtlab2.utils.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class AnswerServiceImpl implements AnswerService {

    private final List<Answer> answers = new ArrayList<>();

    public AnswerServiceImpl() {
        for (int i = 0; i < 100; i++)
            answers.add(Answer.builder()
                    .id(i)
                    .text("Answer text " + i)
                    .build());
    }

    @Override
    public void addAnswer(String text, long authorId, long questionId) {

    }

    @Override
    public void deleteAnswer(long id) {

    }

    @Override
    public void updateAnswer(long id, String text) {

    }

    @Override
    public Optional<Answer> getAnswer(long id) {
        return Optional.empty();
    }

    @Override
    public Page<Answer> getAllAnswers(int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, answers.size());
        return Page.<Answer>builder()
                .content(answers.subList(fromIndex, toIndex))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalPages((answers.size() + pageSize - 1) / pageSize)
                .totalElements(answers.size())
                .build();
    }

    @Override
    public List<Answer> getAllAnswers() {
        return answers;
    }

    @Override
    public Page<Answer> getAllAnswersByQuestion(long questionId, int page, int pageSize) {
        return getAllAnswers(page, pageSize);
    }

    @Override
    public List<Answer> getAllAnswersByQuestion(long questionId) {
        return answers;
    }

    @Override
    public Page<Answer> getAllAnswersByAuthor(long authorId, int page, int pageSize) {
        return getAllAnswers(page, pageSize);
    }

    @Override
    public List<Answer> getAllAnswersByAuthor(long authorId) {
        return null;
    }

    @Override
    public void upvote(long id, long userId) {

    }

    @Override
    public void downvote(long id, long userId) {

    }

    @Override
    public void unvote(long id, long userId) {

    }
}
