package by.training.task1oop.dao.repository.specification;

import java.util.Comparator;

public interface SortSpecification<T> extends Specification {
    /**
     * determines which property the list will be sorted by.
     * @return Comparator<T>
     */
    Comparator<T> sortBy();
}
