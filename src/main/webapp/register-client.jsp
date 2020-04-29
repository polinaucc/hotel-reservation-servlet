<%@ page import="ua.polina.model.entity.RoomType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <form action="/register" method="post">
            <h1>Registration form</h1>
            <div>
                <input type="text" placeholder="Email" required="" id="email" name="email"/>
            </div>
            <div>
                <input type="text" placeholder="Username" required="" id="username" name="username"/>
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="text" placeholder="First name" required="" id="firstname" name="first_name"/>
            </div>
            <div>
                <input type="text" placeholder="Middle name" required="" id="middlename" name="middle_name"/>
            </div>
            <div>
                <input type="text" placeholder="Last name" required="" id="lastname" name="last_name"/>
            </div>
            <div>
                <input type="text" placeholder="Passport" required="" id="passport" name="passport"
                       pattern="^[А-Я]{2}[0-9]{6}$"/>
            </div>
            <div>
                <input type="date" placeholder="Birthday" required="" id="birthday" name="birthday"/>
            </div>
            <input type="submit" value="Sign up"/>
        </form>
    </section>
</div>
</body>
</html>