package by.training.task3composite.bean.entity;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Sentence extends TextComposite {
    /**
     * Add new component.
     * @param newComponent new component
     * @return true(false) if component was added(not)
     */
    @Override
    public boolean add(final TextComponent newComponent) {
        if (newComponent instanceof Lexeme) {
            return super.add(newComponent);
        } else {
            return false;
        }
    }
    /**
     * Remove component.
     * @param newComponent component to remove
     * @return true(false) if component was removed(not)
     */
    @Override
    public boolean remove(final TextComponent newComponent) {
        if (newComponent instanceof Lexeme) {
            return super.remove(newComponent);
        } else {
            return false;
        }
    }
    /**
     * Returns String representation of sentence.
     * @return String representation of sentence
     */
    @Override
    public String toString() {
        var components = new ArrayList<TextComponent>();
        var numberOfComponents = numberOfComponents();
        for (int i = 0; i < numberOfComponents; ++i) {
            components.add(getComponent(i));
        }
        StringJoiner joiner = new StringJoiner(" ");
        components.forEach(c -> joiner.add(c.toString()));
        return joiner.toString();
    }
}
