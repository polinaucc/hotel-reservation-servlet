package ua.polina.model.dao;

import ua.polina.model.entity.Request;

import java.util.List;

public interface RequestDao extends Dao<Request> {
    List<Request> findByClient(Long clientId);
}
