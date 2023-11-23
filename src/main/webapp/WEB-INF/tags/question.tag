<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="question" required="true" type="by.bsuir.wtlab2.entity.Question" %>
<div class="list-group mt-2">
    <div class="list-group-item card">
        <div class="row g-0">
            <div class="col-md-2">
                <div class="d-flex flex-column align-items-center">
                    <div><fmt:message key="question.views"/>: ${question.viewsCount}</div>
                    <div><fmt:message key="question.answers"/>: ${question.answersCount}</div>
                </div>
            </div>
            <div class="col-md-10">
                <div class="card-body">
                    <h5 class="card-title">${question.title}</h5>
                    <p class="card-text">${question.text}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <span>${question.topic}</span>
                        </div>
                        <div>
                            <span>${question.author}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>