package by.bsuir.wtlab2.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static by.bsuir.wtlab2.constants.SessionAttributes.LOCALE;

@Slf4j
public class LocaleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(true);
        String locale = (String) session.getAttribute(LOCALE.getValue());
        if (locale == null) {
            session.setAttribute(LOCALE.getValue(), httpRequest.getLocale().toString());
            log.debug("Locale set to {}", httpRequest.getLocale());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
