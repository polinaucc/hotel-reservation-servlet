<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.polina.model.entity.RoomType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="request.form"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="text-center" data-gr-c-s-loaded="true">
<style>
    <%@include file="css/app_styles.css"%>
</style>
<div class="container">
    <section id="content">
        <form action="/add-request" method="post">
            <h3 class="h3 mb-3 font-weight-normal"><fmt:message key="request.form"/></h3>
            <jsp:include page="language-fragment.jsp"/>
            <c:if test="${not empty argumentError}">
                <fmt:message key="${argumentError}"/>
            </c:if>
            <div class="form-group">
                <label for="room"><fmt:message key="select.appartmant.class"/></label>
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
            <c:choose>
                <c:when test="${not empty error}">
                    <div class="error">
                        <fmt:message key="${error}"/>
                    </div>
                    <div class="form-group">
                        <label for="checkInDate"><fmt:message key="check.in.date"/></label>
                        <input class="form-control" type="date" placeholder="<fmt:message key="check.in.date"/>"
                               required="" id="checkInDate" name="check_in_date"
                               pattern="dd-MM-yyyy" value="<%=request.getParameter("check_in_date")%>"/>
                    </div>
                    <div class="form-group">
                        <label for="checkOutDate"><fmt:message key="check.out.date"/></label>
                        <input class="form-control" type="date" placeholder="<fmt:message key="check.out.date"/>"
                               required="" id="checkOutDate"
                               name="check_out_date" pattern="dd-MM-yyyy"
                               value="<%=request.getParameter("check_out_date")%>"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="form-group">
                        <label for="checkInDate2"><fmt:message key="check.in.date"/></label>
                        <input class="form-control" type="date" placeholder="<fmt:message key="check.in.date"/>"
                               required="" id="checkInDate2"
                               name="check_in_date" pattern="dd-MM-yyyy"/>
                    </div>
                    <div class="form-group">
                        <label for="checkOutDate2"><fmt:message key="check.out.date"/></label> <input
                            class="form-control" type="date" placeholder="<fmt:message key="check.out.date"/>"
                            required="" id="checkOutDate2"
                            name="check_out_date" pattern="dd-MM-yyyy"/>
                    </div>
                </c:otherwise>
            </c:choose>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="apply"/></button>
        </form>
    </section>
</div>
</body>
</html>
