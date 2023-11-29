package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testAddUser_Successful() throws ServiceException, DAOException {
        String username = "testUser";
        String password = "testPassword";
        String email = "test@example.com";

        userService.addUser(username, password, email);

        verify(userDAO, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById_Successful() throws ServiceException, DAOException {
        long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userDAO.getById(userId)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserById(userId);

        assertTrue(result.isPresent());
        assertEquals(userId, result.get().getId());
    }

    @Test
    void testGetUserById_UserNotFound() throws ServiceException, DAOException {
        long userId = 1L;

        when(userDAO.getById(userId)).thenReturn(Optional.empty());

        Optional<User> result = userService.getUserById(userId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetUserByUsername_Successful() throws ServiceException, DAOException {
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        when(userDAO.getByUsername(username)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUserByUsername(username);

        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
    }
}