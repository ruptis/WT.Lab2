package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.OkResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.StatusCodeResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.TopicService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static by.bsuir.wtlab2.constants.Role.ADMIN;

@Slf4j
@CommandSecurity(roles = {ADMIN})
@WebCommand(mapping = "/topic", method = HttpMethod.DELETE)
@RequiredArgsConstructor
public class DeleteTopicCommand implements Command {

    private final TopicService topicService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int topicId = Integer.parseInt(request.getParameter("id"));

        boolean isDeleted;
        try {
            isDeleted = topicService.deleteTopic(topicId);
        } catch (ServiceException e) {
            log.error("Failed to delete topic", e);
            throw new CommandException("Failed to delete topic", e);
        }

        log.debug("Topic with id = {} was deleted: {}", topicId, isDeleted);
        return isDeleted
                ? new OkResult()
                : new StatusCodeResult(500);
    }
}
