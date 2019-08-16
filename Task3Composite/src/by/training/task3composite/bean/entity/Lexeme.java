package by.training.task3composite.bean.entity;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Lexeme extends TextComposite {
    /**
     * Add new lexeme.
     * @param newComponent new lexeme
     * @return true(false) if lexeme was added(not)
     */
    @Override
    public boolean add(final TextComponent newComponent) {
        if (newComponent instanceof Word
                || newComponent instanceof PunctuationMark) {
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
        if (newComponent instanceof Word
                || newComponent instanceof PunctuationMark) {
            return super.remove(newComponent);
        } else {
            return false;
        }
    }
    /**
     * Returns String representation of lexeme.
     * @return String representation of lexeme
     */
    @Override
    public String toString() {
        var components = new ArrayList<TextComponent>();
        var numberOfComponents = numberOfComponents();
        for (int i = 0; i < numberOfComponents; ++i) {
            components.add(getComponent(i));
        }
        StringJoiner joiner = new StringJoiner("");
        components.forEach(c -> joiner.add(c.toString()));
        return joiner.toString();
    }
}
