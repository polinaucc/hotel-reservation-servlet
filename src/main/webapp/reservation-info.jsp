<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Reservation info</title>
</head>
<body>
<div>
    <h1>Room number: </h1>
    <h1>${reservation.room.roomNumber}</h1>

    <h1>Sum of the reservation</h1>
    <h1>${reservation.sum}</h1>
</div>
</body>
</html>
