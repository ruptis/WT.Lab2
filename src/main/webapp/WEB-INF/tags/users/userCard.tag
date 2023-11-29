<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="d" uri="date" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="user" required="true" type="by.bsuir.wtlab2.entity.User" %>

<div class="list-group-item card">
    <div class="card-body d-flex justify-content-between align-items-center">
        <div>
            <h5 class="card-title"><a href="<c:url value="/profile?id=${user.id}"/>" class="text-decoration-none"><c:out value="${user.username}"/></a></h5>
            <div class="card-text d-flex flex-column flex-md-row gap-4 align-items-md-center">
                <div class="card-text"><fmt:message key="user.email"/>: <c:out value="${user.email}"/></div>
                <div class="card-text"><fmt:message key="user.role"/>: <c:out value="${user.role}"/></div>
                <div class="card-text"><fmt:message key="user.registration_date"/>: <c:out value="${user.registrationDate}"/></div>
                <div class="card-text"><fmt:message key="user.reputation"/>: <c:out value="${user.reputation}"/></div>
            </div>
        </div>
        <c:if test="${user.role != 'BANNED'}">
            <a href="<c:url value="/user/ban?id=${user.id}"/>" class="btn btn-danger"><fmt:message key="user.ban"/></a>
        </c:if>
        <c:if test="${user.role == 'BANNED'}">
            <a href="<c:url value="/user/unban?id=${user.id}"/>" class="btn btn-secondary"><fmt:message key="user.unban"/></a>
        </c:if>
    </div>
</div>