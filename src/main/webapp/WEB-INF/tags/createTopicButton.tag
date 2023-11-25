<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<c:if test="${sessionScope.user != null && sessionScope.user.role == 'ADMIN'}">
    <a href="#" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createTopicModal">
        <fmt:message key="topic.new"/>
    </a>
    <t:createTopicModal/>
</c:if>