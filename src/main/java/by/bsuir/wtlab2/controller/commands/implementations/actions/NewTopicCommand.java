package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.StatusCodeResult;
import by.bsuir.wtlab2.entity.Topic;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.TopicService;
import by.bsuir.wtlab2.utils.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static by.bsuir.wtlab2.constants.Role.ADMIN;

@Slf4j
@CommandSecurity(roles = {ADMIN})
@WebCommand(mapping = "/topic", method = HttpMethod.POST)
@RequiredArgsConstructor
public class NewTopicCommand implements Command {

    private final TopicService topicService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        long authorId = SessionUtil.getAuthorId(request);
        String title = request.getParameter("title");
        log.trace("New topic: title = {}, authorId = {}", title, authorId);

        Optional<Topic> newTopic = topicService.addTopic(title, authorId);
        log.debug("New topic: {}", newTopic);

        return newTopic.isPresent()
                ? new RedirectResult("/topics")
                : new StatusCodeResult(500);
    }
}
