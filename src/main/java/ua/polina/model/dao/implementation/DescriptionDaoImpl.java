package ua.polina.model.dao.implementation;

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
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void update(Description entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public Description findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findDescriptionsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Description> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_FIND_ALL)) {
            return findDescriptionsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private void fillPreparedStatement(Description entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getRoomType().toString());
        preparedStatement.setInt(2, entity.getCountOfPersons());
        preparedStatement.setInt(3, entity.getCountOfBeds());
        preparedStatement.setBigDecimal(4, entity.getCostPerNight());
    }

    private List<Description> findDescriptionsByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, Description> descriptions = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        DescriptionMapper descriptionMapper = new DescriptionMapper();
        while (resultSet.next()) {
            Description description = descriptionMapper.resultSetToEntity(resultSet);
            description = descriptionMapper.makeUnique(descriptions, description);
        }
        return new ArrayList<>(descriptions.values());
    }

    @Override
    public Optional<Description> findDescriptionByRoomTypeAndCountOfPersonsAndCountOfBeds(
            RoomType type, int countOfPersons, int countOfBeds) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_DESCRIPTION_FIND_BY_ROOM_TYPE_COUNT_BEDS_COUNT_PERSONS)) {
            return Optional.of(findDescriptionsByPreparedStatement(preparedStatement).get(0));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
