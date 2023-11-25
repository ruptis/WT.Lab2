<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib prefix="d" uri="date" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="answer" required="true" type="by.bsuir.wtlab2.entity.Answer" %>
<div class="list-group-item card">
    <div class="row g-0 g-md-3">
        <div class="col-md-2 d-flex flex-column align-items-md-end align-items-center justify-content-center">
            <div><fmt:message key="answer.reputation"/><c:out value="${answer.reputation}"/></div>
        </div>
        <div class="col-md-10">
            <div class="card-body">
                <p class="card-text"><c:out value="${answer.text}"/></p>
                <div class="d-flex justify-content-between align-items-center">
                    <div class="d-flex gap-2 align-items-end flex-lg-row flex-column justify-content-start">
                        <a href="<c:url value="/question?id=${answer.question.id}"/>"
                           class="text-decoration-none"><c:out value="${answer.question.title}"/></a>
                        <a href="<c:url value="/topic?id=${answer.question.topic.id}"/>"
                           class="badge bg-info text-decoration-none"><c:out value="${answer.question.topic}"/></a>
                    </div>
                    <div class="d-flex gap-2 align-items-end flex-lg-row flex-column">
                        <div class="small text-muted"><fmt:message key="answer.answered_by"/></div>
                        <a href="<c:url value="/profile?id=${answer.author.id}"/>"
                           class="link-primary text-decoration-none lead" style="line-height: 1.2;"><c:out
                                value="${answer.author.username}"/></a>
                        <div class="small text-muted"><fmt:message key="answer.at"/><c:out
                                value="${d:formatLocalDateTime(answer.answerTime)}"/></div>
                        <div class="small text-muted"><fmt:message key="answer.to"/></div>
                        <a href="<c:url value="/profile?id=${answer.question.author.id}"/>"
                           class="link-primary text-decoration-none lead" style="line-height: 1.2;"><c:out
                                value="${answer.question.author.username}"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>