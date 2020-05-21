<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body class="text-center" data-gr-c-s-loaded="true">
<style>
    <%@include file="css/app_styles.css"%>
</style>
<jsp:include page="index.jsp"/>
<div class="container">
    <div class="error">
        <c:if test="${not empty illegalCred}">
            <fmt:message key="${illegalCred}"/>
        </c:if>
    </div>
    <form class="form-signin" action="/login" method="post">
        <h3 class="h3 mb-3 font-weight-normal"><fmt:message key="login.form"/></h3>
        <input type="text" placeholder="<fmt:message key="placeholder.username"/>" required=""
               name="username" class="form-control"/>
        <input type="password" placeholder="<fmt:message key="placeholder.password"/>" required=""
               name="password" class="form-control"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="login.log.in"/></button>
    </form>
</div>

</body>
</html>
