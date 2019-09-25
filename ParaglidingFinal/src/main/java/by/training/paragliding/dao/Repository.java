package by.training.paragliding.dao;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.Specification;

import java.util.List;


public interface Repository<T> {
    /**
     * Returns object by it's id.
     *
     * @param id unique object identifier
     * @return object by it's id
     * @throws DaoException if something goes wrong
     */
    T readById(int id) throws DaoException;

    /**
     * Adds object to repository.
     *
     * @param object object to add
     * @return is object was added
     * @throws DaoException if something goes wrong
     */
    boolean add(T object) throws DaoException;

    /**
     * Deletes object from repository.
     *
     * @param object object to delete
     * @return true if object was deleted
     * @throws DaoException if something goes wrong
     */
    boolean delete(T object) throws DaoException;

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     * @throws DaoException if something goes wrong
     */
    boolean isEmpty() throws DaoException;

    /**
     * Updates all fields of the object with the current values.
     *
     * @param object object to update
     * @return true if object was updated, false if not.
     * @throws DaoException if something goes wrong
     */
    boolean update(T object) throws DaoException;

    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     * @throws DaoException if something goes wrong
     */
    List<T> query(Specification specification) throws DaoException;
}
