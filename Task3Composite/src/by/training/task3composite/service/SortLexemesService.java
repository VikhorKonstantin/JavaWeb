package by.training.task3composite.service;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.Leaf;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;
import by.training.task3composite.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortLexemesService {
    
    private Character character;

    public SortLexemesService(final Character newCharacter) {
        character = newCharacter;
    }

    public TextComposite parseAndSortLexemes(final String fileName)
            throws ServiceException {
        ParseTextFromFileService parseTextService =
                new ParseTextFromFileService();
        TextComposite text = parseTextService.parseTextFromFile(fileName);
        sortSentencesInText(text);
        return text;

    }
    private void sortSentencesInText(TextComponent textComponent) {
        if (textComponent.getComponentType() == ComponentType.SENTENCE) {
            var lexemes = readAllChildren(textComponent);
            lexemes.forEach(textComponent::remove);
            lexemes.sort(
                    Comparator.comparing(
                    l -> countNumberOfCharacter((TextComponent) l, character))
                            .reversed()
                            .thenComparing(l -> ((TextComponent)l).compose()));
            lexemes.forEach(textComponent::add);
        } else {
            var numberOfComponents = textComponent.numberOfComponents();
            for (int i = 0; i < numberOfComponents; ++i) {
                sortSentencesInText(textComponent.getComponent(i));
            }
        }
    }
    
    private int countNumberOfCharacter(final TextComponent lexeme,
                                       final Character character) {
        var words = readAllChildren(lexeme).stream()
                .filter(c -> c.getComponentType()==ComponentType.WORD)
                .collect(Collectors.toList());
        int numberOfChar = 0;
        for (var word : words) {
            var letters = readAllChildren(word);
            numberOfChar += letters.stream()
                    .filter(l -> l.compose().equals(String.valueOf(character)))
                    .count();
        }
        return numberOfChar;
    }

    private List<TextComponent> readAllChildren(
            TextComponent newTextComponent) {
        var numberOfComponents = newTextComponent.numberOfComponents();
        var children = new ArrayList<TextComponent>();
        for (int i = 0; i < numberOfComponents; ++i) {
            children.add(newTextComponent.getComponent(i));
        }
        return children;
    }
}
