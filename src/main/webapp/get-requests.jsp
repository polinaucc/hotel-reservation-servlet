<%@ page import="ua.polina.model.entity.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<style>
    li{
        float: left;
        padding: 5px;
    }
    li a{
        display: block;
    }
</style>
<head>
    <title><fmt:message key="all.requests"/></title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<h2>Requests</h2>
<table border="1">
    <thead>
    <tr>
        <th><fmt:message key="client"/></th>
        <th><fmt:message key="room.type"/></th>
        <th><fmt:message key="count.of.persons"/></th>
        <th><fmt:message key="count.of.persons"/></th>
        <th><fmt:message key="check.in.date"/></th>
        <th><fmt:message key="check.out.date"/></th>
        <th><fmt:message key="status"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${entities}" var="request">
        <tr>
            <td>${request.client.firstName} ${request.client.middleName} ${request.client.lastName}</td>
            <td>${request.description.roomType}</td>
            <td>${request.description.countOfPersons}</td>
            <td>${request.description.countOfBeds}</td>
            <td>${request.checkInDate}</td>
            <td>${request.checkOutDate}</td>
            <td>${request.status}</td>
            <td>
                <a href="find-room?id=${request.id}">
                    <c:if test="${request.status eq 'New_request'}">
                        <fmt:message key="find.room"/>
                    </c:if>
                </a>
            </td>
            <td>
                <a href="reservation-info?id=${request.id}">
                    <c:if test="${request.status eq 'Accepted'}">
                        <fmt:message key="check.reservation"/>
                    </c:if>
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


<ul style="list-style-type: none">
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li>${i}</li>
            </c:when>
            <c:otherwise>
                <li>
                    <a href="${command}?currentPage=${i}&recordsPerPage=${recordsPerPage}">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>


</body>
</html>
