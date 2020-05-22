<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Room</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="text-center" data-gr-c-s-loaded="true">
<style>
    <%@include file="css/app_styles.css"%>
</style>

<div class="container">
    <section id="content">
        <form action="add-room" method="post">
            <h1><fmt:message key="room.form"/></h1>
            <jsp:include page="language-fragment.jsp"/>
            <div class="form-group">
                <input class="form-control" type="text" placeholder="<fmt:message key="room.number"/>" required="" id="roomNumber" name="room_number"/>
            </div>
            <div class="form-group">
                <select class="form-control" id="room" name="description_id">
                    <c:forEach var="description" items="${descriptions}">
                        <option value="${description.id}"><c:out value="${description}"/></option>
                    </c:forEach>
                </select>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="room.add"/></button>
        </form>
    </section>
    <a href="/" ><fmt:message key="home"/></a>
</div>
</body>
</html>
