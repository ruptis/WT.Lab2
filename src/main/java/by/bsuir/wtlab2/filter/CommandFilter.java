package by.bsuir.wtlab2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsuir.wtlab2.constants.RequestPrefixes.*;

@Slf4j
public final class CommandFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.startsWith(VIEWS_PREFIX.getValue()) || path.startsWith(RESOURCES_PREFIX.getValue())) {
            log.debug("Passing request {} to next filter.", path);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            log.debug("Forwarding request {} to command.", path);
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(COMMAND_PREFIX.getValue() + path);
            dispatcher.forward(servletRequest, servletResponse);
        }
    }
}
