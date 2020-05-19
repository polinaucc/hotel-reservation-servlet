<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="find.room"/></title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<div class="container">
    <section id="content">
        <form action="/add-reservation" method="post">
            <h1>Choose room</h1>
            <div class="col-lg-6">
                <label for="room"><fmt:message key="select.free.room"/></label>
                <select class="form-control" id="room" name="room_id">
                    <c:forEach var="room" items="${rooms}">
                        <option value="${room.id}"><c:out value="${room.roomNumber}"/></option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="<fmt:message key="create.reservation"/>"/>
        </form>
    </section>
</div>
</body>
</html>
