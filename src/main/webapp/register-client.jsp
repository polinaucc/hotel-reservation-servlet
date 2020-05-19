<%@ page import="ua.polina.model.entity.RoomType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="register"/></title>
</head>
<body>
<jsp:include page="language-fragment.jsp"/>
<div class="container">
    <section id="content">
        <form name="registration-form" action="/register" method="post">
            <h1><fmt:message key="registration.form"/></h1>
            <c:choose>
                <c:when test="${not empty errors}">
                    <fmt:message key="${errors['email'][0]}"/>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.email"/>" required title="Email error" id="email2" name="email"
                               value="<%=request.getParameter("email")%>"/>
                    </div>
                    <fmt:message key="${errors['username'][0]}"/>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.username"/>" required="" id="username" name="username"
                               value="<%=request.getParameter("username")%>"/>
                    </div>
                    <fmt:message key="${errors['password'][0]}"/>
                    <div>
                        <input type="password" placeholder="<fmt:message key="placeholder.password"/>" required="" id="password" name="password"/>
                    </div>
                    <fmt:message key="${errors['first_name'][0]}"/>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.first.name"/>" required="" id="firstname" name="first_name"
                               value="<%=request.getParameter("first_name")%>"/>
                    </div>
                    <fmt:message key="${errors['middle_name'][0]}"/>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.second.name"/>" required="" id="middlename" name="middle_name"
                               value="<%=request.getParameter("middle_name")%>"/>
                    </div>
                    <fmt:message key="${errors['last_name'][0]}"/>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.last.name"/>" required="" id="lastname" name="last_name"
                               value="<%=request.getParameter("last_name")%>"/>
                    </div>
                    <fmt:message key="${errors['passport'][0]}"/>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.passport"/>" required="" id="passport" name="passport"
                               pattern="^[А-Я]{2}[0-9]{6}$" value="<%=request.getParameter("passport")%>"/>
                    </div>
                    <fmt:message key="${errors['birthday'][0]}"/>
                    <div>
                        <input type="date" placeholder="<fmt:message key="placeholder.birthday"/>" required="" id="birthday" name="birthday"
                               value="<%=request.getParameter("birthday")%>"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.email"/>" required title="Email error" id="email" name="email"/>
                    </div>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.username"/>" required="" id="username2" name="username"/>
                    </div>
                    <div>
                        <input type="password" placeholder="<fmt:message key="placeholder.password"/>" required="" id="password2" name="password"/>
                    </div>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.first.name"/>" required="" id="firstname2" name="first_name"/>
                    </div>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.second.name"/>" required="" id="middlename2" name="middle_name"/>
                    </div>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.second.name"/>" required="" id="lastname2" name="last_name"/>
                    </div>
                    <div>
                        <input type="text" placeholder="<fmt:message key="placeholder.passport"/>" required="" id="passport2" name="passport"
                               pattern="^[А-Я]{2}[0-9]{6}$"/>
                    </div>
                    <div>
                        <input type="date" placeholder="<fmt:message key="placeholder.birthday"/>" required="" id="birthday2" name="birthday"/>
                    </div>
                </c:otherwise>
            </c:choose>
            <input type="submit" value="<fmt:message key="sign.up"/>"/>
        </form>
    </section>
</div>
</body>
</html>