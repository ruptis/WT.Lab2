package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.JspResult;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.service.TopicService;
import by.bsuir.wtlab2.utils.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static by.bsuir.wtlab2.constants.Role.BANNED;

@CommandSecurity(except = {BANNED})
@WebCommand(mapping = "/")
@RequiredArgsConstructor
public final class HomePageCommand implements Command {

    private static final int PAGE_SIZE = 5;
    private static final int TOPICS_SIZE = 10;

    private final TopicService topicService;
    private final QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

        Page<Topic> topics = topicService.getAllTopics(1, TOPICS_SIZE);
        Page<Question> questions = questionService.getAllQuestions(page, PAGE_SIZE);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("topics", topics.getContent());
        attributes.put("questionsPage", questions);
        return new JspResult("index", attributes);
    }
}
