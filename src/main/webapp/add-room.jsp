<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Room</title>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="add-room" method="post">
            <h1>Room form</h1>
            <div>
                <input type="text" placeholder="Room number" required="" id="roomNumber" name="room_number"/>
            </div>
            <div class="col-lg-6">
                <select class="form-control" id="room" name="description_id">
                    <c:forEach var="description" items="${descriptions}">
                        <option value="${description.id}"><c:out value="${description}"/></option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Add room"/>
        </form>
    </section>
</div>

</body>
</html>
