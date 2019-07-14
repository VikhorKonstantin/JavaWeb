package by.training.task1oop.dao.repository;

import by.training.task1oop.bean.entity.Plane;
import by.training.task1oop.specification.Specification;

import java.util.List;


public interface Repository<T> {
    /**
     * @param id unique plane ID.
     * @return plane by it's id
     */
    Plane readById(long id);

    /**
     * Add plane to repository.
     * @param plane plane to add
     */
    void add(Plane plane);

    /**
     * Delete plane from repository.
     * @param plane plane to delete
     */
    void delete(Plane plane);

    /**
     * @return list of all objects in repository.
     */
    List<T> getAll();

    /**
     * @return is repository empty.
     */
    boolean isEmpty();

    /**
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    List<T> query(Specification specification);
}
