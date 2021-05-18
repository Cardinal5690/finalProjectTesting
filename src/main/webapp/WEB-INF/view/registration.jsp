<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 14.05.2021
  Time: 15:02
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
    <jsp:include page="/WEB-INF/parts/head.jsp"/>
</head>
<body>
<div class="container">
    <form  action="${pageContext.request.contextPath}/testing/registration/create" align="center"  method="post">
        <p>
            <label>
                <input type="text" pattern="^[A-Z][a-z]{1,20}$" required placeholder="<fmt:message key="text.name"/>" name="name"/>
            </label>
        </p>
        <p>
            <label>
                <input type="text" pattern="^[A-Z][a-z]{1,20}$" required placeholder="<fmt:message key="text.surname"/>" name="surname"/>
            </label>
        </p>
        <p>
            <label>
                <input  type="email" required placeholder="<fmt:message key="text.email"/>" name="email"/>
            </label>
        </p>
        <p>
            <label>
                <input  type="password" required placeholder="<fmt:message key="text.password"/>" name="password"/>
            </label>
        </p>
        <div>
            <input   type="submit" class="btn"   value="<fmt:message key="text.registration"/>">
            <c:if test="${requestScope.registrationError}">
            <div >
                <fmt:message key="text.registration.error"/>
            </div>
            </c:if>
            <c:if test="${requestScope.userExist}">
            <div >
                <fmt:message key="text.user.exist"/>
            </div>
            </c:if>
    </form>
</div>
<jsp:include page="/WEB-INF/parts/foot.jsp"/>
</body>
</html>
