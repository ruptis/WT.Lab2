package by.bsuir.wtlab2.utils;

import by.bsuir.wtlab2.exception.DAOException;

@FunctionalInterface
public interface CountConsumer<T> {
    int count(T t) throws DAOException;
}
