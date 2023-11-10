<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@attribute name="question" required="true" type="by.bsuir.wtlab2.model.Question" %>

<div class="list-group mt-2">
    <div class="list-group-item card">
        <div class="row g-0">
            <div class="col-md-2">
                <div class="d-flex flex-column align-items-center">
                    <div>Views: ${question.viewsCount}</div>
                    <div>Answers: ${question.answersCount}</div>
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
                            <img src="avatar.jpg" alt="Avatar" class="rounded-circle me-2" width="32" height="32">
                            <span>${question.author}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>