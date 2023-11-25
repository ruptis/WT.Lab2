<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<t:basepage title="404">
    <jsp:body>
        <div class="container mt-5 ms-5">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading"><fmt:message key="404.title"/></h4>
                <p><fmt:message key="404.sorry"/></p>
                <hr>
                <p class="mb-0"><fmt:message key="404.contact"/></p>
            </div>
        </div>
    </jsp:body>
</t:basepage>
