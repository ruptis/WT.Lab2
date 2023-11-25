package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.service.AuthenticationService;
import by.bsuir.wtlab2.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Singleton
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;

    @Override
    public AuthenticationResult authenticate(String username, String password) {
        Optional<User> userOptional = userService.getUserByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
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
        return AuthenticationResult.builder()
                .success(false)
                .message("Invalid username.")
                .build();
    }

    @Override
    public RegistrationResult register(String email, String username, String password) {
        if (userService.getUserByUsername(username).isPresent()) {
            return RegistrationResult.builder()
                    .success(false)
                    .message("Username already taken.")
                    .build();
        } else if (userService.getUserByEmail(email).isPresent()) {
            return RegistrationResult.builder()
                    .success(false)
                    .message("Email already taken.")
                    .build();
        }
        userService.addUser(username, password, email);
        return RegistrationResult.builder()
                .success(true)
                .build();
    }
}
