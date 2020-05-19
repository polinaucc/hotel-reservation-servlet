<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html lang="en">
<head>
    <title>
        <fmt:message key="home"/>
    </title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<ul class="d-inline-flex nav">
    <li class="nav-item ">
        <a class="nav-link" href="#"><fmt:message key="home"/> <span class="sr-only">(current)</span></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#"><fmt:message key="contacts"/></a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/login">
            <c:if test="${empty principals}"><fmt:message key="login.log.in"/></c:if>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/register">
            <c:if test="${empty principals}"><fmt:message key="register"/></c:if>
        </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="/logout">
            <c:if test="${not empty principals}"><fmt:message key="log.out"/></c:if>
        </a>
    </li>
</ul>
<c:if test="${not empty principals}">
    <h2><fmt:message key="hello"/> ${principals.username} </h2>
</c:if>

<c:if test="${fn:contains(principals.authorities, 'ADMIN')}">
    <ul>
        <li>
            <a href="/add-room"><fmt:message key="admin.add.room"/></a>
        </li>
        <li>
            <a href="/add-description"><fmt:message key="admin.add.new.description"/></a>
        </li>
        <li>
            <a href="/get-requests"><fmt:message key="admin.view.requests"/></a>
        </li>
    </ul>
</c:if>

<c:if test="${fn:contains(principals.authorities, 'CLIENT')}">
    <ul>
        <li>
            <a href="/my-requests"><fmt:message key="view.requests"/></a>
        </li>
        <li>
            <a href="/add-request"><fmt:message key="add.new.request"/></a>
        </li>
    </ul>
</c:if>
</body>
</html>
