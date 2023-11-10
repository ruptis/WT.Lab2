package by.bsuir.wtlab2.controller.commands.results;

import by.bsuir.wtlab2.constants.RequestPrefixes;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

public class JspResult implements CommandResult {
    private final String page;
    private final Map<String, Object> attributes;

    public JspResult(String page, Map<String, Object> attributes) {
        this.page = page;
        this.attributes = attributes;
    }

    public JspResult(String page)
    {
        this(page, new HashMap<>());
    }

    @Override
    public void executeResult(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            attributes.forEach(request::setAttribute);
            request.getRequestDispatcher(RequestPrefixes.VIEWS_PREFIX.getValue() + page + ".jsp").forward(request, response);
        } catch (Exception e) {
            throw new CommandException(e.getMessage(), e);
        }
    }
}