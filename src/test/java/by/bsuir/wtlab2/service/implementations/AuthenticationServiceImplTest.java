package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.entity.UserDetails;
import by.bsuir.wtlab2.exception.AuthenticationException;
import by.bsuir.wtlab2.exception.RegistrationException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.PasswordHasher;
import by.bsuir.wtlab2.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {

    @Mock
    private PasswordHasher passwordHasher;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    void testAuthenticate_SuccessfulAuthentication() throws ServiceException {
        String username = "testUser";
        String password = "testPassword";
        String hashedPassword = "hashedPassword";

        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(hashedPassword);

        when(userService.getUserByUsername(username)).thenReturn(Optional.of(mockUser));
        when(passwordHasher.check(password, hashedPassword)).thenReturn(true);

        UserDetails userDetails = authenticationService.authenticate(username, password);

        assertNotNull(userDetails);
        assertEquals(mockUser, userDetails);
    }

    @Test
    void testAuthenticate_InvalidPassword() throws ServiceException {
        String username = "testUser";
        String password = "testPassword";
        String hashedPassword = "hashedPassword";

        User mockUser = new User();
        mockUser.setUsername(username);
        mockUser.setPassword(hashedPassword);

        when(userService.getUserByUsername(username)).thenReturn(Optional.of(mockUser));
        when(passwordHasher.check(password, hashedPassword)).thenReturn(false);

        assertThrows(AuthenticationException.class, () -> authenticationService.authenticate(username, password));
    }

    @Test
    void testAuthenticate_UserNotFound() throws ServiceException {
        String username = "nonExistentUser";
        String password = "testPassword";

        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());

        assertThrows(AuthenticationException.class, () -> authenticationService.authenticate(username, password));
    }

    @Test
    void testRegister_UsernameTaken() throws ServiceException {
        String email = "test@example.com";
        String username = "existingUser";
        String password = "testPassword";

        when(userService.getUserByUsername(username)).thenReturn(Optional.of(new User()));

        assertThrows(RegistrationException.class, () -> authenticationService.register(email, username, password));
    }

    @Test
    void testRegister_EmailTaken() throws ServiceException {
        String email = "existing@example.com";
        String username = "newUser";
        String password = "testPassword";

        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());
        when(userService.getUserByEmail(email)).thenReturn(Optional.of(new User()));

        assertThrows(RegistrationException.class, () -> authenticationService.register(email, username, password));
    }

    @Test
    void testRegister_SuccessfulRegistration() throws ServiceException {
        String email = "new@example.com";
        String username = "newUser";
        String password = "testPassword";

        when(userService.getUserByUsername(username)).thenReturn(Optional.empty());
        when(userService.getUserByEmail(email)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> authenticationService.register(email, username, password));
    }
}
