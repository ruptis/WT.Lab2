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
                    <t:topicNamesList topics="${requestScope.topics}"/>
                </div>

                <div class="col-md-9 mt-2">
                    <div class="d-flex justify-content-between align-items-center">
                        <h4><fmt:message key="questions.title"/></h4>
                        <t:askButton topics="${requestScope.topics}"/>
                    </div>
                    <t:questionCardsList questions="${requestScope.questionsPage.content}"/>
                    <t:pagination url="/?" page="${requestScope.questionsPage}"/>
                </div>

            </div>
        </div>
    </jsp:body>
</t:basepage>
