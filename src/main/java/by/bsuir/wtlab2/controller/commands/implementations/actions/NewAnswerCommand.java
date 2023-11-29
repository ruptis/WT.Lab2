package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.AnswerService;
import by.bsuir.wtlab2.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@Slf4j
@CommandSecurity(roles = {ADMIN, USER})
@WebCommand(mapping = "/answer", method = HttpMethod.POST)
@RequiredArgsConstructor
public class NewAnswerCommand implements Command {

    private final AnswerService answerService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        long authorId = RequestUtil.getAuthorId(request);
        long questionId = Long.parseLong(request.getParameter("id"));
        String text = request.getParameter("text");

        try {
            answerService.addAnswer(text, authorId, questionId);
        } catch (Exception e) {
            log.error("Failed to save answer", e);
            throw new CommandException("Failed to save answer", e);
        }

        log.info("Saved answer with text {} for question with id {}", text, questionId);
        return new RedirectResult("/question?id=" + questionId);
    }
}
