package by.training.task3composite.bean.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements TextComponent {
    /**
     * List of child-components.
     */
    private List<TextComponent> componentList = new ArrayList<>();
    /**
     * Type of the component.
     */
    private ComponentType componentType;

    /**
     * Constructs a new TextComponent by the ComponentType.
     *
     * @param newComponentType the ComponentType of a new TextComponent
     */
    public TextComposite(final ComponentType newComponentType) {
        componentType = newComponentType;
    }

    /**
     * Returns the ComponentType of the TextComponent.
     *
     * @return the ComponentType of the TextComponent
     */
    public ComponentType getComponentType() {
        return componentType;
    }

    /**
     * Returns number of children-components.
     *
     * @return number of children-components
     */
    public int numberOfComponents() {
        return componentList.size();
    }

    /**
     * Returns component from componentList via index.
     *
     * @param index index of component
     * @return component via index
     */
    @Override
    public TextComponent getComponent(final int index) {
        return componentList.get(index);
    }

    /**
     * Add new component.
     *
     * @param newComponent new component
     * @return true(false) if component was added(not)
     */
    @Override
    public boolean add(final TextComponent newComponent) {
        return componentList.add(newComponent);
    }

    /**
     * Remove component.
     *
     * @param newComponent component to remove
     * @return true(false) if component was removed(not)
     */
    @Override
    public boolean remove(final TextComponent newComponent) {
        return componentList.remove(newComponent);
    }

    /**
     * Returns String representation of the TextComponent.
     *
     * @return String representation of the TextComponent
     */
    @Override
    public String compose() {
        var components = new ArrayList<TextComponent>();
        var numberOfComponents = componentList.size();
        for (int i = 0; i < numberOfComponents; ++i) {
            components.add(getComponent(i));
        }
        var type = getComponentType();
        StringJoiner joiner = new StringJoiner(type.getDelimiter(),
                type.getPrefix(), type.getSuffix());
        components.forEach(c -> joiner.add(c.compose()));
        return joiner.toString();
    }
}
