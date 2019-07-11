package by.training.task1oop.repository;

import by.training.task1oop.entity.Plane;


public interface Repository {
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
}
