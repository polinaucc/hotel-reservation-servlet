package ua.polina.model.dao;

import ua.polina.model.entity.Room;

import java.util.List;

public interface RoomDao extends Dao<Room> {
    List<Room> findByDescription(Long descriptionId);
}
