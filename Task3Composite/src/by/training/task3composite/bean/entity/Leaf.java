package by.training.task3composite.bean.entity;

public class Leaf implements TextComponent {
    private ComponentType componentType;
    /**
     * Char symbol of parsed text.
     */
    private char character;

    public Leaf(final ComponentType newComponentType, final char newCharacter) {
        componentType = newComponentType;
        character = newCharacter;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    /**
     * Add new component.
     *
     * @param newComponent new component
     * @return true(false) if component was added(not)
     */
    @Override
    public boolean add(final TextComponent newComponent) {
        return false;
    }

    /**
     * Remove component.
     *
     * @param newComponent component to remove
     * @return true(false) if component was removed(not)
     */
    @Override
    public boolean remove(final TextComponent newComponent) {
        return false;
    }

    /**
     * Returns component from componentList via index.
     *
     * @param index index of component
     * @return component via index
     */
    @Override
    public TextComponent getComponent(final int index) {
        return null;
    }

    @Override
    public int numberOfComponents() {
        return 0;
    }

    @Override
    public String compose() {
        return String.valueOf(character);
    }
    
    
}