<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@attribute name="answers" required="true" type="java.util.List<by.bsuir.wtlab2.entity.Answer>" %>

<div class="list-group mt-2">
    <c:forEach items="${answers}" var="answer">
        <t:profileAnswer answer="${answer}"/>
    </c:forEach>
</div>