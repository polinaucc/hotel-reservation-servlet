<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>
        <fmt:message key="home"/>
    </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="#"><strong>Hotel</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
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

                <c:if test="${fn:contains(principals.authorities, 'ADMIN')}">
                    <li class="nav-item">
                        <a class="nav-link" href="/add-room"><fmt:message key="admin.add.room"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/add-description"><fmt:message key="admin.add.new.description"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/get-requests"><fmt:message key="admin.view.requests"/></a>
                    </li>
                </c:if>
                <c:if test="${fn:contains(principals.authorities, 'CLIENT')}">

                    <li class="nav-item">
                        <a class="nav-link" href="/my-requests"><fmt:message key="view.requests"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/add-request"><fmt:message key="add.new.request"/></a>
                    </li>
            </c:if>
            </ul>
            <jsp:include page="language-fragment.jsp"/>
        </div>
    </nav>
</header>
<c:if test="${not empty principals}">
    <h2><fmt:message key="hello"/> ${principals.username} </h2>
</c:if>
</body>
</html>
