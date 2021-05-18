package com.testing.model.service;

import java.util.List;

public interface GeneralService<T>{
    T create(T entity);
    T findById(int id);
    List<T> findAll();
    void update(T entity);
    void delete(int id);
}
