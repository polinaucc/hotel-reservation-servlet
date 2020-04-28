package ua.polina.model.dao.mapper;

import ua.polina.model.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ReservationMapper implements Mapper<Reservation> {
    @Override
    public Reservation resultSetToEntity(ResultSet resultSet) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(resultSet.getLong("id"));
        reservation.setSum(resultSet.getBigDecimal("sum"));
        return reservation;
    }

    @Override
    public Reservation makeUnique(Map<Long, Reservation> cache, Reservation entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
