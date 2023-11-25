<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<t:basepage title="Topics">
    <jsp:body>
        <div class="container mt-2">
            <div class="row">
                <div class="col-md-12 d-flex justify-content-between align-items-center">
                    <h1><fmt:message key="topics.title"/></h1>
                    <t:createTopicButton/>
                </div>
                <hr>
            </div>
            <c:forEach items="${requestScope.topicsMap}" var="entry">
                <t:topicCard topic="${entry.key}" questions="${entry.value}"/>
            </c:forEach>
            <t:pagination url="/topics?" page="${requestScope.topicsPage}"/>
        </div>
    </jsp:body>
</t:basepage>
