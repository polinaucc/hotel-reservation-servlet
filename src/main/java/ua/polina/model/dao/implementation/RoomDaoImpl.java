package ua.polina.model.dao.implementation;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.DescriptionDao;
import ua.polina.model.dao.RoomDao;
import ua.polina.model.dao.mapper.RoomMapper;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDaoImpl implements RoomDao {
    private final Connection connection;
    private final DaoFactory daoFactory= DaoFactory.getInstance();

    public RoomDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Room entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_ROOM_INSERT, Statement.RETURN_GENERATED_KEYS)) {
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
    public void update(Room entity) {

    }

    //TODO: implement method
    @Override
    public void delete(Long id) {

    }

    @Override
    public Room findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_ROOM_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findRoomsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_ROOM_FIND_ALL)) {
            return findRoomsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //TODO: to implement
    @Override
    public List<Room> findAll(Integer offset, Integer limit) {
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Room> findByDescription(Long descriptionId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_ROOM_FIND_BY_DESCRIPTION)) {
            preparedStatement.setLong(1, descriptionId);
            return findRoomsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void fillPreparedStatement(Room entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getRoomNumber());
        preparedStatement.setLong(2, entity.getDescription().getId());
    }

    private List<Room> findRoomsByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, Room> rooms = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        RoomMapper roomMapper = new RoomMapper();
        DescriptionDao descriptionDao = daoFactory.createDescriptionDao();

        while (resultSet.next()){
            Room room = roomMapper.resultSetToEntity(resultSet);
            room = roomMapper.makeUnique(rooms, room);

            Description description = descriptionDao.findById(resultSet.getLong("description_id"));

            room.setDescription(description);
        }
        descriptionDao.close();
        return new ArrayList<>(rooms.values());
    }
}
