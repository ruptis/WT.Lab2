package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.OkResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.StatusCodeResult;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.TopicService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.ADMIN;

@Slf4j
@CommandSecurity(roles = {ADMIN})
@WebCommand(mapping = "/topic", method = HttpMethod.PUT)
@RequiredArgsConstructor
public class EditTopicCommand implements Command {

    private final TopicService topicService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int topicId = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        log.trace("Edit topic: id = {}, title = {}", topicId, title);

        Optional<Topic> updatedTopic = topicService.updateTopic(topicId, title);
        log.debug("Updated topic: {}", updatedTopic);

        return updatedTopic.isPresent()
                ? new OkResult()
                : new StatusCodeResult(500);
    }
}
