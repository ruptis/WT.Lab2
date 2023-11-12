package by.bsuir.wtlab2.controller.commands.implementations;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@CommandSecurity(roles = {"admin", "user"})
@WebCommand(mapping = "/newQuestion", method = HttpMethod.GET)
public class NewQuestionPageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        return new JspResult("newQuestion");
    }
}
