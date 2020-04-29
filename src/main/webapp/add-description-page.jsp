<%@ page import="ua.polina.model.entity.RoomType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="css/logIn.css">
    <title>Request</title>
</head>
<body>

<div class="container">
    <section id="content">
        <form action="/add-description" method="post">
            <h1>Description form</h1>
            <div class="col-lg-6">
                <label for="room">Select count of persons</label>
                <select class="form-control" id="room" name="roomType">
                    <option value="<%=RoomType.BUSINESS%>"><%=RoomType.BUSINESS.toString()%></option>
                    <option value="<%=RoomType.BALCONY%>"><%=RoomType.BALCONY.toString()%></option>
                    <option value="<%=RoomType.ECONOMY%>"><%=RoomType.ECONOMY.toString()%></option>
                    <option value="<%=RoomType.LUXURY%>"><%=RoomType.LUXURY.toString()%></option>
                </select>
            </div>
            <div class="col-lg-6">
                <label for="countOfPersons">Select count of persons</label>
                <select class="form-control" id="countOfPersons" name="persons">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
            <div class="col-lg-6">
                <label for="countOfBeds">Select count of beds</label>
                <select class="form-control" id="countOfBeds" name="beds">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select>
            </div>
            <div>
                <label for="cost">Cost per night</label>
                <input type="number" required="" id="cost" name="costPerNight"/>
            </div>
            <input type="submit" value="Add"/>
        </form>
    </section>
</div>
</body>
</html>