package com.taim.taimbackendservice.manager;

import java.util.List;

public interface ManagerBase<T> {
    List<T> getAll();
    T getById(long id);
    T save(T t);
    T update(T t);

}
