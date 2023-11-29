package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.exception.DAOException;

public interface ViewDAO {
    boolean exists(long questionId, long userId) throws DAOException;
    void save(long questionId, long userId) throws DAOException;
}
