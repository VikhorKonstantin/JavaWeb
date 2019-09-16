package by.training.sqltask.dao.repository;

import by.training.sqltask.dao.exception.DaoException;
import by.training.sqltask.dao.repository.specification.Specification;

import java.util.List;


public interface Repository<T> {
    /**
     * @param id unique object ID.
     * @return object by it's id
     */
    T readById(int id) throws DaoException;

    /**
     * Add object to repository.
     * @param object plane to add
     * @return is object was added
     */
    boolean add(T object) throws DaoException;

    /**
     * Delete object from repository.
     * @param object object to delete
     * @return true if object was deleted
     */
    boolean delete(T object) throws DaoException;

    /**
     * @return is repository empty.
     */
    boolean isEmpty() throws DaoException;

    /**
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    List<T> query(Specification specification) throws DaoException;
}
