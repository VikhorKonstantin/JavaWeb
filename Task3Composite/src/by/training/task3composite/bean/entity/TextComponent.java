package by.training.task3composite.bean.entity;

public interface TextComponent {
    /**
     * Add new component.
     * @param newComponent new component
     * @return true(false) if component was added(not)
     */
    boolean add(TextComponent newComponent);

    /**
     * Remove component.
     * @param newComponent component to remove
     * @return true(false) if component was removed(not)
     */
    boolean remove(TextComponent newComponent);

    /**
     * Returns String representation of component.
     * @return
     */
    @Override
    String toString();
}
