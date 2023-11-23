<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<t:basepage title="Banned">
    <c:if test="${not empty requestScope.ban}">
        <h1>Banned</h1>
        <p>
            You have been banned from this site.
        </p>
        <p>
            Reason: ${requestScope.ban.reason}
        </p>
        <p>
            Ban expires: ${requestScope.ban.expiredTime}
        </p>
    </c:if>
</t:basepage>
