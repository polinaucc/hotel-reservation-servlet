package ua.polina.model.dao.implementation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.DescriptionDao;
import ua.polina.model.dao.mapper.DescriptionMapper;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.RoomType;

import java.sql.*;
import java.util.*;

public class DescriptionDaoImpl implements DescriptionDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger LOGGER = LogManager.getLogger(DescriptionDaoImpl.class);

    public DescriptionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Description entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                SqlConstants.SQL_DESCRIPTION_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public void update(Description entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public Description findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findDescriptionsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Description> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_FIND_ALL)) {
            return findDescriptionsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Description> findAll(Integer offset, Integer limit) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_FIND_ALL_PAGINATION)) {

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            return findDescriptionsByPreparedStatement(preparedStatement);
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

    private void fillPreparedStatement(Description entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getRoomType().toString());
        preparedStatement.setInt(2, entity.getCountOfPersons());
        preparedStatement.setInt(3, entity.getCountOfBeds());
        preparedStatement.setBigDecimal(4, entity.getCostPerNight());
    }

    private List<Description> findDescriptionsByPreparedStatement(PreparedStatement preparedStatement) throws SQLException {
        Map<Long, Description> descriptions = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        DescriptionMapper descriptionMapper = new DescriptionMapper();
        while (resultSet.next()) {
            descriptionMapper.makeUnique(descriptions, descriptionMapper.resultSetToEntity(resultSet));
        }
        return new ArrayList<>(descriptions.values());
    }

    @Override
    public Optional<Description> findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
            RoomType type, int countOfPersons, int countOfBeds) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_FIND_BY_ROOM_TYPE_COUNT_BEDS_COUNT_PERSONS)) {
            preparedStatement.setString(1, type.name());
            preparedStatement.setInt(2, countOfPersons);
            preparedStatement.setInt(3, countOfBeds);
            return Optional.of(findDescriptionsByPreparedStatement(preparedStatement).get(0));
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.empty();
    }
}
