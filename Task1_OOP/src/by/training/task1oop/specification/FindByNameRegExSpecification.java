package by.training.task1oop.specification;

import by.training.task1oop.bean.entity.Plane;

public class FindByNameRegExSpecification implements FindSpecification<Plane> {
    /**
     * Regular expression that must match
     * the name of a plane you are looking for.
     */
    private String regex;

    /**
     * @param newRegex regex.
     */
    public FindByNameRegExSpecification(final String newRegex) {
        this.regex = newRegex;
    }
    /**
     * determines which property the object in the list will be searched by.
     * @param plane object to check if it satisfy the property of specification
     * @return does obj satisfy the property
     */
    @Override
    public boolean isSatisfiedBy(final Plane plane) {
        return plane.getName().matches(regex);
    }
}
