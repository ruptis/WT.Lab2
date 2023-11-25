<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<t:basepage title="Profile">
    <jsp:body>
        <div class="container ps-5">
            <div class="row">
                <h1 class="display-3">${requestScope.user.username}</h1>
                <p class="small text-muted"><fmt:message key="profile.member_since"/>: ${requestScope.user.registrationDate}</p>
                <div class="d-flex flex-column">
                    <div>
                        <p class="lead"><fmt:message key="profile.reputation"/>: ${requestScope.user.reputation}</p>
                    </div>
                    <div class="d-flex flex-row gap-5">
                        <p><fmt:message key="profile.questions"/>: ${requestScope.user.questionsCount}</p>
                        <p><fmt:message key="profile.answers"/>: ${requestScope.user.answersCount}</p>
                    </div>
                </div>
            </div>

            <ul class="nav nav-tabs" id="tabs">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope.tab == 'questions'}">active</c:if>" id="questionsTab" data-bs-toggle="tab" href="#questions"><fmt:message key="profile.questions"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope.tab == 'answers'}">active</c:if>" id="answersTab" data-bs-toggle="tab" href="#answers"><fmt:message key="profile.answers"/></a>
                </li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane fade <c:if test="${requestScope.tab == 'questions'}">show active</c:if>"  id="questions">
                    <t:questionCardsList questions="${requestScope.questions.content}"/>
                    <t:pagination url="/profile?id=${requestScope.user.id}&tab=questions" page="${requestScope.questions}" id="Questions"/>
                </div>
                <div class="tab-pane fade <c:if test="${requestScope.tab == 'answers'}">show active</c:if>" id="answers">
                    <t:profileAnswersList answers="${requestScope.answers.content}"/>
                    <t:pagination url="/profile?id=${requestScope.user.id}&tab=answers" page="${requestScope.answers}" id="Answers"/>
                </div>
            </div>
        </div>
    </jsp:body>
</t:basepage>
