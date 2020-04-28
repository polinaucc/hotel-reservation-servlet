package ua.polina.model.dao.mapper;

import ua.polina.model.entity.Request;
import ua.polina.model.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RequestMapper implements Mapper<Request> {
    @Override
    public Request resultSetToEntity(ResultSet resultSet) throws SQLException {
        Request request = new Request();
        request.setId(resultSet.getLong("id"));
        request.setStatus(Status.valueOf(resultSet.getString("status")));
        request.setCheckInDate(resultSet.getDate("in_date").toLocalDate());
        request.setCheckOutDate(resultSet.getDate("out_date").toLocalDate());
        return request;
    }

    @Override
    public Request makeUnique(Map<Long, Request> cache, Request entity) {
        cache.putIfAbsent(entity.getId(), entity);
        return cache.get(entity.getId());
    }
}
