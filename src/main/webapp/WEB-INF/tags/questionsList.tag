<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@attribute name="questions" required="true" type="by.bsuir.wtlab2.entity.Question[]" %>

<c:forEach items="${questions}" var="question">
    <t:question question="${question}"/>
</c:forEach>
