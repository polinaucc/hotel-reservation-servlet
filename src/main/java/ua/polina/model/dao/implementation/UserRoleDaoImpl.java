package ua.polina.model.dao.implementation;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.polina.model.dao.DaoFactory;
import ua.polina.model.dao.UserDao;
import ua.polina.model.dao.UserRoleDao;
import ua.polina.model.entity.Role;
import ua.polina.model.entity.User;
import ua.polina.model.entity.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    private final Connection connection;
    private final DaoFactory daoFactory= DaoFactory.getInstance();
    private static Logger LOGGER = LogManager.getLogger(UserRole.class);

    public UserRoleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserRole entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_ROLE_INSERT)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    @Override
    public void update(UserRole entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_ROLE_UPDATE)) {
            fillPreparedStatement(entity, preparedStatement);
            preparedStatement.setLong(3, entity.getUser().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("SQL State: " + e.getSQLState() + e.getMessage());
        }

    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public UserRole findById(Long id) {
        return null;
    }

    @Override
    public List<UserRole> findAll() {
        return null;
    }

    @Override
    public List<UserRole> findAll(Integer offset, Integer limit) {
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
    public List<Role> findRolesByUser(Long userId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlConstants.SQL_USER_ROLE_FIND_BY_USER)) {
            preparedStatement.setLong(1, userId);
            return findRolesByPreparedStatement(preparedStatement);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void fillPreparedStatement(UserRole entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setLong(1, entity.getUser().getId());
        preparedStatement.setString(2, entity.getRole().toString());
    }

    private List<Role> findRolesByPreparedStatement(PreparedStatement preparedStatement) throws Exception {
        List<Role> roles = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        UserDao userDao = daoFactory.createUserDao();

        while (resultSet.next()){
            Role role = Role.valueOf(resultSet.getString("authorities"));
            roles.add(role);
        }
        userDao.close();
        return roles;
    }
}
