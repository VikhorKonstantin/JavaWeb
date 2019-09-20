package by.training.paragliding.dao;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.sql.Specification;

import java.util.List;


public interface Repository<T> {
    /**
     * Returns object by it's id.
     *
     * @param id unique object identifier
     * @return object by it's id
     */
    T readById(int id) throws DaoException;

    /**
     * Adds object to repository.
     *
     * @param object object to add
     * @return is object was added
     */
    boolean add(T object) throws DaoException;

    /**
     * Deletes object from repository.
     *
     * @param object object to delete
     * @return true if object was deleted
     */
    boolean delete(T object) throws DaoException;

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     */
    boolean isEmpty() throws DaoException;

    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    List<T> query(Specification specification) throws DaoException;
}
