package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.Vote;
import by.bsuir.wtlab2.exception.ServiceException;

import java.util.Optional;

public interface VoteService {
    void upvote(long answerId, long userId) throws ServiceException;

    void downvote(long answerId, long userId) throws ServiceException;

    void unvote(long answerId, long userId) throws ServiceException;

    Optional<Vote> getVote(long answerId, long userId) throws ServiceException;
}
