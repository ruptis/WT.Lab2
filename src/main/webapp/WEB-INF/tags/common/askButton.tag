<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="common" tagdir="/WEB-INF/tags/common"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="topics" required="false" type="java.util.List<by.bsuir.wtlab2.entity.Topic>" %>
<%@attribute name="topic" required="false" type="by.bsuir.wtlab2.entity.Topic" %>

<c:if test="${sessionScope.user != null}">
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#askQuestionModal">
        <fmt:message key="questions.ask"/>
    </button>
    <common:askQuestionModal topics="${topics}" topic="${topic}"/>
</c:if>
<c:if test="${sessionScope.user == null}">
    <a href="<c:url value="/login"/>" class="btn btn-primary">
        <fmt:message key="questions.ask"/>
    </a>
</c:if>