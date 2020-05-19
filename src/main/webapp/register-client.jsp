<%@ page import="ua.polina.model.entity.RoomType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/logIn.css">
    <title>Register</title>
</head>
<body>

<div class="container">
    <section id="content">
        <form name="registration-form" action="/register" method="post">
            <h1>Registration form</h1>
            <c:choose>
                <c:when test="${not empty errors}">
                    ${errors['email']}
                    <div>
                        <input type="text" placeholder="Email" required title="Email error" id="email2" name="email"
                               value=<%=request.getParameter("email")%>/>
                    </div>
                    ${errors['username']}
                    <div>
                        <input type="text" placeholder="Username" required="" id="username" name="username"
                               value="<%=request.getParameter("username")%>"/>
                    </div>
                    ${errors['password']}
                    <div>
                        <input type="password" placeholder="Password" required="" id="password" name="password"/>
                    </div>
                    ${errors['first_name']}
                    <div>
                        <input type="text" placeholder="First name" required="" id="firstname" name="first_name"
                               value="<%=request.getParameter("first_name")%>"/>
                    </div>
                    ${errors['middle_name']}
                    <div>
                        <input type="text" placeholder="Middle name" required="" id="middlename" name="middle_name"
                               value="<%=request.getParameter("middle_name")%>"/>
                    </div>
                    ${errors['last_name']}
                    <div>
                        <input type="text" placeholder="Last name" required="" id="lastname" name="last_name"
                               value="<%=request.getParameter("last_name")%>"/>
                    </div>
                    ${errors['passport']}
                    <div>
                        <input type="text" placeholder="Passport" required="" id="passport" name="passport"
                               pattern="^[А-Я]{2}[0-9]{6}$" value="<%=request.getParameter("passport")%>"/>
                    </div>
                    ${errors['birthday']}
                    <div>
                        <input type="date" placeholder="Birthday" required="" id="birthday" name="birthday"
                               value="<%=request.getParameter("birthday")%>"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <input type="text" placeholder="Email" required title="Email error" id="email" name="email"/>
                    </div>
                    <div>
                        <input type="text" placeholder="Username" required="" id="username2" name="username"/>
                    </div>
                    <div>
                        <input type="password" placeholder="Password" required="" id="password2" name="password"/>
                    </div>
                    <div>
                        <input type="text" placeholder="First name" required="" id="firstname2" name="first_name"/>
                    </div>
                    <div>
                        <input type="text" placeholder="Middle name" required="" id="middlename2" name="middle_name"/>
                    </div>
                    <div>
                        <input type="text" placeholder="Last name" required="" id="lastname2" name="last_name"/>
                    </div>
                    <div>
                        <input type="text" placeholder="Passport" required="" id="passport2" name="passport"
                               pattern="^[А-Я]{2}[0-9]{6}$"/>
                    </div>
                    <div>
                        <input type="date" placeholder="Birthday" required="" id="birthday2" name="birthday"/>
                    </div>
                </c:otherwise>
            </c:choose>

            <input type="submit" value="Sign up"/>
        </form>
    </section>
</div>
</body>
</html>