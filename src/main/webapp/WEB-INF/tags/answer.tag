<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="d" uri="date" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="answer" required="true" type="by.bsuir.wtlab2.entity.Answer" %>
<div class="row">
    <div class="col-md-1">
        <div class="d-flex flex-column align-items-center">
            <a href="#" class="btn btn-outline-secondary rounded-5 mb-2" id="arrow-up-<c:out value="${answer.id}"/>">
                <i class="bi bi-arrow-up text-muted fs-4"></i>
            </a>
            <span class="lead">0</span>
            <a href="#" class="btn btn-outline-secondary rounded-5 mt-2" id="arrow-down-<c:out value="${answer.id}"/>">
                <i class="bi bi-arrow-down text-muted fs-4"></i>
            </a>
        </div>
    </div>
    <div class="col-md-11">
        <p><c:out value="${answer.text}"/></p>
    </div>
</div>
<div class="row">
    <hr>
</div>
<div class="row mb-3">
    <div class="d-flex justify-content-end gap-2 align-items-end">
        <div class="small text-muted"><fmt:message key="answer.answered_by"/></div>
        <a href="#" class="link-primary text-decoration-none lead" style="line-height: 1.2;"><c:out value="${answer.author.username}"/></a>
        <div class="small text-muted"><fmt:message key="answer.at"/> <c:out value="${d:formatLocalDateTime(answer.answerTime)}"/></div>
    </div>
</div>
<div class="row">
    <hr>
</div>