<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<t:basepage title="Login">
    <jsp:body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4 col-md-offset-4">
                    <h1>Login</h1>
                    <div class="row">
                        <form action="<c:url value="/login"/>" method="post">
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required="required" autofocus="autofocus" value="${not empty param.username ? param.username : ''}"/>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required"/>
                            </div>
                            <div class="form-group mt-3 d-flex justify-content-between align-items-center">
                                <button type="submit" class="btn btn-primary px-5">Login</button>
                                <a href="<c:url value="/register"/>" class="text-decoration-none">Register</a>
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