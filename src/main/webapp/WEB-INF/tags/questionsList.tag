<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@attribute name="questions" required="true" type="by.bsuir.wtlab2.model.Question[]" %>
<div class="d-flex justify-content-between align-items-center">
    <h4>Questions</h4>
    <a href="#" class="btn btn-primary">Ask a question</a>
</div>

<c:forEach items="${questions}" var="question">
    <t:question question="${question}"/>
</c:forEach>
