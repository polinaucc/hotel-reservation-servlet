<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<h5>Something went wrong:(</h5>
<h2><fmt:message key="${smthError}"/></h2>
<a href="/"><fmt:message key="home"/></a>
</body>
</html>

