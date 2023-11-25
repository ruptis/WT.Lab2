package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.JspResult;
import by.bsuir.wtlab2.entity.User;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.UserService;
import by.bsuir.wtlab2.utils.Page;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.Collections;

import static by.bsuir.wtlab2.constants.Role.ADMIN;

@CommandSecurity(roles = {ADMIN})
@WebCommand(mapping = "/users", method = HttpMethod.GET)
@RequiredArgsConstructor
public class UsersPageCommand implements Command {

    private static final int PAGE_SIZE = 10;

    private final UserService userService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));

        Page<User> usersPage = userService.getAllUsers(page, PAGE_SIZE);

        return new JspResult("users", Collections.singletonMap("users", usersPage));
    }
}
