<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 20.05.2021
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<nav aria-label="Navigation for countries">
    <ul class="pagination">
        <c:if test="${requestScope.currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="<%request.getRequestURI();%>
                                     ?currentPage=${requestScope.currentPage - 1}"><fmt:message
                    key="jsp.pagination.prev.button"/></a>
            </li>
        </c:if>
        <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
            <c:choose>
                <c:when test="${requestScope.currentPage eq i}">
                    <li class="active"><a class="page-link">
                            ${i} <span class="sr-only"></span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="waves-effect"><a class="page-link"
                                                href="<%request.getRequestURI();%>?currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="<%request.getRequestURI();%>?currentPage=${requestScope.currentPage + 1}"><fmt:message
                    key="jsp.pagination.next.button"/></a>
            </li>
        </c:if>
    </ul>
</nav>