package ua.polina.model.dao.implementation;

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

    //TODO:implement method
    @Override
    public void update(UserRole entity) {

    }

    //TODO:implement method
    @Override
    public void delete(Long id) {

    }

    //TODO:implement method
    @Override
    public UserRole findById(Long id) {
        return null;
    }

    //TODO:implement method
    @Override
    public List<UserRole> findAll() {
        return null;
    }

    //TODO: to implement
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
