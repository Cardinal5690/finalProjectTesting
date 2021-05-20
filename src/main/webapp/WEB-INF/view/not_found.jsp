<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 14.05.2021
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored = "false" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<head>
    <title><fmt:message key="text.title"/></title>
    <jsp:include page="/WEB-INF/parts/head.jsp"/>
    <title>Error 404</title>
</head>
<body>
<div class="container">
    <H1 class="flow-text"> <fmt:message key="text.error.page"/> </H1>
    <form method="post"
          action="${pageContext.request.contextPath}/testing/main">
        <input type="hidden">
        <button class="btn" type="submit"><fmt:message key="text.main"/></button>
    </form>
</div>
<jsp:include page="/WEB-INF/parts/foot.jsp"/>
</body>
</html>
