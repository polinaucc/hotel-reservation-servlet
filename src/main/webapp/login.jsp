<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Login</title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<div class="container">
    <section id="content">
        <form action="/login" method="post">
            <h1><fmt:message key="login.form"/></h1>
            <div>
                <input type="text" placeholder="<fmt:message key="placeholder.username"/>" required="" id="username" name="username"/>
            </div>
            <div>
                <input type="password" placeholder="<fmt:message key="placeholder.password"/>" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="submit" value="<fmt:message key="login.log.in"/>"/>
            </div>
        </form>
    </section>
</div>

</body>
</html>
