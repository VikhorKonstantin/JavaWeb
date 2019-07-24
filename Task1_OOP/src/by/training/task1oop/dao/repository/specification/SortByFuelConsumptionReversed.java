package by.training.task1oop.dao.repository.specification;

import by.training.task1oop.bean.entity.Plane;

import java.util.Comparator;

public class SortByFuelConsumptionReversed implements SortSpecification<Plane> {
    /**
     * determines which property the list will be sorted by.
     * @return Comparator<T>
     */
    @Override
    public Comparator<Plane> sortBy() {
        return Comparator.comparing(Plane::getFuelConsumption).reversed();
    }
}
