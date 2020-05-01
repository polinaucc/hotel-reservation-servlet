package ua.polina.model.dao.mapper;

import ua.polina.model.entity.Description;
import ua.polina.model.entity.RoomType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DescriptionMapper implements Mapper<Description> {
    @Override
    public Description resultSetToEntity(ResultSet resultSet) throws SQLException {
        Description description = new Description();
        description.setId(resultSet.getLong("id"));
        description.setRoomType(RoomType.valueOf(resultSet.getString("room_type")));
        description.setCountOfPersons(resultSet.getInt("count_persons"));
        description.setCountOfBeds(resultSet.getInt("count_beds"));
        description.setCostPerNight(resultSet.getBigDecimal("cost"));
        return description;
    }

    @Override
    public Description makeUnique(Map<Long, Description> cache, Description entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
