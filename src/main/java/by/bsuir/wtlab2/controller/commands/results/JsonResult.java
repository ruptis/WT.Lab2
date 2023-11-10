package by.bsuir.wtlab2.controller.commands.results;

import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JsonResult implements CommandResult {
    private final Object data;

    public JsonResult(Object data) {
        this.data = data;
    }

    @Override
    public void executeResult(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            response.setContentType("application/json");
            response.getWriter().write(data.toString());
        } catch (Exception e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}
