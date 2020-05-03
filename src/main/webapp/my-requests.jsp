<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>My Requests</title>
</head>
<body>
<h2>My Requests</h2>
<table border="1">
    <thead>
    <tr>
        <th>Room type</th>
        <th>Count of persons</th>
        <th>Count of beds</th>
        <th>Check in date</th>
        <th>Check out date</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requests}" var="req">
        <tr>
            <td>${req.description.roomType}</td>
            <td>${req.description.countOfPersons}</td>
            <td>${req.description.countOfBeds}</td>
            <td>${req.checkInDate}</td>
            <td>${req.checkOutDate}</td>
            <td>${req.status}</td>
            <td>
                <c:if test="${req.status eq 'Accepted'}">
                    <a href="reservation-info?id=${req.id}">View reservation</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
