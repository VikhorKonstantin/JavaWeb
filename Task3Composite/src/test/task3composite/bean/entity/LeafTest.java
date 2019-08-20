package test.task3composite.bean.entity;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.Leaf;
import by.training.task3composite.bean.entity.TextComponent;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class LeafTest {
    /**
     * Test of constructor.
     */
    @Test(description = "Constructor test")
    public void constructorLeafTest() {
        ComponentType type = ComponentType.LETTER;
        char character = 'c';
        String valueOfChar = "c";
        TextComponent dummyTextComponent = new TextComponent() {
            @Override
            public boolean add(final TextComponent newComponent) {
                return false;
            }

            @Override
            public boolean remove(final TextComponent newComponent) {
                return false;
            }

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
                return null;
            }

            @Override
            public ComponentType getComponentType() {
                return null;
            }
        };
        Leaf leaf = new Leaf(type, character);
        var isCorrect = !leaf.add(dummyTextComponent)
                && !leaf.remove(dummyTextComponent)
                && leaf.compose().equals(valueOfChar)
                && leaf.getComponent(0) == null
                && leaf.getComponentType() == type
                && leaf.numberOfComponents() == 0;
        assertTrue(isCorrect);
    }
}
