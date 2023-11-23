package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.service.AuthenticationService;

import java.util.ArrayList;
import java.util.List;

import static by.bsuir.wtlab2.constants.Role.BANNED;
import static by.bsuir.wtlab2.constants.Role.USER;

@Singleton
public class AuthenticationServiceImpl implements AuthenticationService {
    private final List<User> users = new ArrayList<>();

    @Override
    public AuthenticationResult authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return AuthenticationResult.builder()
                            .success(true)
                            .userDetails(user)
                            .build();
                }
                return AuthenticationResult.builder()
                        .success(false)
                        .message("Invalid password.")
                        .build();
            }
        }
        return AuthenticationResult.builder()
                .success(false)
                .message("Invalid username.")
                .build();
    }

    @Override
    public RegistrationResult register(String email, String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return RegistrationResult.builder()
                        .success(false)
                        .message("Username already taken.")
                        .build();
            } else if (user.getEmail().equals(email)) {
                return RegistrationResult.builder()
                        .success(false)
                        .message("Email already taken.")
                        .build();
            }
        }
        users.add(User.builder()
                .id(users.size() + 1)
                .email(email)
                .username(username)
                .password(password)
                .role(username.equals("banneduser") ? BANNED : USER)
                .build());
        return RegistrationResult.builder()
                .success(true)
                .build();
    }
}
