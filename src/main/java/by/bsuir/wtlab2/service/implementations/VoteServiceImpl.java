package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.AnswerDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.dao.VoteDAO;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.entity.Vote;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.VoteService;
import by.bsuir.wtlab2.utils.ServiceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteDAO voteDAO;
    private final AnswerDAO answerDAO;
    private final UserDAO userDAO;

    @Override
    public void upvote(long answerId, long userId) throws ServiceException {
        Answer answer = ServiceUtil.getAnswer(answerId, answerDAO).orElseThrow(() -> new ServiceException("Answer not found"));
        User user = ServiceUtil.getUser(userId, userDAO).orElseThrow(() -> new ServiceException("User not found"));
        Optional<Vote> optionalVote = getVote(answerId, userId);

        if (optionalVote.isPresent() && optionalVote.get().getChange() == 1) {
            log.error("User {} already upvoted answer {}", userId, answerId);
            throw new ServiceException("User " + userId + " already upvoted answer " + answerId);
        }

        if (optionalVote.isPresent()) {
            unvote(answerId, userId);
        }

        Vote vote = buildVote(answer, user, 1);
        try {
            voteDAO.save(vote);
        } catch (DAOException e) {
            log.error("Failed to save vote", e);
            throw new ServiceException("Failed to save vote", e);
        }
    }

    @Override
    public void downvote(long answerId, long userId) throws ServiceException {
        Answer answer = ServiceUtil.getAnswer(answerId, answerDAO).orElseThrow(() -> new ServiceException("Answer not found"));
        User user = ServiceUtil.getUser(userId, userDAO).orElseThrow(() -> new ServiceException("User not found"));

        Optional<Vote> optionalVote = getVote(answerId, userId);
        if (optionalVote.isPresent() && optionalVote.get().getChange() == -1) {
            log.error("User {} already downvoted answer {}", userId, answerId);
            throw new ServiceException("User " + userId + " already downvoted answer " + answerId);
        }

        if (optionalVote.isPresent()) {
            unvote(answerId, userId);
        }

        Vote vote = buildVote(answer, user, -1);
        try {
            voteDAO.save(vote);
        } catch (DAOException e) {
            log.error("Failed to save vote", e);
            throw new ServiceException("Failed to save vote", e);
        }
    }

    @Override
    public void unvote(long answerId, long userId) throws ServiceException {
        Answer answer = ServiceUtil.getAnswer(answerId, answerDAO).orElseThrow(() -> new ServiceException("Answer not found"));
        User user = ServiceUtil.getUser(userId, userDAO).orElseThrow(() -> new ServiceException("User not found"));

        Vote vote = buildVote(answer, user, 0);

        try {
            voteDAO.delete(vote);
        } catch (DAOException e) {
            log.error("Failed to delete vote", e);
            throw new ServiceException("Failed to delete vote", e);
        }
    }

    @Override
    public Optional<Vote> getVote(long answerId, long userId) throws ServiceException {
        Optional<Vote> optionalVote;
        try {
            optionalVote = voteDAO.get(answerId, userId);
        } catch (DAOException e) {
            log.error("Failed to get vote by answer id and user id", e);
            throw new ServiceException("Failed to get vote by answer id and user id", e);
        }
        return optionalVote;
    }

    private static Vote buildVote(Answer answer, User user, int change) {
        return Vote.builder()
                .answer(answer)
                .user(user)
                .change(change)
                .build();
    }
}
