<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="question" required="true" type="by.bsuir.wtlab2.entity.Question" %>
<div class="list-group mt-2">
    <div class="list-group-item card">
        <div class="row g-0 g-md-3">
            <div class="col-md-2 d-flex flex-column align-items-md-end align-items-center justify-content-center">
                    <div><fmt:message key="question.views"/>: ${question.viewsCount}</div>
                    <div><fmt:message key="question.answers"/>: ${question.answersCount}</div>
            </div>
            <div class="col-md-10">
                <div class="card-body">
                    <h5 class="card-title"><a href="#" class="text-decoration-none">${question.title}</a></h5>
                    <p class="card-text">${question.text}</p>
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <a href="#" class="badge bg-info text-decoration-none">${question.topic}</a>
                        </div>
                        <div class="d-flex gap-2 align-items-end">
                            <div class="small text-muted"><fmt:message key="question.asked_by"/></div>
                            <a href="#" class="link-primary text-decoration-none lead" style="line-height: 1.2;">${question.author}</a>
                            <div class="small text-muted"><fmt:message key="question.at"/> ${question.askTime}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>