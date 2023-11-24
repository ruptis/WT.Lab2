<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<t:basepage title="Profile">
    <jsp:body>
        <div class="container ps-5">
            <div class="row">
                <h1 class="display-3">Username</h1>
                <p class="small text-muted">Member since: 2021-09-01</p>
                <div class="d-flex flex-column">
                    <div>
                        <p class="lead">Reputation: ReputationCount</p>
                    </div>
                    <div class="d-flex flex-row gap-5">
                        <p>Questions: QuestionCount</p>
                        <p>Answers: AnswerCount</p>
                    </div>
                </div>
            </div>

            <ul class="nav nav-tabs" id="tabs">
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope.tab == 'questions'}">active</c:if>" id="questionsTab" data-bs-toggle="tab" href="#questions">Questions</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link <c:if test="${requestScope.tab == 'answers'}">active</c:if>" id="answersTab" data-bs-toggle="tab" href="#answers">Answers</a>
                </li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane fade <c:if test="${requestScope.tab == 'questions'}">show active</c:if>"  id="questions">
                    <!-- Здесь может быть ваш контент для вопросов пользователя -->
                    <c:if test="${requestScope.tab == 'questions'}">${requestScope.tab}</c:if>
                </div>
                <div class="tab-pane fade <c:if test="${requestScope.tab == 'answers'}">show active</c:if>" id="answers">
                    <!-- Здесь может быть ваш контент для ответов пользователя -->
                    <c:if test="${requestScope.tab == 'answers'}">${requestScope.tab}</c:if>
                </div>
                <!-- Добавьте дополнительные блоки для других вкладок -->
            </div>
        </div>
    </jsp:body>
</t:basepage>
