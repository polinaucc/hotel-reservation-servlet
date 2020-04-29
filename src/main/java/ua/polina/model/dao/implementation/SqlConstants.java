package ua.polina.model.dao.implementation;

public interface SqlConstants {
    String SQL_CLIENT_INSERT = "INSERT into client (" +
            "first_name, " +
            "middle_name, " +
            "last_name, " +
            "passport, " +
            "birthday, " +
            "user_id) " +
            "VALUES (?,?,?,?,?,?)";

    String SQL_CLIENT_UPDATE = "UPDATE client SET (" +
            "first_name=?, " +
            "middle_name=?, " +
            "last_name=?, " +
            "passport=?, " +
            "birthday=?, " +
            "user_id=?) " +
            "WHERE id=?";

    String SQL_CLIENT_DELETE_BY_ID = "DELETE FROM client WHERE id=?";

    String SQL_CLIENT_FIND_BY_ID = "SELECT * FROM client WHERE id=?";

    String SQL_CLIENT_FIND_ALL = "SELECT * FROM client";

    String SQL_CLIENT_FIND_BY_USER = "SELECT * FROM client WHERE user_id = ?";

    String SQL_USER_INSERT = " INSERT into users (" +
            "email, " +
            "password, " +
            "username) " +
            "VALUES(?,?,?)";

    String SQL_USER_FIND_BY_ID = "SELECT * FROM users WHERE id=?";

    String SQL_USER_ROLE_INSERT = "INSERT into user_role (" +
            "user_id, " +
            "authorities) " +
            "VALUES(?,?)";

    String SQL_USER_ROLE_FIND_BY_USER = "SELECT * FROM user_role WHERE user_id=?";

    String SQL_DESCRIPTION_INSERT = "INSERT into description (" +
            "room_type, " +
            "count_persons, " +
            "count_beds, " +
            "cost) " +
            "VALUES(?,?,?,?)";

    String SQL_DESCRIPTION_UPDATE = "UPDATE description SET (" +
            "room_type=?, " +
            "count_persons=?, " +
            "count_beds=?, " +
            "cost=?) " +
            "WHERE id=?";

    String SQL_DESCRIPTION_FIND_ALL = "SELECT * FROM description";

    String SQL_DESCRIPTION_DELETE_BY_ID = "DELETE FROM description WHERE id=?";

    String SQL_DESCRIPTION_FIND_BY_ROOM_TYPE_COUNT_BEDS_COUNT_PERSONS = "SELECT * FROM description WHERE " +
            "room_type=? AND count_beds=? AND count_persons=?";

    String SQL_DESCRIPTION_FIND_BY_ID = "SELECT * FROM description WHERE id=?";

    String SQL_REQUEST_INSERT = " INSERT into request (" +
            "client_id, " +
            "description_id, " +
            "in_date, " +
            "out_date, " +
            "status) " +
            "VALUES(?,?,?,?,?)";

    String SQL_REQUEST_FIND_ALL = "SELECT * FROM request";

    String SQL_REQUEST_FIND_BY_ID = "SELECT * FROM request WHERE id = ?";

    String SQL_REQUEST_UPDATE = "UPDATE request SET (" +
            "client_id=?, " +
            "description_id=?, " +
            "in_date=?, " +
            "out_date=?, " +
            "status=?) " +
            "WHERE id=?";

    String SQL_REQUEST_FIND_BY_CLIENT = "SELECT * FROM request WHERE client_id = ?";

    String SQL_RESERVATION_INSERT = "INSERT into reservation (request_id, room_id, sum) VALUES(?,?,?)";

    String SQL_RESERVATION_FIND_BY_REQUEST = "SELECT * FROM reservation WHERE request_id = ?";

    String SQL_RESERVATION_FIND_ALL = "SELECT * FROM reservation";

    String SQL_ROOM_INSERT = "INSERT into room (room)number, description_id)";

    String SQL_ROOM_FIND_BY_DESCRIPTION = "SELECT * FROM room WHERE description_id=?";

    String SQL_ROOM_FIND_BY_ID = "SELECT * FROM room WHERE id = ?";

    String SQL_ROOM_FIND_ALL = "SELECT * FROM room";
}