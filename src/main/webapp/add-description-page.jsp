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
</head>
<body class="text-center" data-gr-c-s-loaded="true">
<style>
    <%@include file="css/app_styles.css"%>
</style>
<jsp:include page="language-fragment.jsp"/>
<c:if test="${not empty error}">
    <fmt:message key="${error}"/>
</c:if>
<div class="container">
    <section id="content">
        <form action="/add-description" method="post">
            <h3 class="h3 font-weight-normal name"><fmt:message key="description.form"/></h3>
            <div class="col-lg-6">
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
            <div class="col-lg-6">
                <label for="countOfPersons"><fmt:message key="select.count.of.persons"/></label>
                <select class="form-control" id="countOfPersons" name="persons">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
            <div class="col-lg-6">
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
    </section>
</div>
</body>
</html>
