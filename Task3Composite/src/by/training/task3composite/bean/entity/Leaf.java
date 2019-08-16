package by.training.task3composite.bean.entity;

public abstract class Leaf implements TextComponent {
    /**
     * Char symbol of parsed text.
     */
    private char character;

    /**
     * Abstract constructor.
     * @param newCharacter new character.
     */
    public Leaf(final Character newCharacter) {
        character = newCharacter;
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
     * Returns String representation of Leaf-component.
     * @return String representation of Leaf-component
     */
    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
