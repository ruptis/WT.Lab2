<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<t:basepage title="Home">
    <jsp:body>
        <div class="container">
            <div class="row">

                <div class="col-md-3 mt-2">
                    <t:topicList topics="${requestScope.topics}"/>
                </div>

                <div class="col-md-9 mt-2">
                    <t:questionsList questions="${requestScope.questions}"/>
                </div>
            </div>
        </div>
    </jsp:body>
</t:basepage>
