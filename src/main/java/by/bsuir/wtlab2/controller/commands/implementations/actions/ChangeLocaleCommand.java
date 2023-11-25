package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import static by.bsuir.wtlab2.constants.SessionAttributes.LOCALE;

@Slf4j
@WebCommand(mapping = "/locale", method = HttpMethod.POST)
public final class ChangeLocaleCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String locale = request.getParameter("locale");
        String referer = request.getHeader("referer");
        log.debug("Changing locale to {}", locale);
        request.getSession().setAttribute(LOCALE.getValue(), locale);
        return new RedirectResult(referer);
    }
}
