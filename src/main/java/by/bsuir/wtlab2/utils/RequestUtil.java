package by.bsuir.wtlab2.utils;

import by.bsuir.wtlab2.entity.UserDetails;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.bsuir.wtlab2.constants.SessionAttributes.USER_DETAILS;

public final class RequestUtil {

    private RequestUtil() {
    }

    public static long getAuthorId(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        if (session == null)
            throw new CommandException("Failed to get session");
        UserDetails userDetails = (UserDetails) session.getAttribute(USER_DETAILS.getValue());
        if (userDetails == null)
            throw new CommandException("Failed to get user details");
        return userDetails.getId();
    }

    public static int getPage(HttpServletRequest request) {
        return getPage(request, "");
    }

    public static int getPage(HttpServletRequest request, String id) {
        String stringPage = request.getParameter("page" + id);
        return stringPage == null ? 1 : Integer.parseInt(stringPage);
    }
}
