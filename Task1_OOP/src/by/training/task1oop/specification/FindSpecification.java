package by.training.task1oop.specification;

public interface FindSpecification<T> extends Specification {
    /**
     * determines which property the object in the list will be searched by.
     * @param obj object to check if it satisfy the property of specification
     * @return does obj satisfy the property
     */
    boolean isSatisfiedBy(T obj);
}
