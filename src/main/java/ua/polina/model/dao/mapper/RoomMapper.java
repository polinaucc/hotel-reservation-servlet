package ua.polina.model.dao.mapper;

import ua.polina.model.entity.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoomMapper implements Mapper<Room> {
    @Override
    public Room resultSetToEntity(ResultSet resultSet) throws SQLException {
        Room room = new Room();
        room.setId(resultSet.getLong("id"));
        room.setRoomNumber(resultSet.getString("room_number"));
        return room;
    }

    @Override
    public Room makeUnique(Map<Long, Room> cache, Room entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
