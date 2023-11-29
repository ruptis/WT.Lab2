package by.bsuir.wtlab2.dao;

import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getById(long id) throws DAOException;
    Optional<User> getByUsername(String username) throws DAOException;
    Optional<User> getByEmail(String email) throws DAOException;
    List<User> getAll() throws DAOException;
    Page<User> getAll(int page, int pageSize) throws DAOException;
    void save(User user) throws DAOException;
    void update(User user) throws DAOException;
}
