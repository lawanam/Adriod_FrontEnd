package com.salary.salary.salary.repository;

import java.util.Set;

 
public interface IRepo<E,ID> {
    E findById(ID id);

    E add(E entity);

    E update(E entity);

    E remove(E entity);

    Set<E> findAll();

    int removeAll();
}
