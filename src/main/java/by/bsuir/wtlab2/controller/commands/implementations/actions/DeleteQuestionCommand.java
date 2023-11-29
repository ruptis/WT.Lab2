package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.OkResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.StatusCodeResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.QuestionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@Slf4j
@CommandSecurity(roles = {USER, ADMIN})
@WebCommand(mapping = "/question", method = HttpMethod.DELETE)
@RequiredArgsConstructor
public class DeleteQuestionCommand implements Command {

    private final QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int questionId = Integer.parseInt(request.getParameter("id"));

        boolean isDeleted;
        try {
            isDeleted = questionService.deleteQuestion(questionId);
        } catch (ServiceException e) {
            log.error("Failed to delete question", e);
            throw new CommandException("Failed to delete question", e);
        }

        return isDeleted
                ? new OkResult()
                : new StatusCodeResult(500);
    }
}
