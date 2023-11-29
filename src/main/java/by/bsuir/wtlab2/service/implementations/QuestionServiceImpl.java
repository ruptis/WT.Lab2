package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.QuestionDAO;
import by.bsuir.wtlab2.dao.TopicDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.dao.ViewDAO;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.utils.Page;
import by.bsuir.wtlab2.utils.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDAO questionDAO;
    private final TopicDAO topicDAO;
    private final UserDAO userDAO;
    private final ViewDAO viewDAO;

    @Override
    public void addQuestion(String title, String text, long authorId, long topicId) throws ServiceException {
        Optional<Topic> optionalTopic = getTopic(topicId);
        if (optionalTopic.isEmpty()) {
            log.error("No such topic");
            throw new ServiceException("No such topic");
        }
        Topic topic = optionalTopic.get();

        Optional<User> optionalUser = ServiceUtil.getUser(authorId, userDAO);
        if (optionalUser.isEmpty()) {
            log.error("No such user");
            throw new ServiceException("No such user");
        }

        Question question = buildQuestion(title, text, authorId, topic);
        try {
            questionDAO.save(question);
        } catch (DAOException e) {
            log.error("Failed to add question", e);
            throw new ServiceException("Failed to add question", e);
        }
    }

    @Override
    public boolean deleteQuestion(long id) throws ServiceException {
        boolean deleted;

        Optional<Question> question = getQuestion(id);
        if (question.isEmpty())
            return false;

        Optional<Topic> optionalTopic = getTopic(question.get().getTopic().getId());
        if (optionalTopic.isEmpty())
            return false;

        Optional<User> optionalUser = ServiceUtil.getUser(question.get().getAuthor().getId(), userDAO);
        if (optionalUser.isEmpty())
            return false;

        try {
            deleted = questionDAO.delete(id);
        } catch (DAOException e) {
            log.error("Failed to delete question", e);
            throw new ServiceException("Failed to delete question", e);
        }

        return deleted;
    }

    @Override
    public Optional<Question> updateQuestion(long id, String title, String text, long topicId) throws ServiceException {
        Optional<Question> question = getQuestion(id);
        question.ifPresent(q -> {
            q.setTitle(title);
            q.setText(text);
            q.setTopic(Topic.builder().id(topicId).build());
            q.setLastUpdateTime(LocalDateTime.now());
        });
        try {
            questionDAO.update(question.orElseThrow());
            return question;
        } catch (DAOException e) {
            log.error("Failed to update question", e);
            throw new ServiceException("Failed to update question", e);
        }
    }

    @Override
    public Optional<Question> getQuestion(long id) throws ServiceException {
        try {
            return questionDAO.getById(id);
        } catch (DAOException e) {
            log.error("Failed to get question by id", e);
            throw new ServiceException("Failed to get question by id", e);
        }
    }

    @Override
    public Page<Question> getAllQuestions(int page, int pageSize) throws ServiceException {
        try {
            return questionDAO.getAll(page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all questions", e);
            throw new ServiceException("Failed to get all questions", e);
        }
    }

    @Override
    public List<Question> getAllQuestions() throws ServiceException {
        try {
            return questionDAO.getAll();
        } catch (DAOException e) {
            log.error("Failed to get all questions", e);
            throw new ServiceException("Failed to get all questions", e);
        }
    }

    @Override
    public Page<Question> getAllQuestionsByTopic(long topicId, int page, int pageSize) throws ServiceException {
        try {
            return questionDAO.getAllByTopicId(topicId, page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all questions by topic", e);
            throw new ServiceException("Failed to get all questions by topic", e);
        }
    }

    @Override
    public List<Question> getAllQuestionsByTopic(long topicId) throws ServiceException {
        try {
            return questionDAO.getAllByTopicId(topicId);
        } catch (DAOException e) {
            log.error("Failed to get all questions by topic", e);
            throw new ServiceException("Failed to get all questions by topic", e);
        }
    }

    @Override
    public Page<Question> getAllQuestionsByAuthor(long authorId, int page, int pageSize) throws ServiceException {
        try {
            return questionDAO.getAllByAuthorId(authorId, page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all questions by author", e);
            throw new ServiceException("Failed to get all questions by author", e);
        }
    }

    @Override
    public List<Question> getAllQuestionsByAuthor(long authorId) throws ServiceException {
        try {
            return questionDAO.getAllByAuthorId(authorId);
        } catch (DAOException e) {
            log.error("Failed to get all questions by author", e);
            throw new ServiceException("Failed to get all questions by author", e);
        }
    }

    @Override
    public void addView(long questionId, long userId) throws ServiceException {
        Optional<Question> optionalQuestion = getQuestion(questionId);
        if (optionalQuestion.isEmpty())
            return;
        saveView(questionId, userId);
    }

    private void saveView(long id, long userId) throws ServiceException {
        try {
            if (viewDAO.exists(id, userId))
                return;
        } catch (DAOException e) {
            log.error("Failed to check if view exists", e);
            throw new ServiceException("Failed to check if view exists", e);
        }
        try {
            viewDAO.save(id, userId);
        } catch (DAOException e) {
            log.error("Failed to add view", e);
            throw new ServiceException("Failed to add view", e);
        }
    }

    private Optional<Topic> getTopic(long topicId) throws ServiceException {
        Optional<Topic> optionalTopic;
        try {
            optionalTopic = topicDAO.getById(topicId);
        } catch (DAOException e) {
            log.error("Failed to get topic by id", e);
            throw new ServiceException("Failed to get topic by id", e);
        }
        return optionalTopic;
    }

    private static Question buildQuestion(String title, String text, long authorId, Topic topic) {
        return Question.builder()
                .title(title)
                .text(text)
                .author(User.builder().id(authorId).build())
                .topic(topic)
                .askTime(LocalDateTime.now())
                .answersCount(0)
                .viewsCount(0)
                .lastUpdateTime(LocalDateTime.now())
                .build();
    }
}
