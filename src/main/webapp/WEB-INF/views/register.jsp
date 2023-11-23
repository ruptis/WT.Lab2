<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<t:basepage title="Login">
    <jsp:body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-4 col-md-offset-4">
                    <h1>Register</h1>
                    <div class="row">
                        <form action="<c:url value="/register"/>" method="post">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="Username">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="Password">
                            </div>
                            <div class="form-group mt-3 d-flex justify-content-between align-items-center">
                                <button type="submit" class="btn btn-primary px-5">Register</button>
                                <a href="<c:url value="/login"/>" class="text-decoration-none">Login</a>
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
