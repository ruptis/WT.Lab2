<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ taglib prefix="d" uri="date" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="answer" required="true" type="by.bsuir.wtlab2.entity.Answer" %>
<%@attribute name="vote" required="true" type="java.util.Optional<by.bsuir.wtlab2.entity.Vote>" %>

<c:set var="upvoted" value="${vote.present && vote.get().change == 1}"/>
<c:set var="downvoted" value="${vote.present && vote.get().change == -1}"/>
<div class="row">
    <div class="col-md-1">
        <div class="d-flex flex-column align-items-center">
            <button onclick="<c:choose>
                <c:when test="${upvoted}">unvote(${answer.id})</c:when>
                <c:otherwise>upvote(${answer.id})</c:otherwise>
                </c:choose>" class="btn <c:choose>
                <c:when test="${upvoted}">btn-outline-success active</c:when>
                <c:otherwise>btn-outline-secondary</c:otherwise>
                </c:choose> rounded-5 mb-2" id="arrow-up-<c:out value="${answer.id}"/>">
                <i class="bi bi-arrow-up text-muted fs-4"></i>
            </button>
            <span class="lead"><c:out value="${answer.reputation}"/></span>
            <button onclick="<c:choose>
                <c:when test="${downvoted}">unvote(${answer.id})</c:when>
                <c:otherwise>downvote(${answer.id})</c:otherwise>
                </c:choose>" class="btn <c:choose>
                <c:when test="${downvoted}">btn-outline-danger active</c:when>
                <c:otherwise>btn-outline-secondary</c:otherwise>
                </c:choose> rounded-5 mt-2" id="arrow-down-<c:out value="${answer.id}"/>">
                <i class="bi bi-arrow-down text-muted fs-4"></i>
            </button>
        </div>
    </div>
    <div class="col-md-11">
        <p><c:out value="${answer.text}"/></p>
    </div>
</div>
<div class="row">
    <hr>
</div>
<div class="row mb-3">
    <div class="d-flex justify-content-end gap-2 align-items-end">
        <div class="small text-muted"><fmt:message key="answer.answered_by"/></div>
        <a href="<c:url value="/profile?id=${answer.author.id}"/>" class="link-primary text-decoration-none lead" style="line-height: 1.2;"><c:out
                value="${answer.author.username}"/></a>
        <div class="small text-muted"><fmt:message key="answer.at"/> <c:out
                value="${d:formatLocalDateTime(answer.answerTime)}"/></div>
    </div>
</div>
<div class="row">
    <hr>
</div>

<script>
    function upvote(id) {
        const upvoteButton = document.getElementById("arrow-up-" + id);
        const downvoteButton = document.getElementById("arrow-down-" + id);

        if (upvoteButton.classList.contains("active")) {
            upvoteButton.classList.remove("active");
            downvoteButton.classList.remove("active");
        } else {
            upvoteButton.classList.add("active");
            downvoteButton.classList.remove("active");
        }

        fetch("/answer/upvote?id=" + id, {
            method: "POST",
        }).then(response => {
            if (response.ok) {
                location.reload();
            }
        });
    }

    function downvote(id) {
        const upvoteButton = document.getElementById("arrow-up-" + id);
        const downvoteButton = document.getElementById("arrow-down-" + id);

        if (downvoteButton.classList.contains("active")) {
            upvoteButton.classList.remove("active");
            downvoteButton.classList.remove("active");
        } else {
            upvoteButton.classList.remove("active");
            downvoteButton.classList.add("active");
        }

        fetch("/answer/downvote?id=" + id, {
            method: "POST",
        }).then(response => {
            if (response.ok) {
                location.reload();
            }
        });
    }

    function unvote(id) {
        const upvoteButton = document.getElementById("arrow-up-" + id);
        const downvoteButton = document.getElementById("arrow-down-" + id);

        upvoteButton.classList.remove("active");
        downvoteButton.classList.remove("active");

        fetch("/answer/unvote?id=" + id, {
            method: "POST",
        }).then(response => {
            if (response.ok) {
                location.reload();
            }
        });
    }
</script>