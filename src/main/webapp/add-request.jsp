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
</head>
<body>
<div class="container">
    <section id="content">
        <form action="/add-request" method="post">
            <h1><fmt:message key="request.form"/></h1>
            <fmt:message key="${argumentError}"/>
            <div class="col-lg-6">
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
            <c:choose>
                <c:when test="${not empty error}">
                    ${error}
                    <div>
                        <input type="date" placeholder="<fmt:message key="check.in.date"/>" required="" id="checkInDate" name="check_in_date"
                               pattern="dd-MM-yyyy" value="<%=request.getParameter("check_in_date")%>"/>
                    </div>
                    <div>
                        <input type="date" placeholder="<fmt:message key="check.out.date"/>" required="" id="checkOutDate"
                               name="check_out_date" pattern="dd-MM-yyyy"
                               value="<%=request.getParameter("check_out_date")%>"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <input type="date" placeholder="<fmt:message key="check.in.date"/>" required="" id="checkInDate2"
                               name="check_in_date" pattern="dd-MM-yyyy"/>
                    </div>
                    <div>
                        <input type="date" placeholder="<fmt:message key="check.out.date"/>" required="" id="checkOutDate2"
                               name="check_out_date" pattern="dd-MM-yyyy"/>
                    </div>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="<fmt:message key="apply"/>"/>
        </form>
    </section>
</div>
</body>
</html>
