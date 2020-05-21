<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title><fmt:message key="check.bill"/></title>
</head>
<body>
<div class="language">
    <a href="?id=${param.id}&lang=en"><fmt:message key="language.eng"/></a> |
    <a href="?id=${param.id}&lang=uk"><fmt:message key="language.ua"/></a>
</div>
<div>
    <h1><fmt:message key="room.number"/></h1>
    <h1>${reservation.room.roomNumber}</h1>

    <h1><fmt:message key="reservation.sum"/></h1>
    <h1>${reservation.sum}</h1>
</div>
</body>
</html>
