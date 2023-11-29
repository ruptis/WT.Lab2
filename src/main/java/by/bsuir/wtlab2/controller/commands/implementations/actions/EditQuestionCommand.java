package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.OkResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.StatusCodeResult;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@Slf4j
@CommandSecurity(roles = {USER, ADMIN})
@WebCommand(mapping = "/question", method = HttpMethod.PUT)
@RequiredArgsConstructor
public class EditQuestionCommand implements Command {

    private final QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int questionId = Integer.parseInt(request.getParameter("id"));
        int topicId = Integer.parseInt(request.getParameter("topicId"));
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        log.debug("Edit question: id = {}, title = {}, text = {}, topicId = {}", questionId, title, text, topicId);

        Optional<Question> updatedQuestion;
        try {
            updatedQuestion = questionService.updateQuestion(questionId, title, text, topicId);
        } catch (ServiceException e) {
            log.error("Failed to update question", e);
            throw new CommandException("Failed to update question", e);
        }

        log.debug("Updated question: {}", updatedQuestion);
        return updatedQuestion.isPresent()
                ? new OkResult()
                : new StatusCodeResult(500);
    }
}
