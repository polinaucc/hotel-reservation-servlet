package ua.polina.model.dao;

import java.util.List;

public interface Dao<T> extends AutoCloseable {
    void create(T entity);
    void update(T entity);
    void delete(Long id);
    T findById(Long id);
    List<T> findAll();
}
