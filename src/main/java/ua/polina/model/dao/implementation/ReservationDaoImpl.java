package ua.polina.model.dao.implementation;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.RequestDao;
import ua.polina.model.dao.ReservationDao;
import ua.polina.model.dao.RoomDao;
import ua.polina.model.dao.mapper.ReservationMapper;
import ua.polina.model.entity.Request;
import ua.polina.model.entity.Reservation;
import ua.polina.model.entity.Room;

import java.sql.*;
import java.util.*;

public class ReservationDaoImpl implements ReservationDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public ReservationDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Reservation entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                SqlConstants.SQL_RESERVATION_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

    }

    //TODO: implement method
    @Override
    public void update(Reservation entity) {

    }

    //TODO: implement method
    @Override
    public void delete(Long id) {

    }

    //TODO: implement method
    @Override
    public Reservation findById(Long id) {
        return null;
    }

    @Override
    public List<Reservation> findAll() {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_RESERVATION_FIND_ALL)){
            return findReservationsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO: to implement
    @Override
    public List<Reservation> findAll(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillPreparedStatement(Reservation entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getRequest().getId());
        preparedStatement.setLong(2, entity.getRoom().getId());
        preparedStatement.setBigDecimal(3, entity.getSum());
    }

    private List<Reservation> findReservationsByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, Reservation> reservations = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        ReservationMapper reservationMapper = new ReservationMapper();

        RoomDao roomDao = daoFactory.createRoomDao();
        RequestDao requestDao = daoFactory.createRequestDao();

        while (resultSet.next()) {
            Reservation reservation = reservationMapper.resultSetToEntity(resultSet);
            reservation = reservationMapper.makeUnique(reservations, reservation);

            Room room = roomDao.findById(resultSet.getLong("room_id"));
            Request request = requestDao.findById(resultSet.getLong("request_id"));

            reservation.setRoom(room);
            reservation.setRequest(request);
        }

        roomDao.close();
        requestDao.close();
        return new ArrayList<>(reservations.values());
    }

    @Override
    public Optional<Reservation> findByRequest(Long requestId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_RESERVATION_FIND_BY_REQUEST)) {
            return Optional.of(findReservationsByPreparedStatement(preparedStatement).get(0));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
