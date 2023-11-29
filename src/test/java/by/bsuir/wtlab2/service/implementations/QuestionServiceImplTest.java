package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.dao.QuestionDAO;
import by.bsuir.wtlab2.dao.TopicDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionServiceImplTest {

    @Mock
    private QuestionDAO questionDAO;

    @Mock
    private TopicDAO topicDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Test
    void testAddQuestion_Successful() throws ServiceException, DAOException {
        long authorId = 1L;
        long topicId = 2L;
        String title = "Test question";
        String text = "Test question text";
        User user = new User();
        user.setId(authorId);
        Topic topic = new Topic();
        topic.setId(topicId);

        when(userDAO.getById(authorId)).thenReturn(Optional.of(user));
        when(topicDAO.getById(topicId)).thenReturn(Optional.of(topic));

        questionService.addQuestion(title, text, authorId, topicId);

        verify(questionDAO, times(1)).save(any(Question.class));
    }

    @Test
    void testAddQuestion_TopicNotFound() throws DAOException {
        long authorId = 1L;
        long topicId = 2L;
        String title = "Test question";
        String text = "Test question text";
        User user = new User();
        user.setId(authorId);

        when(topicDAO.getById(topicId)).thenReturn(Optional.empty());

        assertThrows(ServiceException.class, () -> questionService.addQuestion(title, text, authorId, topicId));
        verify(questionDAO, never()).save(any(Question.class));
    }

    @Test
    void testAddQuestion_UserNotFound() throws DAOException {
        long authorId = 1L;
        long topicId = 2L;
        String title = "Test question";
        String text = "Test question text";

        assertThrows(ServiceException.class, () -> questionService.addQuestion(title, text, authorId, topicId));
        verify(questionDAO, never()).save(any(Question.class));
    }
}
