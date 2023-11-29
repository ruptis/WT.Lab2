package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.utils.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String username, String password, String email) throws ServiceException;

    Optional<User> getUserById(long id) throws ServiceException;

    Optional<User> getUserByUsername(String username) throws ServiceException;

    Optional<User> getUserByEmail(String email) throws ServiceException;

    Page<User> getAllUsers(int page, int pageSize) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    void banUser(long id) throws ServiceException;

    void unbanUser(long id) throws ServiceException;
}
