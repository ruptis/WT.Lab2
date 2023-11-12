package by.bsuir.wtlab2.controller.commands.implementations;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.constants.Roles;
import by.bsuir.wtlab2.constants.SessionAttributes;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.controller.commands.results.RedirectResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebCommand(mapping = "/login", method = HttpMethod.POST)
public class LogInCommand implements Command {
    private final Logger logger = LoggerFactory.getLogger(LogInCommand.class);
    private static final String[] logins = new String[]{"admin", "user"};

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        logger.debug("Login: {}", login);

        if (login == null || password == null) {
            return new JspResult("login");
        }

        for (String l : logins) {
            if (l.equals(login)) {
                request.getSession().setAttribute("login", login);
                request.getSession().setAttribute(SessionAttributes.ROLE.getValue(),
                        login.equals("admin") ? Roles.ADMIN.getName() : Roles.USER.getName());
                logger.info("User {} logged in.", login);
                return new RedirectResult("/");
            }
        }

        logger.info("Failed to log in as {}.", login);
        return new JspResult("login");
    }
}
