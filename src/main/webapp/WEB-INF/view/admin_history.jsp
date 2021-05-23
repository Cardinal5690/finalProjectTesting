<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 20.05.2021
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message key="text.title"/></title>
</head>
<body>
<jsp:include page="/WEB-INF/parts/head.jsp"/>
<div class="container">
    <table>
        <thead>
        <tr>
        <th><fmt:message key="text.id"/></th>
        <th><fmt:message key="text.result"/></th>
        <th><fmt:message key="text.test.id"/></th>
        <br/>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${results}" var="result">
            <td>${result.getUserId()}</td>
            <td>${result.getResult()}</td>
            <td>${result.getTestId()}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <jsp:include page="pagination.jsp"/>
</div>
</body>
<jsp:include page="/WEB-INF/parts/foot.jsp"/>
</html>
