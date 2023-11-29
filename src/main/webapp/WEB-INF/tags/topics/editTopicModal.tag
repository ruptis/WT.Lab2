<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="topic" required="true" type="by.bsuir.wtlab2.entity.Topic" %>
<div class="modal fade" id="editTopicModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="topic.edit"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editTopicForm">
                    <div class="mb-3">
                        <label for="title" class="form-label"><fmt:message key="topic.title"/></label>
                        <input type="text" class="form-control" id="title" name="title" value="${topic.name}" required>
                    </div>
                    <button onclick="editTopic(${topic.id})" type="submit" class="btn btn-primary"><fmt:message key="topic.edit_action"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
<script>
    function editTopic(id) {
        const form = document.getElementById('editTopicForm');
        const formData = new FormData(form);
        const params = new URLSearchParams(formData).toString();
        fetch('/topic?id=' + id + '&' + params, {
            method: 'PUT'
        }).then(response => {
            if (response.ok) {
                window.location.reload();
            }
        });
    }
</script>