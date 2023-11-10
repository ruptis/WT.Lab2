package by.bsuir.wtlab2.controller.commands.implementations;


import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.constants.HttpMethod;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JspResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;

@WebCommand(mapping = "/hello", method = HttpMethod.GET)
public class HelloCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        var attributes = new HashMap<String, Object>();
        attributes.put("message", "Hello, World!");
        return new JspResult("hello", attributes);
    }
}
