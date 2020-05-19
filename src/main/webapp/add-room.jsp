<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Room</title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<div class="container">
    <section id="content">
        <form action="add-room" method="post">
            <h1><fmt:message key="room.form"/></h1>
            <div>
                <input type="text" placeholder="<fmt:message key="room.number"/>" required="" id="roomNumber" name="room_number"/>
            </div>
            <div class="col-lg-6">
                <select class="form-control" id="room" name="description_id">
                    <c:forEach var="description" items="${descriptions}">
                        <option value="${description.id}"><c:out value="${description}"/></option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="<fmt:message key="room.add"/>"/>
        </form>
    </section>
</div>
</body>
</html>
