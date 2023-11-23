package by.bsuir.wtlab2.controller.commands.implementations;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.controller.commands.results.RedirectResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

@Slf4j
@WebCommand(mapping = "/register", method = HttpMethod.POST)
@RequiredArgsConstructor
public class RegisterCommand implements Command {

    private final AuthenticationService authenticationService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AuthenticationService.RegistrationResult result = authenticationService.register(email, username, password);
        if (result.isSuccess()) {
            log.debug("Registered as {}.", username);
            return new RedirectResult("/login");
        }

        log.debug("Failed to register as {}.", username);
        return new JspResult("register", Collections.singletonMap("error", result.getMessage()));
    }
}
