package com.teamviewer.service;

import java.util.List;
import java.util.Optional;

public interface CrudService<T> {

    List<T> getAll();

    Optional<T> get(long id);

    T create(T object);

    T update(T object);

    void delete(long id);

}
