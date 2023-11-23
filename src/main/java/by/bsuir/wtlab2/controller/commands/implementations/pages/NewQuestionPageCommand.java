package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@CommandSecurity(roles = {ADMIN, USER})
@WebCommand(mapping = "/newQuestion", method = HttpMethod.GET)
public final class NewQuestionPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        return new JspResult("newQuestion");
    }
}
