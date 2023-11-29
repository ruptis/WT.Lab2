package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entity.Vote;
import by.bsuir.wtlab2.exception.DAOException;

import java.util.Optional;

public interface VoteDAO {
    Optional<Vote> get(long answerId, long userId) throws DAOException;
    void save(Vote vote) throws DAOException;
    boolean delete(Vote vote) throws DAOException;
}
