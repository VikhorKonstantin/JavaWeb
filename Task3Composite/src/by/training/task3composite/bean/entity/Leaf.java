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

    @Override
    public String compose() {
        return String.valueOf(character);
    }
}
