<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/topics"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="topic" required="true" type="by.bsuir.wtlab2.entity.Topic" %>

<c:if test="${sessionScope.user != null && sessionScope.user.role == 'ADMIN'}">
    <a class="btn btn-outline-primary" data-bs-toggle="modal" data-bs-target="#editTopicModal"><fmt:message key="topic.edit_action"/></a>
    <a class="btn btn-outline-danger" data-bs-toggle="modal" data-bs-target="#deleteTopicModal"><fmt:message key="topic.delete_action"/></a>
    <t:editTopicModal topic="${topic}"/>
    <t:deleteTopicModal topic="${topic}"/>
</c:if>