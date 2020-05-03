<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Requests</title>
</head>
<body>
<h2>Requests</h2>
<table border="1">
    <thead>
    <tr>
        <th>Client</th>
        <th>Room type</th>
        <th>Count of persons</th>
        <th>Count of beds</th>
        <th>Check in date</th>
        <th>Check out date</th>
        <th>Status</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${requests}" var="request">
        <tr>
            <td>${request.client.firstName} ${request.client.middleName} ${request.client.lastName}</td>
            <td>${request.description.roomType}</td>
            <td>${request.description.countOfPersons}</td>
            <td>${request.description.countOfBeds}</td>
            <td>${request.checkInDate}</td>
            <td>${request.checkOutDate}</td>
            <td>${request.status}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>
