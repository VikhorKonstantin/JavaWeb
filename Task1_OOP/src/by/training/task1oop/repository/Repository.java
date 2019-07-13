package by.training.task1oop.repository;

import by.training.task1oop.entity.Plane;
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
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    List<T> query(Specification specification);
}
