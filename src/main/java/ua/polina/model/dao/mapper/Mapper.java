package ua.polina.model.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface Mapper<T> {
    T resultSetToEntity(ResultSet resultSet) throws SQLException;

    T makeUnique(Map<Long, T> cache, T entity);
}
