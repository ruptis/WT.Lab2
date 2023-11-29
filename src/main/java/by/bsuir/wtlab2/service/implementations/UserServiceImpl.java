package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.UserService;
import by.bsuir.wtlab2.utils.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.BANNED;
import static by.bsuir.wtlab2.constants.Role.USER;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Override
    public void addUser(String username, String password, String email) throws ServiceException {
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(USER)
                .registrationDate(LocalDateTime.now())
                .build();
        try {
            userDAO.save(user);
        } catch (DAOException e) {
            log.error("Failed to add user", e);
            throw new ServiceException("Failed to add user", e);
        }
    }

    @Override
    public Optional<User> getUserById(long id) throws ServiceException {
        try {
            return userDAO.getById(id);
        } catch (DAOException e) {
            log.error("Failed to get user by id", e);
            throw new ServiceException("Failed to get user by id", e);
        }
    }

    @Override
    public Optional<User> getUserByUsername(String username) throws ServiceException {
        try {
            return userDAO.getByUsername(username);
        } catch (DAOException e) {
            log.error("Failed to get user by username", e);
            throw new ServiceException("Failed to get user by username", e);
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) throws ServiceException {
        try {
            return userDAO.getByEmail(email);
        } catch (DAOException e) {
            log.error("Failed to get user by email", e);
            throw new ServiceException("Failed to get user by email", e);
        }
    }

    @Override
    public Page<User> getAllUsers(int page, int pageSize) throws ServiceException {
        try {
            return userDAO.getAll(page, pageSize);
        } catch (DAOException e) {
            log.error("Failed to get all users", e);
            throw new ServiceException("Failed to get all users", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            log.error("Failed to get all users", e);
            throw new ServiceException("Failed to get all users", e);
        }
    }

    @Override
    public void banUser(long id) throws ServiceException {
        User user = getUserById(id).orElseThrow(() -> new ServiceException("User not found"));
        user.setRole(BANNED);
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            log.error("Failed to ban user", e);
            throw new ServiceException("Failed to ban user", e);
        }
    }

    @Override
    public void unbanUser(long id) throws ServiceException {
        User user = getUserById(id).orElseThrow(() -> new ServiceException("User not found"));
        user.setRole(USER);
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            log.error("Failed to unban user", e);
            throw new ServiceException("Failed to unban user", e);
        }
    }
}
