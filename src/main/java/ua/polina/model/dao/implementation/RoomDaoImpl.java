package ua.polina.model.dao.implementation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger LOGGER = LogManager.getLogger(RoomDaoImpl.class);

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

    @Override
    public void update(Room entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_ROOM_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }

    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_ROOM_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
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

    @Override
    public List<Room> findAll(Integer offset, Integer limit) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_ROOM_FIND_ALL_PAGINATION)) {

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            return findRoomsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
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

        while (resultSet.next()) {
            Room room = roomMapper.resultSetToEntity(resultSet);
            room = roomMapper.makeUnique(rooms, room);

            Description description = descriptionDao.findById(resultSet.getLong("description_id"));

            room.setDescription(description);
        }
        descriptionDao.close();
        return new ArrayList<>(rooms.values());
    }
}
