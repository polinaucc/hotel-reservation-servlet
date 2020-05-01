<%@ page import="ua.polina.model.entity.RoomType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add request</title>
</head>
<body>
<div class="container">
    <section id="content">
        <form action="/add-request" method="post">
            <h1>Request form</h1>
            <div class="col-lg-6">
                <label for="room">Select room type</label>
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
                <input type="date" placeholder="CheckInDate" required="" id="checkInDate" name="check_in_date" pattern="dd-MM-yyyy"/>
            </div>
            <div>
                <input type="date" placeholder="Checkout date" required="" id="checkOutDate" name="check_out_date" pattern="dd-MM-yyyy"/>
            </div>
            <input type="submit" value="Apply"/>
        </form>
    </section>
</div>
</body>
</html>
