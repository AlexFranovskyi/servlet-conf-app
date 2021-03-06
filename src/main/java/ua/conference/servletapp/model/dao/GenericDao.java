package ua.conference.servletapp.model.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T> extends AutoCloseable {
    T create (T entity);
    Optional<T> findById(long id);
    List<T> findAll();
    void update(T entity);
    boolean delete(long id);
    void close();
}
