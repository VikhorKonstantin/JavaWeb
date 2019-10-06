package by.training.paragliding.service;

import by.training.paragliding.service.exception.ServiceException;

import java.util.List;

public interface Service<T> {

    List<T> find(String property, Object... values)
            throws ServiceException;

    /**
     * @param <P> param type.
     * @param <R> return type.
     * @param <E> exception type.
     */
    @FunctionalInterface
    interface ThrowingFunction<P, R, E extends Exception> {
        R apply(P t) throws E;
    }
}
