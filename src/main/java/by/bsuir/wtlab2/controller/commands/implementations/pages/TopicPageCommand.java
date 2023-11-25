package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
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
@WebCommand(mapping = "/topic", method = HttpMethod.GET)
@RequiredArgsConstructor
public class TopicPageCommand implements Command {

    private static final int PAGE_SIZE = 10;

    private final QuestionService questionService;
    private final TopicService topicService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int page = getPage(request);
        long id = Long.parseLong(request.getParameter("id"));

        Topic topic = topicService.getTopic(id).orElseThrow();
        Page<Topic> topics = topicService.getAllTopics(page, PAGE_SIZE);
        Page<Question> questions = questionService.getAllQuestionsByTopic(id, page, PAGE_SIZE);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("topic", topic);
        attributes.put("topics", topics.getContent());
        attributes.put("questions", questions);
        return new JspResult("topic", attributes);
    }

    private static int getPage(HttpServletRequest request) {
        String page = request.getParameter("page");
        if (page == null)
            return 1;
        return Integer.parseInt(page);
    }
}
