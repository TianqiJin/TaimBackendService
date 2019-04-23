package com.taim.taimbackendservice.service;

import java.util.List;

public interface IBaseService<T> {
    List<T> getAll();
    T getById(long id);
    T save(T t);
    T update(T t);

}
