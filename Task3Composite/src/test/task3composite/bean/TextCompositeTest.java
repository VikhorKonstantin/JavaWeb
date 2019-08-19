package test.task3composite.bean;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.Leaf;
import by.training.task3composite.bean.entity.TextComposite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TextCompositeTest {
    
    
    @Test(description = "TextComposite constructor test")
    public void constructorTextComposite() {
        ComponentType type = ComponentType.LETTER;
        char character = 'c';
        String valueOfChar = "c";
        Leaf leaf = new Leaf(type, character);
        ComponentType compositeType = ComponentType.WORD;
        TextComposite textComposite = new TextComposite(compositeType);
        
        var isCorrect = textComposite.add(leaf)
                && textComposite.getComponent(0).equals(leaf)
                && textComposite.compose().equals(valueOfChar)
                && textComposite.getComponentType() == compositeType
                && textComposite.numberOfComponents() == 1
                && textComposite.remove(leaf);
        assertTrue(isCorrect);
    }
    
}
