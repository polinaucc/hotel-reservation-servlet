package ua.polina.model.dao.implementation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.model.dao.ClientDao;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.dao.mapper.ClientMapper;
import ua.polina.model.entity.Client;
import ua.polina.model.entity.User;
import ua.polina.model.exception.DataExistsException;

import java.sql.Date;
import java.sql.*;
import java.util.*;

public class ClientDaoImpl implements ClientDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class);

    public ClientDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Client entity) throws DataExistsException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                SqlConstants.SQL_CLIENT_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new DataExistsException("passport.exists");
            }
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public void update(Client entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_CLIENT_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(10, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_CLIENT_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public Client findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_CLIENT_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findClientsByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return null;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Client> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_CLIENT_FIND_ALL)) {
            return findClientsByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Client> findAll(Integer offset, Integer limit) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_CLIENT_FIND_ALL_PAGINATION)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            return findClientsByPreparedStatement(preparedStatement);
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

    private void fillPreparedStatement(Client entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getFirstNameUk());
        preparedStatement.setString(3, entity.getMiddleName());
        preparedStatement.setString(4, entity.getMiddleNameUk());
        preparedStatement.setString(5, entity.getLastName());
        preparedStatement.setString(6, entity.getLastNameUk());
        preparedStatement.setString(7, entity.getPassport());
        preparedStatement.setDate(8, Date.valueOf(entity.getBirthday()));
        preparedStatement.setLong(9, entity.getUser().getId());
    }

    private List<Client> findClientsByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, Client> clients = new HashMap<>();

        ResultSet resultSet = preparedStatement.executeQuery();

        ClientMapper clientMapper = new ClientMapper();

        UserDao userDao = daoFactory.createUserDao();

        while (resultSet.next()) {
            Client client = clientMapper.resultSetToEntity(resultSet);
            client = clientMapper.makeUnique(clients, client);

            User user = userDao.findById(resultSet.getLong("user_id"));

            client.setUser(user);
        }
        userDao.close();
        return new ArrayList<>(clients.values());
    }

    @Override
    public Optional<Client> findByUser(Long userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_CLIENT_FIND_BY_USER)) {
            preparedStatement.setLong(1, userId);
            return Optional.of(findClientsByPreparedStatement(preparedStatement).get(0));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
