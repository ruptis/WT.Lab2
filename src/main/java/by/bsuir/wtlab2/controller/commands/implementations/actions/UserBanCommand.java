package by.bsuir.wtlab2.controller.commands.implementations.actions;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.controller.commands.implementations.results.RedirectResult;
import by.bsuir.wtlab2.exception.CommandException;
import by.bsuir.wtlab2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import static by.bsuir.wtlab2.constants.Role.ADMIN;

@CommandSecurity(roles = {ADMIN})
@WebCommand(mapping = "/user/ban")
@RequiredArgsConstructor
public class UserBanCommand implements Command {

    private final UserService userService;

    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        int userId = Integer.parseInt(request.getParameter("id"));
        userService.banUser(userId);
        return new RedirectResult("/users");
    }
}
