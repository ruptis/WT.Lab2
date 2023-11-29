package by.bsuir.wtlab2.utils;

import by.bsuir.wtlab2.dao.AnswerDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public final class ServiceUtil {
    private ServiceUtil() {
    }

    public static Optional<User> getUser(long authorId, UserDAO userDAO) throws ServiceException {
        Optional<User> optionalUser;
        try {
            optionalUser = userDAO.getById(authorId);
        } catch (DAOException e) {
            log.error("Failed to get user by id", e);
            throw new ServiceException("Failed to get user by id", e);
        }
        if (optionalUser.isEmpty()) {
            log.error("User with id {} not found", authorId);
            throw new ServiceException("User with id " + authorId + " not found");
        }
        return optionalUser;
    }


    public static Optional<Answer> getAnswer(long id, AnswerDAO answerDAO) throws ServiceException {
        Optional<Answer> optionalAnswer;
        try {
            optionalAnswer = answerDAO.getById(id);
        } catch (DAOException e) {
            log.error("Failed to get answer by id", e);
            throw new ServiceException("Failed to get answer by id", e);
        }
        if (optionalAnswer.isEmpty()) {
            log.error("Answer with id {} not found", id);
            throw new ServiceException("Answer with id " + id + " not found");
        }
        return optionalAnswer;
    }
}
