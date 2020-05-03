<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <h2> Hello, ${principals.username} </h2></c:if>

</h2>
</body>
</html>
