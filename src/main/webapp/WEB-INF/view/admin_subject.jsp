<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 19.05.2021
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="language" value="${not empty sessionScope.language ? sessionScope.language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title><fmt:message key="text.title"/></title>
    <jsp:include page="/WEB-INF/parts/head.jsp"/>
</head>
<body>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/testing/admin/subject/test">
        <table>
            <thead>
            <tr>
                <th><fmt:message key="text.subject.name"/></th>
                <th></th>
                <br/>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${databaseList}" var="subject">
                <tr>
                    <td> ${subject.getTitle()}</td>
                    <td>
                        <p>
                            <label>
                                <input type="hidden" value="${subject.getId()}" name="subjectId">
                                <input type="radio" required="required" name="title" value="${subject.getTitle()}"/><span> </span>
                            </label>
                        </p>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
        <input class="btn" type="submit" value="<fmt:message key="text.choose"/>">
    </form>
</div>
</body>
</html>
