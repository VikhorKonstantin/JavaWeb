package by.training.task3composite.service;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;
import by.training.task3composite.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;

public class SortParagraphsService {
    /**
     * Parse and sort text.
     *
     * @param fileName file name of text
     * @return sorted text
     * @throws ServiceException if something goes wrong
     */
    public TextComposite parseAndSortParagraph(final String fileName)
            throws ServiceException {
        ParseTextFromFileService parseTextService =
                new ParseTextFromFileService();
        TextComposite text = parseTextService.parseTextFromFile(fileName);
        TextComposite sortedText = new TextComposite(ComponentType.TEXT);
        var paragraphs = new ArrayList<TextComponent>();
        var numberOfParagraphs = text.numberOfComponents();
        for (int i = 0; i < numberOfParagraphs; ++i) {
            paragraphs.add(text.getComponent(i));
        }
        paragraphs.stream()
                .sorted(Comparator.comparing(TextComponent::numberOfComponents))
                .forEach(sortedText::add);
        sortedText.compose();
        return sortedText;
    }
}
