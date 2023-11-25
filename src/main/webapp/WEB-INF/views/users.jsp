<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<t:basepage title="Users">
    <jsp:body>
        <div class="container">
        <div class="row">
            <h1><fmt:message key="users.title"/></h1>
            <hr>
        </div>

        <div class="list-group gap-2">
            <c:forEach items="${requestScope.users.content}" var="user">
                <t:userCard user="${user}"/>
            </c:forEach>
            <t:pagination url="/users?" page="${requestScope.users}"/>
        </div>
    </jsp:body>
</t:basepage>