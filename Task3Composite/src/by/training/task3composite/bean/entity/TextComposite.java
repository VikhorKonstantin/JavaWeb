package by.training.task3composite.bean.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class TextComposite implements TextComponent {
    /**
     * List of child-components.
     */
    private List<TextComponent> componentList = new ArrayList<>();
    private ComponentType componentType;

    public TextComposite(final ComponentType newComponentType) {
        componentType = newComponentType;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    /**
     * Returns component from componentList via index.
     * @param index index of component
     * @return component via index
     */
    @Override
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
    
    public int numberOfComponents() {
        return componentList.size();
    }

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
