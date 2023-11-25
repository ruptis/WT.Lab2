<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<t:basepage title="${requestScope.topic.name}">
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-between align-items-center">
                    <h1><c:out value="${requestScope.topic.name}"/></h1>
                    <t:askButton topics="${requestScope.topics}"/>
                </div>
                <hr>
            </div>
            <t:questionCardsList questions="${requestScope.questions.content}"/>
            <t:pagination url="/topic?id=${requestScope.topic.id}" page="${requestScope.questions}"/>
        </div>
    </jsp:body>
</t:basepage>
