package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.bsuir.wtlab2.constants.SessionAttributes.USER_DETAILS;

@WebCommand(mapping = "/logout")
public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        if (session.getAttribute(USER_DETAILS.getValue()) != null)
            session.removeAttribute(USER_DETAILS.getValue());
        return new RedirectResult("/");
    }
}
