package by.bsuir.wtlab2.controller.commands.implementations.results;

import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StatusCodeResult implements CommandResult {
    private final int statusCode;

    public StatusCodeResult(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void executeResult(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            response.sendError(statusCode);
        } catch (Exception e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
