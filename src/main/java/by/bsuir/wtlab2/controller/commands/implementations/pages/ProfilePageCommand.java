package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.JspResult;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.AnswerService;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.service.UserService;
import by.bsuir.wtlab2.utils.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@CommandSecurity(roles = {USER, ADMIN})
@WebCommand(mapping = "/profile")
@RequiredArgsConstructor
public final class ProfilePageCommand implements Command {

    private static final String DEFAULT_TAB = "questions";
    private static final int PAGE_SIZE = 5;

    private final UserService userService;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String tab = getTab(request);
        int pageQuestions = getPage(request, "Questions");
        int pageAnswers = getPage(request, "Answers");
        long id = getId(request);

        User user = getUser(id);
        Page<Question> questions = questionService.getAllQuestionsByAuthor(user.getId(), pageQuestions, PAGE_SIZE);
        Page<Answer> answers = answerService.getAllAnswersByAuthor(user.getId(), pageAnswers, PAGE_SIZE);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("tab", tab);
        attributes.put("user", user);
        attributes.put("questions", questions);
        attributes.put("answers", answers);
        return new JspResult("profile", attributes);
    }

    private User getUser(long id) throws CommandException {
        Optional<User> optionalUser = userService.getUserById(id);
        if (optionalUser.isEmpty()) throw new CommandException("No user with id " + id);
        return optionalUser.get();
    }

    private static int getPage(HttpServletRequest request, String id) {
        String stringPage = request.getParameter("page" + id);
        return stringPage == null ? 1 : Integer.parseInt(stringPage);
    }

    private static long getId(HttpServletRequest request) throws CommandException {
        String stringId = request.getParameter("id");
        if (stringId == null) throw new CommandException("No id parameter");
        return Long.parseLong(stringId);
    }

    private static String getTab(HttpServletRequest request) {
        String tab = request.getParameter("tab");
        if (tab == null) tab = DEFAULT_TAB;
        return tab;
    }
}
