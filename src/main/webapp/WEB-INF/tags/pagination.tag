<%@ tag description="Pagination" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@attribute name="url" required="true" type="java.lang.String" %>
<%@attribute name="page" required="true" type="by.bsuir.wtlab2.utils.Page" %>
<%@attribute name="id" required="false" type="java.lang.String" %>

<c:if test="${page.hasNextPage() || page.hasPreviousPage()}">
    <nav aria-label="Page navigation" class="mt-5">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link <c:if test="${!page.hasPreviousPage()}">disabled</c:if>" href="<c:url value="${url}"/>&page${id}=${page.pageNumber-1}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach var="i" begin="1" end="${page.totalPages}">
                <li class="page-item <c:if test="${page.pageNumber==i}">active</c:if>">
                    <a class="page-link" href="<c:url value="${url}"/>&page${id}=${i}">${i}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link <c:if test="${!page.hasNextPage()}">disabled</c:if>" href="<c:url value="${url}"/>&page${id}=${page.pageNumber+1}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
