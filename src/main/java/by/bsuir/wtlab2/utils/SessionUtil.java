package by.bsuir.wtlab2.utils;

import by.bsuir.wtlab2.entity.UserDetails;
import by.bsuir.wtlab2.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static by.bsuir.wtlab2.constants.SessionAttributes.USER_DETAILS;

public final class SessionUtil {

    private SessionUtil() {
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
}
