package ua.polina.model.dao.mapper;

import ua.polina.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements Mapper<User> {
    @Override
    public User resultSetToEntity(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setUsername(resultSet.getString("username"));
        return user;
    }

    @Override
    public User makeUnique(Map<Long, User> cache, User entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }

}
