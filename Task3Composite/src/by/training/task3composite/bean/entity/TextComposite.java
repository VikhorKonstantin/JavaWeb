package by.training.task3composite.bean.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class TextComposite implements TextComponent {
    /**
     * List of child-components.
     */
    private List<TextComponent> componentList = new ArrayList<>();

    /**
     * Returns component from componentList via index.
     * @param index index of component
     * @return component via index
     */
    public TextComponent getComponent(final int index) {
        return componentList.get(index);
    }

    /**
     * Add new component.
     * @param newComponent new component
     * @return true(false) if component was added(not)
     */
    @Override
    public boolean add(final TextComponent newComponent) {
        return componentList.add(newComponent);
    }

    /**
     * Remove component.
     * @param newComponent component to remove
     * @return true(false) if component was removed(not)
     */
    @Override
    public boolean remove(final TextComponent newComponent) {
        return componentList.remove(newComponent);
    }

    /**
     * Returns number of child-components.
     * @return size of componentList
     */
    public int numberOfComponents() {
        return componentList.size();
    }
}
