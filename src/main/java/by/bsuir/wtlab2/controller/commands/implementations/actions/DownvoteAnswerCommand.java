package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.OkResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.exception.ServiceException;
import by.bsuir.wtlab2.service.VoteService;
import by.bsuir.wtlab2.utils.RequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@Slf4j
@CommandSecurity(roles = {USER, ADMIN})
@WebCommand(mapping = "/answer/downvote", method = HttpMethod.POST)
@RequiredArgsConstructor
public class DownvoteAnswerCommand implements Command {

    private final VoteService voteService;
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        long authorId = RequestUtil.getAuthorId(request);
        long answerId = Long.parseLong(request.getParameter("id"));

        try {
            voteService.downvote(answerId, authorId);
        } catch (ServiceException e) {
            log.error("Failed to downvote answer", e);
            throw new CommandException("Failed to downvote answer", e);
        }

        log.info("Downvoted answer with id {}", answerId);
        return new OkResult();
    }
}
