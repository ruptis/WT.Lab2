package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.annotations.Singleton;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.entity.UserDetails;
import by.bsuir.wtlab2.exception.AuthenticationException;
import by.bsuir.wtlab2.exception.RegistrationException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.AuthenticationService;
import by.bsuir.wtlab2.service.PasswordHasher;
import by.bsuir.wtlab2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
@Singleton
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordHasher passwordHasher;
    private final UserService userService;

    @Override
    public UserDetails authenticate(String username, String password) throws AuthenticationException {
        Optional<User> userOptional;
        try {
            userOptional = userService.getUserByUsername(username);
        } catch (ServiceException e) {
            log.error("Cannot get user by username", e);
            throw new AuthenticationException("Cannot get user by username", e);
        }
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordHasher.check(password, user.getPassword())) {
                return user;
            }
            log.debug("Invalid password");
            throw new AuthenticationException("Invalid password.");
        }
        log.debug("User not found");
        throw new AuthenticationException("User not found.");
    }

    @Override
    public void register(String email, String username, String password) throws RegistrationException {
        try {
            if (userService.getUserByUsername(username).isPresent()) {
                throw new RegistrationException("Username already taken.");
            } else if (userService.getUserByEmail(email).isPresent()) {
                throw new RegistrationException("Email already taken.");
            }
        } catch (ServiceException e) {
            log.error("Cannot register user", e);
            throw new RegistrationException("Cannot register user", e);
        }

        String hashedPassword = passwordHasher.hash(password);
        try {
            userService.addUser(username, hashedPassword, email);
        } catch (ServiceException e) {
            log.error("Cannot register user", e);
            throw new RegistrationException("Cannot register user", e);
        }
    }
}
