package by.training.task3composite.service;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;
import by.training.task3composite.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Service which reads and parses text from file and sorts lexemes in sentences.
 */
public class SortLexemesService {
    /**
     * Character to compare by.
     */
    private Character character;

    /**
     * Parse and sort text.
     *
     * @param fileName     file name of text
     * @param newCharacter character to sort by
     * @return Sorted text
     * @throws ServiceException if something goes wrong
     */
    public TextComposite parseAndSortLexemes(final String fileName,
                                             final char newCharacter)
            throws ServiceException {
        character = newCharacter;
        ParseTextFromFileService parseTextService =
                new ParseTextFromFileService();
        TextComposite text = parseTextService.parseTextFromFile(fileName);
        sortLexemesInText(text);
        return text;

    }

    private void sortLexemesInText(final TextComponent textComponent) {
        if (textComponent.getComponentType() == ComponentType.SENTENCE) {
            var lexemes = readAllChildren(textComponent);
            lexemes.forEach(textComponent::remove);
            lexemes.sort(
                    Comparator.comparing(
                            l -> countNumberOfCharacter((TextComponent) l))
                            .reversed()
                            .thenComparing(l -> ((TextComponent) l).compose()));
            lexemes.forEach(textComponent::add);
        } else {
            var numberOfComponents = textComponent.numberOfComponents();
            for (int i = 0; i < numberOfComponents; ++i) {
                sortLexemesInText(textComponent.getComponent(i));
            }
        }
    }

    private int countNumberOfCharacter(final TextComponent lexeme) {
        var words = readAllChildren(lexeme).stream()
                .filter(c -> c.getComponentType() == ComponentType.WORD)
                .collect(Collectors.toList());
        int numberOfChar = 0;
        for (var word : words) {
            var letters = readAllChildren(word);
            final String stringOfCharacter = String.valueOf(character);
            numberOfChar += letters.stream()
                    .filter(l -> l.compose().equals(stringOfCharacter))
                    .count();
        }
        return numberOfChar;
    }

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
