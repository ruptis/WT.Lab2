<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib tagdir="/WEB-INF/tags/question" prefix="q" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<t:basepage title="${requestScope.question.title}">
    <jsp:body>
        <div class="container ps-5">
            <q:question question="${requestScope.question}"/>
            <div class="row">
                <hr>
            </div>
            <div class="row mb-3">
                <h4><c:out value="${requestScope.question.answersCount}"/> <fmt:message key="question.answers_count"/></h4>
            </div>
            <c:forEach var="entry" items="${requestScope.votes}">
                <q:answer answer="${entry.key}" vote="${entry.value}"/>
            </c:forEach>
            <t:pagination url="/question?id=${requestScope.question.id}" page="${requestScope.answers}"/>
            <div class="row mb-3">
                <h4><fmt:message key="question.your_answer"/></h4>
                <form action="<c:url value="/answer?id=${requestScope.question.id}"/>" method="post">
                    <div class="mb-3">
                        <label for="text" class="form-label"><fmt:message key="question.answer"/></label>
                        <textarea class="form-control" id="text" name="text" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="question.add_answer"/></button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:basepage>
