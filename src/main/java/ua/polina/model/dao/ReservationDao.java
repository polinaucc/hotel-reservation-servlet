package ua.polina.model.dao;

import ua.polina.model.entity.Reservation;

import java.util.Optional;

public interface ReservationDao extends Dao<Reservation> {
    Optional<Reservation> findByRequest(Long requestId);
}
