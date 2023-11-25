<%@tag description="Question Modal" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="question" required="true" type="by.bsuir.wtlab2.entity.Question" %>

<c:if test="${sessionScope.user.id == question.author.id}">
    <a href="#" class="btn btn-outline-primary" onclick="editQuestion()" style="min-width: 100px;"
    id="edit"><fmt:message key="question.edit"/></a>
    <a href="#" class="btn btn-outline-danger" style="min-width: 100px;"><fmt:message
        key="question.delete"/></a>
    <t:deleteQuestionModal question="${question}"/>
    <script>
        function editQuestion() {
            const title = document.getElementById('title');
            const body = document.getElementById('body');
            const edit = document.getElementById('edit');

            const isEditing = title.contentEditable === 'true';
            title.contentEditable = !isEditing;
            title.classList.toggle('border');
            body.contentEditable = !isEditing;
            body.classList.toggle('border');

            edit.innerText = isEditing ? '<fmt:message key="question.edit"/>' : '<fmt:message key="question.save"/>';
            edit.classList.toggle('btn-outline-primary');
            edit.classList.toggle('btn-outline-success');

            if (!isEditing) {
                saveChanges(title.innerText, body.innerText);
            }
        }

        function saveChanges(title, body) {
            const form = new FormData();
            form.append('id', ${question.id});
            form.append('title', title);
            form.append('text', body);

            const params = new URLSearchParams(form);
            fetch('/question?' + params.toString(), {
                method: 'PUT'
            }).then(response => {
                if (response.ok) {
                    location.reload();
                }
            });
        }
    </script>
</c:if>