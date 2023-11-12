<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@attribute name="topics" required="true" type="by.bsuir.wtlab2.beans.Topic[]" %>
<h4>Topics</h4>
<div class="list-group mt-3">
    <c:forEach items="${topics}" var="topic">
        <t:topicListItem topic="${topic}"/>
    </c:forEach>
</div>