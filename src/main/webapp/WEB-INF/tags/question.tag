<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="d" uri="date" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="question" required="true" type="by.bsuir.wtlab2.entity.Question" %>
<div class="row">
    <h1 class="display-4 border-info" id="title"><c:out value="${question.title}"/></h1>
    <div class="d-flex gap-3">
        <p class="small text-muted"><fmt:message key="question.asked_at"/>: <c:out value="${d:formatLocalDateTime(question.askTime)}"/></p>
        <p class="small text-muted ms-3"><fmt:message key="question.last_update"/>: <c:out value="${d:formatLocalDateTime(question.lastUpdateTime)}"/></p>
        <p class="small text-muted ms-3"><fmt:message key="question.views"/>: <c:out value="${question.viewsCount}"/></p>
    </div>
    <div class="row">
        <hr>
    </div>
    <div class="row">
        <div class="col-md-12 p-3 border-info" id="body">
            <c:out value="${question.text}"/>
        </div>
    </div>
    <div class="row">
        <hr>
    </div>
    <div class="row mb-3">
        <div class="d-flex gap-5 justify-content-between">
            <div class="d-flex gap-2">
                <t:questionActionButtons question="${question}"/>
            </div>
            <div class="d-flex gap-2 align-items-end">
                <div class="small text-muted"><fmt:message key="question.asked_by"/>: </div>
                <a href="#" class="link-primary text-decoration-none lead"
                   style="line-height: 1.2;"><c:out value="${question.author.username}"/></a>
                <div class="small text-muted"><fmt:message key="question.at"/>: <c:out
                        value="${d:formatLocalDateTime(question.askTime)}"/></div>
            </div>
        </div>
    </div>
</div>