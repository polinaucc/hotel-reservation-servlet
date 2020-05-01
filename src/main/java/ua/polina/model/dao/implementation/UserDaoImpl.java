package ua.polina.model.dao.implementation;

import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.dao.UserRoleDao;
import ua.polina.model.dao.mapper.UserMapper;
import ua.polina.model.entity.Role;
import ua.polina.model.entity.User;
import ua.polina.model.entity.UserRole;

import java.sql.*;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private final Connection connection;
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_INSERT, Statement.RETURN_GENERATED_KEYS)) {
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

    //TODO:implement method
    @Override
    public void update(User entity) {

    }

    //TODO:implement method
    @Override
    public void delete(Long id) {

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
        try(PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_FIND_BY_USERNAME)){
            preparedStatement.setString(1, username);
            return Optional.of(findUsersByPreparedStatement(preparedStatement).get(0));
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    //TODO: implement method
    @Override
    public List<User> findAll() {
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
            List<UserRole> userRoles = userRoleDao.findRolesByUser(user.getId());
            List<Role> roles = new ArrayList<>();
            for (UserRole ur : userRoles) {
                roles.add(ur.getRole());
            }
            user.setAuthorities(new HashSet<>(roles));
            userMapper.makeUnique(users, user);
        }
        userRoleDao.close();
        return new ArrayList<>(users.values());

    }
}
