<%@ page import="ua.polina.model.entity.Status" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<style>
    <%@include file="css/app_styles.css"%>
</style>
<head>
    <title><fmt:message key="all.requests"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="header-container">
    <h2><fmt:message key="all.requests"/></h2>
    <a href="?currentPage=${param.currentPage}&recordsPerPage=${param.recordsPerPage}&lang=en"><fmt:message
            key="language.eng"/></a> |
    <a href="?currentPage=${param.currentPage}&recordsPerPage=${param.recordsPerPage}&lang=uk"><fmt:message
            key="language.ua"/></a>
</div>
<table class="table table-hover">
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


<ul class="pagination justify-content-center">
    <c:forEach begin="1" end="${numberOfPages}" var="i">
        <c:choose>
            <c:when test="${currentPage eq i}">
                <li class="page-item">${i}</li>
            </c:when>
            <c:otherwise>
                <li class="page-item">
                    <a class="page-link" href="${command}?currentPage=${i}&recordsPerPage=${recordsPerPage}">${i}</a>
                </li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</ul>
<div class="home">
    <a href="/"><fmt:message key="home"/></a>
</div>
</body>
</html>
