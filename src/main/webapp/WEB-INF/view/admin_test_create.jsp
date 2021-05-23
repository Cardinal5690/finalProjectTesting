<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 21.05.2021
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><fmt:message key="text.title"/></title>
    <jsp:include page="/WEB-INF/parts/head.jsp"/>
</head>
<body>
<div class="container">
    <form action="${pageContext.request.contextPath}/testing/admin/subject/test/create/action" method="post">
        <input type="hidden" value="${subjectId}" name="subjectId">
        <input type="hidden" name="title" value="${title}"/>
        <p>
            <label>
                <input type="text" required placeholder="<fmt:message key="text.test.write.test.name"/>" name="testName"/>
            </label>
        </p>
        <p>
            <label>
                <input type="text" pattern="EASY|MIDDLE|DIFFICULT" placeholder="<fmt:message key="text.test.write.complexity"/>"
                       name="complexity"/>
            </label>
        </p>
        <p>
            <label>
                <input type="text" pattern="/^(\d){1,3}$/g)" placeholder="<fmt:message key="text.test.write.time"/>"
                       name="time"/>
            </label>
        </p>
        <div>
            <input type="submit" class="btn" value="<fmt:message key="text.subjects.create"/>"/>
            <c:if test="${requestScope.updateError}">
            <div>
                <fmt:message key="text.update.error"/>
            </div>
            </c:if>
    </form>
</div>
<form method="post"
      action="${pageContext.request.contextPath}/testing/admin/subject/test">
    <label>
        <input type="hidden" value="${subjectId}" name="subjectId">
        <button class="btn" name="title" value="${title}" type="submit"><fmt:message key="text.test.name"/> </button>
    </label>
</form>
<jsp:include page="/WEB-INF/parts/foot.jsp"/>
</body>
</html>