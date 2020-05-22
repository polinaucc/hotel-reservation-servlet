<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="register"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>

<body class="text-center" data-gr-c-s-loaded="true">
<jsp:include page="index.jsp"/>
<style>
    <%@include file="css/app_styles.css"%>
</style>
<div class="container">
    <form class="form-signin" name="registration-form" action="/register" method="post">
        <h3 class="h3 font-weight-normal name"><fmt:message key="registration.form"/></h3>
        <c:choose>
            <c:when test="${not empty errors}">
                <div class="error">
                    <c:if test="${not empty errors['email'][0]}"><fmt:message key="${errors['email'][0]}"/></c:if>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.email"/>" required title="Email error"
                           name="email" class="form-control"
                           value="<%=request.getParameter("email")%>"/>
                </div>
                <div class="error">
                    <c:if test="${not empty errors['username'][0]}"><fmt:message key="${errors['username'][0]}"/></c:if>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.username"/>" required=""
                           name="username" class="form-control"
                           value="<%=request.getParameter("username")%>"/>
                </div>
                <div class="error">
                    <c:if test="${not empty errors['password'][0]}"><fmt:message key="${errors['password'][0]}"/></c:if>
                </div>
                <div>
                    <input type="password" placeholder="<fmt:message key="placeholder.password"/>" required=""
                           class="form-control" name="password"/>
                </div>
                <div class="error">
                    <c:if test="${not empty errors['first_name'][0]}"><fmt:message
                            key="${errors['first_name'][0]}"/></c:if>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.first.name"/>" required=""
                           class="form-control" name="first_name"
                           value="<%=request.getParameter("first_name")%>"/>
                </div>
                <div class="error">
                    <c:if test="${not empty errors['middle_name'][0]}"><fmt:message key="${errors['middle_name'][0]}"/></c:if>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.second.name"/>" required=""
                           class="form-control" name="middle_name"
                           value="<%=request.getParameter("middle_name")%>"/>
                </div>
                <div class="error">
                    <C:if test="${not empty errors['last_name'][0]}"><fmt:message
                            key="${errors['last_name'][0]}"/></C:if>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.last.name"/>" required=""
                           class="form-control" name="last_name"
                           value="<%=request.getParameter("last_name")%>"/>
                </div>
                <div class="error">
                    <c:if test="${not empty errors['passport'][0]}"><fmt:message key="${errors['passport'][0]}"/></c:if>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.passport"/>" required=""
                           name="passport" class="form-control"
                           pattern="^[А-Я]{2}[0-9]{6}$" value="<%=request.getParameter("passport")%>"/>
                </div>
                <div class="error">
                    <c:if test="${not empty errors['birthday'][0]}"><fmt:message key="${errors['birthday'][0]}"/></c:if>
                </div>
                <div>
                    <input type="date" placeholder="<fmt:message key="placeholder.birthday"/>" required=""
                           name="birthday" class="form-control"
                           value="<%=request.getParameter("birthday")%>"/>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.email"/>" required title="Email error"
                           class="form-control" name="email"/>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.username"/>" required=""
                           class="form-control" name="username"/>
                </div>
                <div>
                    <input type="password" placeholder="<fmt:message key="placeholder.password"/>" required=""
                           class="form-control" name="password"/>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.first.name"/>" required=""
                           class="form-control" name="first_name"/>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.second.name"/>" required=""
                           class="form-control" name="middle_name"/>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.second.name"/>" required=""
                           class="form-control" name="last_name"/>
                </div>
                <div>
                    <input type="text" placeholder="<fmt:message key="placeholder.passport"/>" required=""
                           class="form-control" name="passport"
                           pattern="^[А-Я]{2}[0-9]{6}$"/>
                </div>
                <div>
                    <input type="date" placeholder="<fmt:message key="placeholder.birthday"/>" required=""
                           class="form-control" name="birthday"/>
                </div>
            </c:otherwise>
        </c:choose>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="register"/></button>
    </form>
</div>
</body>
</html>