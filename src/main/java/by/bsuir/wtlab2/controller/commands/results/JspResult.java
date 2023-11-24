package by.bsuir.wtlab2.controller.commands.results;

import by.bsuir.wtlab2.constants.RequestPrefixes;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class JspResult implements CommandResult {
    private final String page;
    private final Map<String, Object> attributes;

    public JspResult(String page, Map<String, Object> attributes) {
        this.page = page;
        this.attributes = attributes;
    }

    public JspResult(String page) {
        this(page, Collections.emptyMap());
    }

    @Override
    public void executeResult(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            attributes.forEach(request::setAttribute);
            String path = buildPath();
            log.trace("Forwarding to {}.", path);
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            throw new CommandException(e.getMessage(), e);
        }
    }

    private String buildPath() {
        return RequestPrefixes.VIEWS_PREFIX.getValue() + page + ".jsp";
    }
}
