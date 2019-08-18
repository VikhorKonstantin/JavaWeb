package by.training.task3composite.bean.entity;

public interface TextComponent {
    /**
     * Add new component.
     *
     * @param newComponent new component
     * @return true(false) if component was added(not)
     */
    boolean add(TextComponent newComponent);

    /**
     * Remove component.
     *
     * @param newComponent component to remove
     * @return true(false) if component was removed(not)
     */
    boolean remove(TextComponent newComponent);

    /**
     * Returns component from componentList via index.
     *
     * @param index index of component
     * @return component via index
     */
    TextComponent getComponent(int index);

    /**
     * Returns number of components.
     *
     * @return number of components
     */
    int numberOfComponents();

    /**
     * Returns string representation of TextComponent.
     *
     * @return string representation of TextComponent
     */
    String compose();

    /**
     * Returns type of component.
     *
     * @return type of component
     */
    ComponentType getComponentType();
}
