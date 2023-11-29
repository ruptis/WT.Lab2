<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/home" prefix="h" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="topics" required="true" type="java.util.List<by.bsuir.wtlab2.entity.Topic>" %>
<h4><fmt:message key="topics.title"/></h4>
<div class="list-group mt-3">
    <c:forEach items="${topics}" var="topic">
        <h:topicName topic="${topic}"/>
    </c:forEach>
</div>