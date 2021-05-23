<%--
  Created by IntelliJ IDEA.
  User: igor5
  Date: 20.05.2021
  Time: 18:13
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
    <form method="post" action="${pageContext.request.contextPath}/testing/admin/subject/test/question">
        <input type="hidden" name="title" value="${title}"/>
        <input type="hidden" name="subjectId" value="${subjectId}"/>
        <table>
            <thead>
            <tr>
                <th><fmt:message key="text.test.name"/></th>
                <th><fmt:message key="text.test.complexity"/></th>
                <th><fmt:message key="text.test.time"/></th>
                <th></th>
                <br/>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${databaseList}" var="test">
                <tr>
                    <td> ${test.getTestName()}</td>
                    <td> ${test.getComplexity()}</td>
                    <td> ${test.getTime()}</td>
                    <td>
                        <p>
                            <label>
                                <input type="radio" required="required" name="testId" value="${test.getId()}"/><span> </span>
                            </label>
                        </p>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <input class="btn" type="submit" value="<fmt:message key="text.create.question"/>">
        <button class="btn" name="testId" formaction="${pageContext.request.contextPath}/testing/admin/subject/test/delete" value="${test.getId()}" type="submit"><fmt:message key="text.admin.test.delete"/> </button>
    </form>
    <form method="post"
          action="${pageContext.request.contextPath}/testing/admin/subject/test/create">
        <input type="hidden" name="title" value="${title}"/>
        <button class="btn" name="subjectId" value="${subjectId}" type="submit"><fmt:message key="text.subjects.create"/> </button>
    </form>
</div>
</body>
</html>
