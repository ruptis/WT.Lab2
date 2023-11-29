package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.AnswerDAO;
import by.bsuir.wtlab2.dao.QuestionDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.AnswerService;
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
public class AnswerServiceImpl implements AnswerService {

    private final AnswerDAO answerDAO;
    private final QuestionDAO questionDAO;
    private final UserDAO userDAO;

    @Override
    public void addAnswer(String text, long authorId, long questionId) throws ServiceException {
        Question question = getQuestion(questionId);
        Optional<User> optionalUser = ServiceUtil.getUser(authorId, userDAO);
        if (optionalUser.isEmpty())
            return;
        User user = optionalUser.get();
        Answer answer = buildAnswer(text, user, question);
        try {
            answerDAO.save(answer);
        } catch (DAOException e) {
            log.error("Failed to add answer", e);
            throw new ServiceException("Failed to add answer", e);
        }
    }

    @Override
    public Optional<Answer> getAnswer(long id) throws ServiceException {
        try {
            return answerDAO.getById(id);
        } catch (DAOException e) {
            log.error("Failed to get answer by id", e);
            throw new ServiceException("Failed to get answer by id", e);
        }
    }

    @Override
    public Page<Answer> getAllAnswers(int page, int pageSize) throws ServiceException {
        try {
            return answerDAO.getAll(page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all answers", e);
            throw new ServiceException("Failed to get all answers", e);
        }
    }

    @Override
    public List<Answer> getAllAnswers() throws ServiceException {
        try {
            return answerDAO.getAll();
        } catch (DAOException e) {
            log.error("Failed to get all answers", e);
            throw new ServiceException("Failed to get all answers", e);
        }
    }

    @Override
    public Page<Answer> getAllAnswersByQuestion(long questionId, int page, int pageSize) throws ServiceException {
        try {
            return answerDAO.getAllByQuestionId(questionId, page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all answers by question", e);
            throw new ServiceException("Failed to get all answers by question", e);
        }
    }

    @Override
    public List<Answer> getAllAnswersByQuestion(long questionId) throws ServiceException {
        try {
            return answerDAO.getAllByQuestionId(questionId);
        } catch (DAOException e) {
            log.error("Failed to get all answers by question", e);
            throw new ServiceException("Failed to get all answers by question", e);
        }
    }

    @Override
    public Page<Answer> getAllAnswersByAuthor(long authorId, int page, int pageSize) throws ServiceException {
        try {
            return answerDAO.getAllByAuthorId(authorId, page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all answers by author", e);
            throw new ServiceException("Failed to get all answers by author", e);
        }
    }

    @Override
    public List<Answer> getAllAnswersByAuthor(long authorId) throws ServiceException {
        try {
            return answerDAO.getAllByAuthorId(authorId);
        } catch (DAOException e) {
            log.error("Failed to get all answers by author", e);
            throw new ServiceException("Failed to get all answers by author", e);
        }
    }

    private Question getQuestion(long questionId) throws ServiceException {
        Optional<Question> optionalQuestion;
        try {
            optionalQuestion = questionDAO.getById(questionId);
        } catch (DAOException e) {
            log.error("Failed to get question by id", e);
            throw new ServiceException("Failed to get question by id", e);
        }
        if (optionalQuestion.isEmpty()) {
            log.error("Question with id {} not found", questionId);
            throw new ServiceException("Question with id " + questionId + " not found");
        }
        return optionalQuestion.get();
    }

    private static Answer buildAnswer(String text, User user, Question question) {
        return Answer.builder()
                .text(text)
                .author(user)
                .question(question)
                .reputation(0)
                .answerTime(LocalDateTime.now())
                .build();
    }
}
