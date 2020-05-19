<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="all.requests"/></title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<h2>My Requests</h2>
<table border="1">
    <thead>
    <tr>
        <th><fmt:message key="room.type"/></th>
        <th><fmt:message key="count.of.persons"/></th>
        <th><fmt:message key="count.of.beds"/></th>
        <th><fmt:message key="check.in.date"/></th>
        <th><fmt:message key="check.out.date"/></th>
        <th><fmt:message key="status"/></th>
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
                    <a href="reservation-info?id=${req.id}"><fmt:message key="check.reservation"/></a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
