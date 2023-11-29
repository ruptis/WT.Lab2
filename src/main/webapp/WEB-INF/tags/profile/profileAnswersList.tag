<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="p" %>

<%@attribute name="answers" required="true" type="java.util.List<by.bsuir.wtlab2.entity.Answer>" %>

<div class="list-group mt-2">
    <c:forEach items="${answers}" var="answer">
        <p:profileAnswer answer="${answer}"/>
    </c:forEach>
</div>