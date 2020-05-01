<%--
  Created by IntelliJ IDEA.
  User: polin
  Date: 01.05.2020
  Time: 20:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="/login" method="post">
            <h1>Login form</h1>
            <div>
                <input type="text" placeholder="Username" required="" id="username" name="username"/>
            </div>
            <div>
                <input type="password" placeholder="Password" required="" id="password" name="password"/>
            </div>
            <div>
                <input type="submit" value="Log in"/>
            </div>
        </form>
    </section>
</div>

</body>
</html>
