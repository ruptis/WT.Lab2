package by.bsuir.wtlab2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

import static by.bsuir.wtlab2.constants.RequestPrefixes.*;

public class CommandFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (path.startsWith(VIEWS_PREFIX.getValue()) || path.startsWith(RESOURCES_PREFIX.getValue())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(COMMAND_PREFIX.getValue() + path);
            dispatcher.forward(servletRequest, servletResponse);
        }
    }
}
