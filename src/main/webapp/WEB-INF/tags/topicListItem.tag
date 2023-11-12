<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@attribute name="topic" required="true" type="by.bsuir.wtlab2.beans.Topic" %>
<div class="list-group-item">
    <a href="#"><c:out value="${topic.name}"/></a>
    <span class="badge bg-primary rounded-pill float-end"><c:out value="${topic.questionsCount}"/></span>
</div>