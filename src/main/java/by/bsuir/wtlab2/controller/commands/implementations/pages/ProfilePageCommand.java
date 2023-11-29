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
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.AnswerService;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.service.UserService;
import by.bsuir.wtlab2.utils.Page;
import by.bsuir.wtlab2.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@Slf4j
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
        int pageQuestions = RequestUtil.getPage(request, "Questions");
        int pageAnswers = RequestUtil.getPage(request, "Answers");
        long id = getId(request);

        User user = getUser(id);

        Page<Question> questions;
        try {
            questions = questionService.getAllQuestionsByAuthor(user.getId(), pageQuestions, PAGE_SIZE);
        } catch (ServiceException e) {
            log.error("Failed to get questions", e);
            throw new CommandException("Failed to get questions", e);
        }

        Page<Answer> answers;
        try {
            answers = answerService.getAllAnswersByAuthor(user.getId(), pageAnswers, PAGE_SIZE);
        } catch (ServiceException e) {
            log.error("Failed to get answers", e);
            throw new CommandException("Failed to get answers", e);
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("tab", tab);
        attributes.put("user", user);
        attributes.put("questions", questions);
        attributes.put("answers", answers);
        return new JspResult("profile", attributes);
    }

    private User getUser(long id) throws CommandException {
        Optional<User> optionalUser;
        try {
            optionalUser = userService.getUserById(id);
        } catch (ServiceException e) {
            log.error("Failed to get user", e);
            throw new CommandException("Failed to get user", e);
        }
        if (optionalUser.isEmpty()) throw new CommandException("No user with id " + id);
        return optionalUser.get();
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
