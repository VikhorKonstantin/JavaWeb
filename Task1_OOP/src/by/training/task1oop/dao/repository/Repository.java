package by.training.task1oop.dao.repository;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.specification.Specification;

import java.util.List;


public interface Repository<T> {
    /**
     * @param id unique object ID.
     * @return object by it's id
     */
    Plane readById(long id);

    /**
     * Add object to repository.
     * @param object plane to add
     * @return is object was added
     */
    boolean add(T object);

    /**
     * Delete object from repository.
     * @param object object to delete
     * @return true if object was deleted
     */
    boolean delete(T object);

    /**
     * @return list of all objects in repository.
     */
    List<T> getAll();

    /**
     * @return is repository empty.
     */
    boolean isEmpty();

    /**
     * clear repository.
     */
    void clear();

    /**
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    List<T> query(Specification specification);
}
