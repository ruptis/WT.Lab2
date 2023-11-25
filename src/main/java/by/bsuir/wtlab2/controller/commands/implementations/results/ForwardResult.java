package by.bsuir.wtlab2.controller.commands.implementations.results;

import by.bsuir.wtlab2.controller.commands.CommandResult;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ForwardResult implements CommandResult {
    private final String page;

    public ForwardResult(String page) {
        this.page = page;
    }
    @Override
    public void executeResult(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
