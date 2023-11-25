package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.utils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@CommandSecurity(roles = {USER, ADMIN})
@WebCommand(mapping = "/question", method = HttpMethod.POST)
@RequiredArgsConstructor
public class NewQuestionCommand implements Command {

    private final QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        long authorId = SessionUtil.getAuthorId(request);

        String title = request.getParameter("title");
        String text = request.getParameter("text");
        long topicId = Long.parseLong(request.getParameter("topic"));

        Optional<Question> newQuestion = questionService.addQuestion(title, text, authorId, topicId);

        if (newQuestion.isPresent()) {
            return new RedirectResult("/question?id=" + newQuestion.get().getId());
        } else {
            throw new CommandException("Failed to add question");
        }
    }
}
