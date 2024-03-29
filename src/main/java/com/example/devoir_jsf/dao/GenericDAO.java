package com.example.devoir_jsf.dao;

import java.util.List;

public interface GenericDAO<T> {
    T create(T t);
    T find(Object id, Class<T> type);
    void delete(Object id, Class<T> type);
    List<T> findAll(Class<T> type);
}
