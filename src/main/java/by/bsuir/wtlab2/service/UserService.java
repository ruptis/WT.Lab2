package by.bsuir.wtlab2.service;

import by.bsuir.wtlab2.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(String username, String password, String email);

    Optional<User> getUserById(long id);

    Optional<User> getUserByUsername(String username);

    List<User> getAllUsers(int page, int pageSize);

    void banUser(long id);

    void unbanUser(long id);
}
