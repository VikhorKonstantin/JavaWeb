package by.training.task3composite.service;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;
import by.training.task3composite.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
/**
 * Service which reads and parses text
 * from file and sorts words in sentences.
 */
public class SortWordsService {

    /**
     * Parses text and sorts words in it.
     *
     * @param fileName file name of text
     * @return Parsed and sorted text
     * @throws ServiceException if something goes wrong
     */
    public TextComposite parseAndSortWords(final String fileName)
            throws ServiceException {
        ParseTextFromFileService parseTextService =
                new ParseTextFromFileService();
        TextComposite text = parseTextService.parseTextFromFile(fileName);
        sortWordsInText(text);
        return text;
    }

    /**
     * Sort words in text.
     *
     * @param textComponent text to sort words in
     */
    private void sortWordsInText(final TextComponent textComponent) {
        if (textComponent.getComponentType() == ComponentType.SENTENCE) {
            sortWordsInSentence(textComponent);
        } else {
            var numberOfComponents = textComponent.numberOfComponents();
            for (int i = 0; i < numberOfComponents; ++i) {
                sortWordsInText(textComponent.getComponent(i));
            }
        }
    }

    /**
     * Sorts words in sentence.
     *
     * @param sentence sentence to sort words in
     */
    private void sortWordsInSentence(final TextComponent sentence) {
        var lexemes = readAllChildren(sentence);
        lexemes.forEach(sentence::remove);
        lexemes.sort((l1, l2) -> {
                    var parts1 = readAllChildren(l1);
                    var parts2 = readAllChildren(l2);
                    int length1 = parts1.stream()
                            .filter(c -> c.getComponentType()
                                    == ComponentType.WORD)
                            .mapToInt(TextComponent::numberOfComponents).sum();
                    int length2 = parts2.stream()
                            .filter(c -> c.getComponentType()
                                    == ComponentType.WORD)
                            .mapToInt(TextComponent::numberOfComponents).sum();
                    return length1 - length2;
                }
        );
        lexemes.forEach(sentence::add);
    }

    /**
     * Returns all children-components of the component.
     *
     * @param newTextComponent component to read children of
     * @return List of children-components
     */
    private List<TextComponent> readAllChildren(
            final TextComponent newTextComponent) {
        var numberOfComponents = newTextComponent.numberOfComponents();
        var children = new ArrayList<TextComponent>();
        for (int i = 0; i < numberOfComponents; ++i) {
            children.add(newTextComponent.getComponent(i));
        }
        return children;
    }
}
