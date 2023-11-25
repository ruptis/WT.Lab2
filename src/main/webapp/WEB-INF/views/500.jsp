<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<t:basepage title="500">
    <jsp:body>
        <div class="container mt-5 ms-5">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">500 - Internal Server Error</h4>
                <p>Sorry, something went wrong.</p>
                <hr>
                <p class="mb-0">Please try again later.</p>
            </div>
        </div>
    </jsp:body>
</t:basepage>
