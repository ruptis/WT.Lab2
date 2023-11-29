package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@Slf4j
@CommandSecurity(roles = {USER, ADMIN})
@WebCommand(mapping = "/question", method = HttpMethod.POST)
@RequiredArgsConstructor
public class NewQuestionCommand implements Command {

    private final QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        long authorId = RequestUtil.getAuthorId(request);

        String title = request.getParameter("title");
        String text = request.getParameter("text");
        long topicId = Long.parseLong(request.getParameter("topic"));

        try {
           questionService.addQuestion(title, text, authorId, topicId);
        } catch (ServiceException e) {
            log.error("Failed to add question", e);
            throw new CommandException("Failed to add question", e);
        }

        return new RedirectResult("/topic?id=" + topicId);
    }
}
