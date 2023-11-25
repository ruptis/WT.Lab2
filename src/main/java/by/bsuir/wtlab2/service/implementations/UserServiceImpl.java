package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.service.UserService;
import by.bsuir.wtlab2.utils.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.*;

@Singleton
public class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    public UserServiceImpl() {
        users.add(User.builder()
                .id(1)
                .email("user@gmail.com")
                .username("user")
                .password("123456")
                .role(USER)
                .registrationDate(LocalDateTime.now())
                .build());
        users.add(User.builder()
                .id(2)
                .email("admin@gmail.com")
                .username("admin")
                .password("123456")
                .role(ADMIN)
                .registrationDate(LocalDateTime.now())
                .build());
    }

    @Override
    public void addUser(String username, String password, String email) {
        users.add(User.builder()
                .id(users.size() + 1)
                .email(email)
                .username(username)
                .password(password)
                .role(USER)
                .registrationDate(LocalDateTime.now())
                .build());
    }

    @Override
    public Optional<User> getUserById(long id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return users.stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    @Override
    public Page<User> getAllUsers(int page, int pageSize) {
        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, users.size());
        return Page.<User>builder()
                .content(users.subList(fromIndex, toIndex))
                .pageNumber(page)
                .pageSize(pageSize)
                .totalPages((users.size() + pageSize - 1) / pageSize)
                .totalElements(users.size())
                .build();
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void banUser(long id) {
        User user = getUserById(id).orElseThrow();
        user.setRole(BANNED);
    }

    @Override
    public void unbanUser(long id) {
        User user = getUserById(id).orElseThrow();
        user.setRole(USER);
    }
}
