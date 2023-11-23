<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="questions" required="true" type="by.bsuir.wtlab2.entity.Question[]" %>
<div class="d-flex justify-content-between align-items-center">
    <h4><fmt:message key="questions.title"/></h4>
    <a href="<c:url value="/newQuestion"/>" class="btn btn-primary"><fmt:message key="questions.new"/></a>
</div>

<c:forEach items="${questions}" var="question">
    <t:question question="${question}"/>
</c:forEach>
