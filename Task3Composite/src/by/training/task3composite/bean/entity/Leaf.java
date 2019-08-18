package by.training.task3composite.bean.entity;

public class Leaf implements TextComponent {
    /**
     * ComponentType of leaf.
     */
    private ComponentType componentType;
    /**
     * Char symbol of parsed text.
     */
    private char character;

    /**
     * Creates new Leaf.
     * @param newComponentType leaf type
     * @param newCharacter new character
     */
    public Leaf(final ComponentType newComponentType, final char newCharacter) {
        componentType = newComponentType;
        character = newCharacter;
    }

    /**
     * Returns type of Leaf.
     * @return componentType
     */
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

    /**
     * Returns 0 because Leaf has no children.
     * @return 0
     */
    @Override
    public int numberOfComponents() {
        return 0;
    }

    /**
     * Returns string representation of character.
     * @return string representation of character
     */
    @Override
    public String compose() {
        return String.valueOf(character);
    }
}
