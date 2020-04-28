package ua.polina.model.service;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.ReservationDao;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Reservation;
import ua.polina.model.entity.Room;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

public class ReservationService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public void saveReservation(Request request, Room room){
        try(ReservationDao reservationDao = daoFactory.createReservationDao()) {
            Reservation reservation = new Reservation();
            reservation.setRequest(request);
            reservation.setRoom(room);
            reservation.setSum(room.getDescription().getCostPerNight()
                    .multiply(new BigDecimal(DAYS.between(request.getCheckInDate(), request.getCheckOutDate()))));
            reservationDao.create(reservation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Reservation> getReservationByRequest(Request request){
        try(ReservationDao reservationDao = daoFactory.createReservationDao()) {
           return reservationDao.findByRequest(request.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Reservation> getAllReservations(){
        try(ReservationDao reservationDao = daoFactory.createReservationDao()){
            return reservationDao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
