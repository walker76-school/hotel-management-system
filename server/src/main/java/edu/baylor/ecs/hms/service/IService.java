package edu.baylor.ecs.hms.service;

import java.util.Collection;

public interface IService<T> {

    T get(Long id);
    Collection<T> getAll();
    T save(T t);
    void update(T t) throws Throwable;
    void delete(T t);
    void deleteById(Long id);
}
