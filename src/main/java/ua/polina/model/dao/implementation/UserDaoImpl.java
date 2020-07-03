package ua.polina.model.dao.implementation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.dao.UserRoleDao;
import ua.polina.model.dao.mapper.UserMapper;
import ua.polina.model.entity.Role;
import ua.polina.model.entity.User;
import ua.polina.model.exception.DataExistsException;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) throws DataExistsException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                throw new DataExistsException("email.or.username.exists");
            }
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

    }

    @Override
    public void update(User entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(4, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }

    }

    @Override
    public User findById(Long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            return findUsersByPreparedStatement(preparedStatement).get(0);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_FIND_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            if (findUsersByPreparedStatement(preparedStatement).isEmpty()) {
                return Optional.empty();
            } else return Optional.of(findUsersByPreparedStatement(preparedStatement).get(0));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_FIND_ALL)) {
            return findUsersByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<User> findAll(Integer offset, Integer limit) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_FIND_ALL_PAGINATION)) {
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);
            return findUsersByPreparedStatement(preparedStatement);
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

    private void fillPreparedStatement(User entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.getEmail());
        preparedStatement.setString(2, entity.getUsername());
        preparedStatement.setString(3, entity.getPassword());
    }

    private List<User> findUsersByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        Map<Long, User> users = new HashMap<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        UserMapper userMapper = new UserMapper();
        UserRoleDao userRoleDao = daoFactory.createUserRoleDao();

        while (resultSet.next()) {
            User user = userMapper.resultSetToEntity(resultSet);
            List<Role> userRoles = userRoleDao.findRolesByUser(user.getId());
            user.setAuthorities(new HashSet<>(userRoles));
            userMapper.makeUnique(users, user);
        }
        userRoleDao.close();
        return new ArrayList<>(users.values());
    }
}
