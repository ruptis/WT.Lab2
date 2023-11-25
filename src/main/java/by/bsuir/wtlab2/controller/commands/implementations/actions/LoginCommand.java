package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.JspResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.entity.UserDetails;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

import static by.bsuir.wtlab2.constants.SessionAttributes.USER_DETAILS;

@Slf4j
@WebCommand(mapping = "/login", method = HttpMethod.POST)
@RequiredArgsConstructor
public final class LoginCommand implements Command {

    private final AuthenticationService authenticationService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        log.debug("Login: {}", username);

        AuthenticationService.AuthenticationResult result = authenticationService.authenticate(username, password);
        if (result.isSuccess()) {
            UserDetails userDetails = result.getUserDetails();
            request.getSession().setAttribute(USER_DETAILS.getValue(), userDetails);
            log.debug("Logged in as {}.", username);
            return new RedirectResult("/");
        }

        log.debug("Failed to log in as {}.", username);
        return new JspResult("login", Collections.singletonMap("error", result.getMessage()));
    }
}
