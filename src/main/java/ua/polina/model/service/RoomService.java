package ua.polina.model.service;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.RoomDao;
import ua.polina.model.dto.RoomDto;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveRoom(RoomDto roomDto, Description description) {
        try (RoomDao roomDao = daoFactory.createRoomDao()) {
            Room room = new Room();
            room.setRoomNumber(roomDto.getRoomNumber());
            room.setDescription(description);
            roomDao.create(room);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Room> getRoomsByDescription(Description description) {
        try (RoomDao roomDao = daoFactory.createRoomDao()) {
            return roomDao.findByDescription(description.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Optional<Room> getRoomById(Long id) {
        try (RoomDao roomDao = daoFactory.createRoomDao()) {
            return Optional.of(roomDao.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
