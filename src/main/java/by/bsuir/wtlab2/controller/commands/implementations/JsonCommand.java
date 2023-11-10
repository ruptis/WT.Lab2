package by.bsuir.wtlab2.controller.commands.implementations;

import by.bsuir.wtlab2.annotations.WebCommand;
import by.bsuir.wtlab2.controller.commands.Command;
import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.controller.commands.results.JsonResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

@WebCommand(mapping = "/json")
public class JsonCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws CommandException {
        return new JsonResult("{\"message\": \"Hello, World!\"}");
    }
}
