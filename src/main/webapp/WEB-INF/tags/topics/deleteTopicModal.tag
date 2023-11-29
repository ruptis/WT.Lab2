<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="topic" required="true" type="by.bsuir.wtlab2.entity.Topic" %>
<div class="modal fade" id="deleteTopicModal" tabindex="-1" aria-labelledby="deleteTopicModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="topic.delete"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p><fmt:message key="topic.delete_confirmation"/> <span class="fw-bold"><c:out value="${topic.name}"/> </span>?</p>
                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-secondary me-2" data-bs-dismiss="modal"><fmt:message key="topic.cancel_action"/></button>
                    <button onclick="deleteTopic(${topic.id})" type="button" class="btn btn-danger"><fmt:message key="topic.delete_action"/></button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function deleteTopic(id) {
        fetch('/topic?id=' + id, {
            method: 'DELETE',
        }).then(response => {
            if (response.ok) {
                location.reload();
            }
        });
    }
</script>