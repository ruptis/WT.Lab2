<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="topics" required="false" type="java.util.List<by.bsuir.wtlab2.entity.Topic>" %>
<%@attribute name="topic" required="false" type="by.bsuir.wtlab2.entity.Topic" %>
<div class="modal fade" id="askQuestionModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="questions.ask"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="<c:url value="/question"/>" method="post">
                    <div class="mb-3">
                        <label for="topic" class="form-label"><fmt:message key="questions.topic"/></label>
                        <c:if test="${empty topics}">
                            <label for="topicName"></label>
                            <input type="text" class="form-control" id="topicName" name="topicName" value="${topic.name}" readonly>
                            <input type="hidden" id="topic" name="topic" value="${topic.id}">
                        </c:if>
                        <c:if test="${not empty topics}">
                            <select class="form-select" id="topic" name="topic" required>
                                <option value="" selected disabled hidden><fmt:message key="questions.selectTopic"/></option>
                                <c:forEach items="${topics}" var="topic">
                                    <option value="${topic.id}">${topic.name}</option>
                                </c:forEach>
                            </select>
                        </c:if>
                    </div>
                    <div class="mb-3">
                        <label for="title" class="form-label"><fmt:message key="question.title"/></label>
                        <input type="text" class="form-control" id="title" name="title" required>
                    </div>
                    <div class="mb-3">
                        <label for="text" class="form-label"><fmt:message key="questions.body"/></label>
                        <textarea class="form-control" id="text" name="text" rows="3" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary"><fmt:message key="questions.submit"/></button>
                </form>
            </div>
        </div>
    </div>
</div>