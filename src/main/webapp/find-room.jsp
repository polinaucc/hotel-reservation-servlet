<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="find.room"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<div class="container">
    <section id="content">
        <form action="/add-reservation" method="post">
            <h3 class="h3 mb-3 font-weight-normal">Choose room</h3>
            <div class="form-group">
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
