package by.training.task3composite.service.parser;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;

public class SentenceParser extends AbstractParser {
    /**
     * Regex to split sentence into lexemes.
     */
    private static final String LEXEME_REGEX = " +";

    /**
     * Parses sentence into lexemes.
     * @param newComponent component which should hold data of parsed string
     * @param stringToParse string that should be parsed
     */
    @Override
    public void parse(final TextComponent newComponent,
                      final String stringToParse) {
        for (var stringLexeme : stringToParse.split(LEXEME_REGEX)) {
            TextComposite lexeme = new TextComposite(ComponentType.LEXEME);
            if (getSuccessor() != null) {
                getSuccessor().parse(lexeme,
                        stringLexeme.replaceAll(" ", ""));
            }
            newComponent.add(lexeme);
        }
    }
}
