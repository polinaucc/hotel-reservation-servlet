<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page isELIgnored="false" %>
<html>
<body>
<h2>
    <ul class="d-inline-flex nav">
        <li class="nav-item ">
            <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Contacts</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/login">
                <c:if test="${empty principals}">Log in</c:if>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/register">
                <c:if test="${empty principals}">Register</c:if>
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/logout">
                    <c:if test="${not empty principals}">Log out</c:if>
            </a>
        </li>
    </ul>
    <c:if test="${not empty principals}">
        <h2> Hello, ${principals.username} </h2>
    </c:if>

    <c:if test="${fn:contains(principals.authorities, 'ADMIN')}">
        <ul>
            <li>
                <a href="/add-room">Add room</a>
            </li>
            <li>
                <a href="/add-description">Add description</a>
            </li>
            <li>
                <a href="/get-requests">View all requests</a>
            </li>
        </ul>
    </c:if>

    <c:if test="${fn:contains(principals.authorities, 'CLIENT')}">
        <ul>
            <li>
                <a href="/my-requests">My requests</a>
            </li>
            <li>
                <a href="/add-request">Add request</a>
            </li>
        </ul>
    </c:if>
</h2>
</body>
</html>
