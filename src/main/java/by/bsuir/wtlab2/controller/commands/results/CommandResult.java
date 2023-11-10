package by.bsuir.wtlab2.controller.commands.results;

import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface CommandResult {
    void executeResult(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
