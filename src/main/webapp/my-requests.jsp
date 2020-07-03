<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="all.requests"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<style>
    <%@include file="css/app_styles.css"%>
</style>
<h3 class="h3 mb-3 font-weight-normal"><fmt:message key="all.requests"/></h3>
<div class="lang2">
    <jsp:include page="language-fragment.jsp"/>
</div>

<table class="table table-hover">
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
            <td><fmt:message key="${req.description.roomType.getDisplayValue()}"/></td>
            <td>${req.description.countOfPersons}</td>
            <td>${req.description.countOfBeds}</td>
            <td>${req.checkInDate}</td>
            <td>${req.checkOutDate}</td>
            <td><fmt:message key="${req.status.getMessage()}"/></td>
            <td>
                <c:if test="${req.status eq 'Accepted'}">
                    <a href="reservation-info?id=${req.id}"><fmt:message key="check.reservation"/></a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>
