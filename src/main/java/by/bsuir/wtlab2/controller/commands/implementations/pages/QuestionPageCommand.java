package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.JspResult;
import by.bsuir.wtlab2.entity.Answer;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.Vote;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.AnswerService;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.service.VoteService;
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
@CommandSecurity(roles = {ADMIN, USER})
@WebCommand(mapping = "/question", method = HttpMethod.GET)
@RequiredArgsConstructor
public class QuestionPageCommand implements Command {

    private static final int PAGE_SIZE = 20;

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final VoteService voteService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int page = RequestUtil.getPage(request);
        long userId = RequestUtil.getAuthorId(request);
        int questionId = Integer.parseInt(request.getParameter("id"));

        Optional<Question> question;
        try {
            question = questionService.getQuestion(questionId);
        } catch (ServiceException e) {
            log.error("Failed to get question", e);
            throw new CommandException("Failed to get question", e);
        }
        if (question.isEmpty()) {
            log.error("Question not found");
            throw new CommandException("Question not found");
        }

        try {
            questionService.addView(questionId, userId);
        } catch (ServiceException e) {
            log.error("Failed to add view", e);
            throw new CommandException("Failed to add view", e);
        }

        Page<Answer> answers;
        try {
            answers = answerService.getAllAnswersByQuestion(questionId, page, PAGE_SIZE);
        } catch (ServiceException e) {
            log.error("Failed to get answers", e);
            throw new CommandException("Failed to get answers", e);
        }
        Map<Answer, Optional<Vote>> votes = new HashMap<>();
        for (Answer answer : answers.getContent()) {
            try {
                votes.put(answer, voteService.getVote(answer.getId(), userId));
            } catch (ServiceException e) {
                log.error("Failed to get vote", e);
                throw new CommandException("Failed to get vote", e);
            }
        }

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("question", question.get());
        attributes.put("answers", answers);
        attributes.put("votes", votes);
        return new JspResult("question", attributes);
    }
}
