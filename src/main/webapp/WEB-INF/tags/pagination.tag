<%@ tag description="Pagination" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@attribute name="url" required="true" type="java.lang.String" %>
<%@attribute name="page" required="true" type="java.lang.Integer" %>
<%@attribute name="totalPages" required="true" type="java.lang.Integer" %>

<c:if test="${totalPages>1}">
    <nav aria-label="Page navigation" class="mt-5">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link <c:if test="${page==1}">disabled</c:if>" href="<c:url value="${url}"/>&page=${page-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item <c:if test="${page==i}">active</c:if>">
                    <a class="page-link" href="<c:url value="${url}"/>&page=${i}">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link <c:if test="${page==totalPages}">disabled</c:if>" href="<c:url value="${url}"/>&page=${page+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
