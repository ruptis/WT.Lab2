package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.entity.Question;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.QuestionService;
import by.bsuir.wtlab2.service.TopicService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.bsuir.wtlab2.constants.Role.BANNED;

@CommandSecurity(except = {BANNED})
@WebCommand(mapping = "/")
@RequiredArgsConstructor
public final class HomePageCommand implements Command {

    private final TopicService topicService;
    private final QuestionService questionService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int page = 1;
        int recordsPerPage = 5;

        List<Topic> topics = topicService.getAllTopics(page, recordsPerPage);
        List<Question> questions = questionService.getAllQuestions(page, recordsPerPage);

        Map<String, Object> attributes = new HashMap<>();
        attributes.put("topics", topics);
        attributes.put("questions", questions);
        return new JspResult("index", attributes);
    }
}
