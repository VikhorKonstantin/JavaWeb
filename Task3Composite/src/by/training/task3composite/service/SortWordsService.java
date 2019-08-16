package by.training.task3composite.service;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;
import by.training.task3composite.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortWordsService {

    public TextComposite parseAndSortWords(final String fileName)
            throws ServiceException {
        ParseTextFromFileService parseTextService =
                new ParseTextFromFileService();
        TextComposite text = parseTextService.parseTextFromFile(fileName);
        sortWordsInText(text);
        return text;
    }
    
    private void sortWordsInText(TextComponent textComponent) {
        
        if (textComponent.getComponentType() == ComponentType.SENTENCE) {
            sortWordsInSentence(textComponent);
        } else {
            var numberOfComponents = textComponent.numberOfComponents();
            for (int i = 0; i < numberOfComponents; ++i) {
                sortWordsInText(textComponent.getComponent(i));
            }
        }
    }
    private List<TextComponent> readAllChildren(
            TextComponent newTextComponent) {
        var numberOfComponents = newTextComponent.numberOfComponents();
        var children = new ArrayList<TextComponent>();
        for (int i = 0; i < numberOfComponents; ++i) {
            var c = newTextComponent.getComponent(i);
            children.add(c);
        }
        return children;
    }
    
    private void sortWordsInSentence(TextComponent sentence) {
        var lexemes = readAllChildren(sentence);
        lexemes.forEach(sentence::remove);
        lexemes.sort((l1, l2) -> {
                    var parts1 = readAllChildren(l1);
                    var parts2 = readAllChildren(l2);
                    int length1 = parts1.stream()
                            .filter(c -> c.getComponentType() ==
                                    ComponentType.WORD)
                            .mapToInt(TextComponent::numberOfComponents).sum();
                    int length2 = parts2.stream()
                            .filter(c -> c.getComponentType() ==
                                    ComponentType.WORD)
                            .mapToInt(TextComponent::numberOfComponents).sum();
                    return length1 - length2;
                }
        );
        lexemes.forEach(sentence::add);
    }

    
}
