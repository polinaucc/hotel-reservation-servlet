package ua.polina.model.dao.implementation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.model.dao.ClientDao;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.DescriptionDao;
import ua.polina.model.dao.RequestDao;
import ua.polina.model.dao.mapper.RequestMapper;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.Description;
import ua.polina.model.entity.Request;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestDaoImpl implements RequestDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(RequestDaoImpl.class);

    public RequestDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Request entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_REQUEST_INSERT, Statement.RETURN_GENERATED_KEYS)) {
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
    public void update(Request entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_REQUEST_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_REQUEST_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public Request findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_REQUEST_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findRequestsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Request> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_REQUEST_FIND_ALL)) {
            return findRequestsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Request> findAll(Integer offset, Integer limit) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_REQUEST_FIND_ALL_PAGINATION)) {

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            return findRequestsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
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
    public List<Request> findByClient(Long clientId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_REQUEST_FIND_BY_CLIENT)) {
            preparedStatement.setLong(1, clientId);
            return findRequestsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Integer countAll() {
        try (PreparedStatement statement = connection.prepareStatement(SqlConstants.SQL_REQUEST_COUNT_ALL)) {

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next() ? resultSet.getInt(1) : 0;
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return 0;
        }
    }


    private void fillPreparedStatement(Request entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getClient().getId());
        preparedStatement.setLong(2, entity.getDescription().getId());
        preparedStatement.setDate(3, Date.valueOf(entity.getCheckInDate()));
        preparedStatement.setDate(4, Date.valueOf(entity.getCheckOutDate()));
        preparedStatement.setString(5, entity.getStatus().toString());
    }

    private List<Request> findRequestsByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, Request> requests = new HashMap<>();

        ResultSet resultSet = preparedStatement.executeQuery();

        RequestMapper requestMapper = new RequestMapper();

        ClientDao clientDao = daoFactory.createClientDao();
        DescriptionDao descriptionDao = daoFactory.createDescriptionDao();

        while (resultSet.next()) {
            Request request = requestMapper.resultSetToEntity(resultSet);
            request = requestMapper.makeUnique(requests, request);

            Client client = clientDao.findById(resultSet.getLong("client_id"));
            Description description = descriptionDao.findById(resultSet.getLong("description_id"));

            request.setClient(client);
            request.setDescription(description);
        }
        clientDao.close();
        descriptionDao.close();
        return new ArrayList<>(requests.values());
    }

}
