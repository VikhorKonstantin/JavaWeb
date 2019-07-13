package by.training.task1oop.specification;

import by.training.task1oop.entity.Plane;

import java.util.Comparator;

public class SortByNameSpecification implements SortSpecification<Plane> {
    /**
     * determines which property the list will be sorted by.
     * @return Comparator<T>
     */
    @Override
    public Comparator<Plane> sortBy() {
        return Comparator.comparing(Plane::getName);
    }
}
