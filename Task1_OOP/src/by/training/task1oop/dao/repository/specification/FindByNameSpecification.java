package by.training.task1oop.dao.repository.specification;

import by.training.task1oop.bean.entity.Plane;

public class FindByNameSpecification implements  FindSpecification<Plane> {
    /**
     * name we are search a plane with.
     */
    private String name;

    /**
     * @param newName name.
     */
    public FindByNameSpecification(final String newName) {
        this.name = newName;
    }
    /**
     * determines which property the object in the list will be searched by.
     * @param plane object to check if it satisfy the property of specification
     * @return does obj satisfy the property
     */
    @Override
    public boolean isSatisfiedBy(final Plane plane) {
        return plane.getName().equals(name);
    }
}
