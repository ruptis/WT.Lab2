<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<t:basepage title="Home">
    <jsp:body>
        <div class="container">
            <div class="row">

                <div class="col-md-3 mt-2">
                    <t:topicList topics="${requestScope.topics}"/>
                </div>

                <div class="col-md-9 mt-2">
                    <div class="d-flex justify-content-between align-items-center">
                        <h4><fmt:message key="questions.title"/></h4>
                        <c:if test="${sessionScope.user != null}">
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#askQuestionModal">
                                <fmt:message key="questions.ask"/>
                            </button>
                            <t:askQuestionModal/>
                        </c:if>
                        <c:if test="${sessionScope.user == null}">
                            <a href="<c:url value="/login"/>" class="btn btn-primary">
                                <fmt:message key="questions.ask"/>
                            </a>
                        </c:if>
                    </div>
                    <t:questionsList questions="${requestScope.questions}"/>
                    <t:pagination url="/questions" page="${requestScope.page}" totalPages="${requestScope.totalPages}"/>
                </div>

            </div>
        </div>
    </jsp:body>
</t:basepage>
