<%@tag description="Header" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<%@attribute name="user" required="false" type="by.bsuir.wtlab2.entity.UserDetails" %>
<header>
    <nav class="navbar navbar-expand-sm navbar-light bg-light fixed-top">
        <div class="container">
            <a class="navbar-brand" href="<c:url value="/"/>">LikeIt</a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/"/>"><fmt:message key="header.home"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value="/topics"/>"><fmt:message key="header.topics"/></a>
                    </li>
                    <c:if test="${user != null && user.role == 'ADMIN'}">
                        <li class="nav-item">
                            <a class="nav-link" href="<c:url value="/users"/>"><fmt:message key="header.users"/></a>
                        </li>
                    </c:if>
                    <c:if test="${user != null}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false" id="navbarProfile">
                                <fmt:message key="header.profile"/> (${user.username})
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarProfile">
                                <li><a class="dropdown-item" href="<c:url value="/profile?id=${user.id}"/>"><fmt:message key="header.my_profile"/></a></li>
                                <li><a class="dropdown-item" href="<c:url value="/profile?id=${user.id}&tab=questions#tabs"/>"><fmt:message key="header.my_questions"/></a></li>
                                <li><a class="dropdown-item" href="<c:url value="/profile?id=${user.id}&tab=answers#tabs"/>"><fmt:message key="header.my_answers"/></a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item link-danger text-danger" href="<c:url value="/logout"/>"><fmt:message key="header.logout"/></a></li>
                            </ul>
                        </li>
                    </c:if>
                    <c:if test="${user == null}">
                        <li class="nav-item">
                            <a class="nav-link link-primary" href="<c:url value="/login"/>"><fmt:message key="header.login"/></a>
                        </li>
                    </c:if>
                    <li class="nav-item dropdown border-start ms-2">
                        <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false" id="navbarLang">
                            <fmt:message key="header.lang"/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarLang">
                            <li><a class="dropdown-item" onclick="changeLocale('en-US')">English</a></li>
                            <li><a class="dropdown-item" onclick="changeLocale('ru-RU')">Русский</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</header>
<script>
    function changeLocale(locale) {
        fetch('/locale?locale=' + locale, {
            method: 'POST'
        }).then(() => {
            location.reload()
        })
    }
</script>
