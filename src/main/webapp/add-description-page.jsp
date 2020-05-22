<%@ page import="ua.polina.model.entity.RoomType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title><fmt:message key="description.form"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="text-center" data-gr-c-s-loaded="true">
<style>
    <%@include file="css/app_styles.css"%>
</style>
<c:if test="${not empty error}">
    <fmt:message key="${error}"/>
</c:if>
<div class="container">
    <form action="/add-description" method="post">
        <h3 class="h3 font-weight-normal name"><fmt:message key="description.form"/></h3>
        <jsp:include page="language-fragment.jsp"/>
        <div class="form-group">
            <label for="room"><fmt:message key="room.select.description"/></label>
            <select class="form-control" id="room" name="roomType">
                <option value="<%=RoomType.BUSINESS%>"><%=RoomType.BUSINESS.toString()%>
                </option>
                <option value="<%=RoomType.BALCONY%>"><%=RoomType.BALCONY.toString()%>
                </option>
                <option value="<%=RoomType.ECONOMY%>"><%=RoomType.ECONOMY.toString()%>
                </option>
                <option value="<%=RoomType.LUXURY%>"><%=RoomType.LUXURY.toString()%>
                </option>
            </select>
        </div>
        <div class="form-group">
            <label for="countOfPersons"><fmt:message key="select.count.of.persons"/></label>
            <select class="form-control" id="countOfPersons" name="persons">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </div>
        <div class="form-group">
            <label for="countOfBeds"><fmt:message key="select.count.of.beds"/></label>
            <select class="form-control" id="countOfBeds" name="beds">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
            </select>
        </div>
        <div>
            <label for="cost"><fmt:message key="placeholder.cost.per.night"/></label>
            <input type="number" required="" id="cost" name="costPerNight"/>
        </div>
        <input type="submit" value="<fmt:message key="admin.add.new.description"/>"/>
    </form>
    <a href="/"><fmt:message key="home"/></a>
</div>
</body>
</html>
