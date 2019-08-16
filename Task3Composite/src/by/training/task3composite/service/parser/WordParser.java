package by.training.task3composite.service.parser;

import by.training.task3composite.bean.entity.Letter;
import by.training.task3composite.bean.entity.TextComponent;

public class WordParser extends AbstractParser {
    /**
     * Regex to split word into letters.
     */
    private static final String DELIM_REGEX = "";

    /**
     * Parses word into letters.
     * @param newComponent component which should hold data of parsed string
     * @param stringToParse string that should be parsed
     */
    @Override
    public void parse(final TextComponent newComponent,
                      final String stringToParse) {
        for (var stringLetter : stringToParse.split(DELIM_REGEX)) {
            Letter letter = new Letter(stringLetter.charAt(0));
            newComponent.add(letter);
        }
    }
}
