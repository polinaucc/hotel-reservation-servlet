package ua.polina.model.dao;

import ua.polina.model.entity.Description;
import ua.polina.model.entity.RoomType;

import java.util.Optional;

public interface DescriptionDao extends Dao<Description> {
    Optional<Description> findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
            RoomType type, int countOfPersons, int countOfBeds);
}
