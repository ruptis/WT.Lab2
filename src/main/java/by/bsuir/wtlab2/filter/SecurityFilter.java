package by.bsuir.wtlab2.filter;

import by.bsuir.wtlab2.application.di.DiContainer;
import by.bsuir.wtlab2.constants.SessionAttributes;
import by.bsuir.wtlab2.exception.DiException;
import by.bsuir.wtlab2.mapping.CommandMapping;
import by.bsuir.wtlab2.service.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SecurityFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        var resolver = DiContainer.getResolver();
        try {
            securityService = resolver.resolve(SecurityService.class);
        } catch (DiException e) {
            logger.error("Failed to initialize security filter.", e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);

        CommandMapping mapping = CommandMapping.of(request);
        logger.debug("Request mapping: {}", mapping);

        if (securityService.isSecured(mapping) && !isAllowed(request, mapping)) {
            logger.debug("Unauthorized access to {}", request.getRequestURI());
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean isAllowed(HttpServletRequest request, CommandMapping mapping) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;
        String role = (String) session.getAttribute(SessionAttributes.ROLE.getValue());
        return securityService.isAllowed(mapping, role);
    }
}
