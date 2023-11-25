package by.bsuir.wtlab2.filter;

import by.bsuir.wtlab2.application.di.DiContainer;
import by.bsuir.wtlab2.constants.Role;
import by.bsuir.wtlab2.entity.UserDetails;
import by.bsuir.wtlab2.exception.DiException;
import by.bsuir.wtlab2.mapping.CommandMapping;
import by.bsuir.wtlab2.service.SecurityService;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsuir.wtlab2.constants.Role.GUEST;
import static by.bsuir.wtlab2.constants.SessionAttributes.USER_DETAILS;

@Slf4j
public final class SecurityFilter implements Filter {
    private SecurityService securityService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        DiContainer resolver = (DiContainer) filterConfig.getServletContext().getAttribute("diContainer");
        try {
            securityService = resolver.resolve(SecurityService.class);
        } catch (DiException e) {
            log.error("Failed to initialize security filter.", e);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);

        CommandMapping mapping = CommandMapping.of(request);
        log.debug("Request mapping: {}", mapping);

        if (securityService.isSecured(mapping) && !isAllowed(request, mapping)) {
            log.debug("Unauthorized access to {}", request.getRequestURI());
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            log.debug("Access to {} is allowed", request.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean isAllowed(HttpServletRequest request, CommandMapping mapping) {
        HttpSession session = request.getSession(false);
        Role role = GUEST;

        if (session != null)
            role = getRole(session, role);

        return securityService.isAllowed(mapping, role);
    }

    private static Role getRole(HttpSession session, Role role) {
        UserDetails userDetails = (UserDetails) session.getAttribute(USER_DETAILS.getValue());
        if (userDetails != null) {
            role = userDetails.getRole();
        }
        return role;
    }
}
