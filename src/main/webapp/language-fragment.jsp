<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <a href="?lang=en"><fmt:message key="language.eng"/></a> |
    <a href="?lang=uk"><fmt:message key="language.ua"/></a>
</head>
<body>

</body>
</html>
