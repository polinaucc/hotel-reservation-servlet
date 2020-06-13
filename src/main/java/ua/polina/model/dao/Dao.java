package ua.polina.model.dao;

import ua.polina.model.exception.DataExistsException;

import java.util.List;

public interface Dao<T> extends AutoCloseable {
    void create(T entity) throws DataExistsException;
    void update(T entity);
    void delete(Long id);
    T findById(Long id);
    List<T> findAll();
    List<T> findAll(Integer offset, Integer limit);
}
