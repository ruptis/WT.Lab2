<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<t:basepage title="Login">
    <jsp:body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4 col-md-offset-4">
                    <h1>Login</h1>
                    <div class="row">
                        <form action="<c:url value="/login"/>" method="post">
                            <div class="form-group">
                                <label for="username"><fmt:message key="username"/></label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="<fmt:message key="username"/>" required="required" autofocus="autofocus" value="${not empty param.username ? param.username : ''}"/>
                            </div>
                            <div class="form-group">
                                <label for="password"><fmt:message key="password"/></label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="<fmt:message key="password"/>" required="required" minlength="6"/>
                            </div>
                            <div class="form-group mt-3 d-flex justify-content-between align-items-center">
                                <button type="submit" class="btn btn-primary px-5"><fmt:message key="login"/></button>
                                <a href="<c:url value="/register"/>" class="text-decoration-none"><fmt:message key="register"/></a>
                            </div>
                            <c:if test="${not empty requestScope.error}">
                                <div class="alert alert-danger mt-3" role="alert">${requestScope.error}</div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:basepage>