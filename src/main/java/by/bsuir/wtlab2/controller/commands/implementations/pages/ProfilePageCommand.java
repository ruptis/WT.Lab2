package by.bsuir.wtlab2.controller.commands.implementations.pages;

import by.bsuir.wtlab2.annotations.CommandSecurity;
import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static by.bsuir.wtlab2.constants.Role.ADMIN;
import static by.bsuir.wtlab2.constants.Role.USER;

@CommandSecurity(roles = {USER, ADMIN})
@WebCommand(mapping = "/profile")
public class ProfilePageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        String tab = request.getParameter("tab");
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("tab", tab);
        return new JspResult("profile", attributes);
    }
}
