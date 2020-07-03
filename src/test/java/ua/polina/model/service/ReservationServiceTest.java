package ua.polina.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.ReservationDao;
import ua.polina.model.entity.*;
import ua.polina.model.exception.DataExistsException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReservationServiceTest {

    Request request;

    Room room;

    @Mock
    DaoFactory daoFactory;

    @Mock
    ReservationDao reservationDao;

    @InjectMocks
    ReservationService reservationService;

    Reservation reservation;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Field field = ReservationService.class.getDeclaredField("daoFactory");
        FieldHelper.makeNonFinal(field);
        field.setAccessible(true);
        field.set(reservationService, daoFactory);

        Description description = new Description();
        description.setId(1L);
        description.setRoomType(RoomType.BUSINESS);
        description.setCountOfPersons(2);
        description.setCountOfBeds(1);
        description.setCostPerNight(new BigDecimal(1500));


        request = new Request();
        request.setId(1L);
        request.setDescription(description);
        request.setClient(new Client());
        request.setCheckInDate(LocalDate.of(2020, 7, 5));
        request.setCheckOutDate(LocalDate.of(2020, 7, 9));
        request.setStatus(Status.New_request);

        room = new Room();
        room.setId(1L);
        room.setDescription(description);
        room.setRoomNumber("11r");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setRequest(request);
        reservation.setRoom(room);
        reservation.setSum(new BigDecimal(4500));
    }

    @Test
    public void saveReservation() throws DataExistsException {
        when(daoFactory.createReservationDao()).thenReturn(reservationDao);
        reservationService.saveReservation(request, room);
        verify(reservationDao, times(1)).create(any(Reservation.class));
    }

    @Test
    public void getReservationByRequest() {
        when(daoFactory.createReservationDao()).thenReturn(reservationDao);
        Optional<Reservation> expectedReservation = Optional.of(reservation);
        when(reservationDao.findByRequest(request.getId())).thenReturn(expectedReservation);
        Optional<Reservation> actualReservation = reservationService.getReservationByRequest(request);
        Assert.assertEquals(expectedReservation, actualReservation);
    }

    @Test
    public void getAllReservations() {
        when(daoFactory.createReservationDao()).thenReturn(reservationDao);
        List<Reservation> expectedList = Collections.singletonList(reservation);
        when(reservationDao.findAll()).thenReturn(expectedList);
        List<Reservation> actualList = reservationService.getAllReservations();
        Assert.assertEquals(expectedList, actualList);
    }
}