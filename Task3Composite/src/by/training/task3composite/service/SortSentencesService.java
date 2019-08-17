package by.training.task3composite.service;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;
import by.training.task3composite.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Comparator;

public class SortSentencesService {

    public TextComposite parseAndSortSentences(final String fileName)
            throws ServiceException {
        ParseTextFromFileService parseTextService =
                new ParseTextFromFileService();
        TextComposite text = parseTextService.parseTextFromFile(fileName);
        sortSentencesInText(text);
        return text;
        
    }
    private void sortSentencesInText(TextComponent textComponent) {
        if (textComponent.getComponentType() == ComponentType.PARAGRAPH) {
            var numberOfComponents = textComponent.numberOfComponents();
            var sentences = new ArrayList<TextComponent>();
            for (int i = 0; i < numberOfComponents; ++i) {
                sentences.add(textComponent.getComponent(i));
            }
            sentences.forEach(textComponent::remove);
            sentences.sort(Comparator.comparing(
                    TextComponent::numberOfComponents));
            sentences.forEach(textComponent::add);
        } else {
            var numberOfComponents = textComponent.numberOfComponents();
            for (int i = 0; i < numberOfComponents; ++i) {
                sortSentencesInText(textComponent.getComponent(i));
            }
        }
    }
}