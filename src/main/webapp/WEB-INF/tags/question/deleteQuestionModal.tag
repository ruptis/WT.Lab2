<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="question" required="true" type="by.bsuir.wtlab2.entity.Question"%>
<div class="modal fade" id="deleteQuestionModal" tabindex="-1" aria-labelledby="deleteQuestionModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="question.delete"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><fmt:message key="question.delete_confirmation"/>?</p>
                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-secondary me-2" data-bs-dismiss="modal"><fmt:message key="topic.cancel_action"/></button>
                    <button onclick="deleteQuestion(${question.id})" type="button" class="btn btn-danger"><fmt:message key="topic.delete_action"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function deleteQuestion(id) {
        fetch('/question?id=' + id, {
            method: 'DELETE',
        }).then(response => {
            if (response.ok) {
                window.location.href = '/topic?id=${question.topic.id}';
            }
        });
    }
</script>