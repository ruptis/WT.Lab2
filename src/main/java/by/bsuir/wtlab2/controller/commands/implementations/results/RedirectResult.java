package by.bsuir.wtlab2.controller.commands.implementations.results;

import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectResult implements CommandResult {
    private final String url;

    public RedirectResult(String url) {
        this.url = url;
    }

    @Override
    public void executeResult(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            response.sendRedirect(url);
        } catch (Exception e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
