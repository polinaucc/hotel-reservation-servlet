<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Find room</title>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="/add-reservation" method="post">
            <h1>Choose room</h1>
            <div class="col-lg-6">
                <select class="form-control" id="room" name="room_id">
                    <c:forEach var="room" items="${rooms}">
                        <option value="${room.id}"><c:out value="${room.roomNumber}"/></option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Create reservation"/>
        </form>
    </section>
</div>

</body>
</html>
