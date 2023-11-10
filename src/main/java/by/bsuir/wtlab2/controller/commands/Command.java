package by.bsuir.wtlab2.controller.commands;

import by.bsuir.wtlab2.controller.commands.results.CommandResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {
    CommandResult execute(HttpServletRequest request) throws CommandException;
}
