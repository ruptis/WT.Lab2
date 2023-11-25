package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.JspResult;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.AnswerService;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.utils.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@CommandSecurity(roles = {ADMIN, USER})
@WebCommand(mapping = "/question", method = HttpMethod.GET)
@RequiredArgsConstructor
public class QuestionPageCommand implements Command {

    private static final int PAGE_SIZE = 20;

    private final QuestionService questionService;
    private final AnswerService answerService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        int questionId = Integer.parseInt(request.getParameter("id"));

        Optional<Question> question = questionService.getQuestion(questionId);
        if (question.isEmpty())
            throw new CommandException("Question not found");
        questionService.addView(questionId, 1);

        Page<Answer> answers = answerService.getAllAnswersByQuestion(questionId, page, PAGE_SIZE);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("question", question.get());
        attributes.put("answers", answers);
        return new JspResult("question", attributes);
    }
}
