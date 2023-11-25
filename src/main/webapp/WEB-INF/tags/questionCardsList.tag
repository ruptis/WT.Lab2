<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@attribute name="questions" required="true" type="java.util.List<by.bsuir.wtlab2.entity.Question>" %>

<div class="list-group mt-2 gap-2">
    <c:forEach items="${questions}" var="question">
        <t:questionCard question="${question}"/>
    </c:forEach>
</div>

