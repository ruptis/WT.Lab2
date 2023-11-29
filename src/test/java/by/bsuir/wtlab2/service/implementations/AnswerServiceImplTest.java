package by.bsuir.wtlab2.service.implementations;

import by.bsuir.wtlab2.dao.AnswerDAO;
import by.bsuir.wtlab2.dao.QuestionDAO;
import by.bsuir.wtlab2.dao.UserDAO;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.DAOException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.utils.Page;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerServiceImplTest {

    @Mock
    private AnswerDAO answerDAO;

    @Mock
    private QuestionDAO questionDAO;

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private AnswerServiceImpl answerService;

    @Test
    void testAddAnswer_Successful() throws ServiceException, DAOException {
        long authorId = 1L;
        long questionId = 2L;
        String text = "Test answer";
        User user = new User();
        user.setId(authorId);
        Question question = new Question();
        question.setId(questionId);

        when(userDAO.getById(authorId)).thenReturn(Optional.of(user));
        when(questionDAO.getById(questionId)).thenReturn(Optional.of(question));

        answerService.addAnswer(text, authorId, questionId);

        verify(answerDAO, times(1)).save(any(Answer.class));
    }

    @Test
    void testGetAnswerById_Successful() throws ServiceException, DAOException {
        long answerId = 1L;
        Answer expectedAnswer = new Answer();
        expectedAnswer.setId(answerId);

        when(answerDAO.getById(answerId)).thenReturn(Optional.of(expectedAnswer));

        Optional<Answer> result = answerService.getAnswer(answerId);

        assertTrue(result.isPresent());
        assertEquals(expectedAnswer, result.get());
    }

    @Test
    void testGetAnswerById_NotFound() throws ServiceException, DAOException {
        long answerId = 1L;

        when(answerDAO.getById(answerId)).thenReturn(Optional.empty());

        Optional<Answer> result = answerService.getAnswer(answerId);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllAnswers_Successful() throws ServiceException, DAOException {
        int page = 1;
        int pageSize = 10;
        List<Answer> expectedAnswers = Collections.singletonList(new Answer());

        when(answerDAO.getAll(page, pageSize)).thenReturn(Page.<Answer>builder().content(expectedAnswers).build());

        Page<Answer> result = answerService.getAllAnswers(page, pageSize);

        assertEquals(expectedAnswers, result.getContent());
    }

    @Test
    void testGetAllAnswersByQuestion_Successful() throws ServiceException, DAOException {
        long questionId = 1L;
        int page = 1;
        int pageSize = 10;
        List<Answer> expectedAnswers = Collections.singletonList(new Answer());

        when(answerDAO.getAllByQuestionId(questionId, page, pageSize)).thenReturn(Page.<Answer>builder().content(expectedAnswers).build());

        Page<Answer> result = answerService.getAllAnswersByQuestion(questionId, page, pageSize);

        assertEquals(expectedAnswers, result.getContent());
    }
}
