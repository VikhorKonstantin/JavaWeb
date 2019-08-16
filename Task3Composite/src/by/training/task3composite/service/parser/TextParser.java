package by.training.task3composite.service.parser;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser extends AbstractParser {
    /**
     * Regular expression to parse text into paragraph.
     */
    private static final String PARAGRAPH_REGEX =
            "(?sm)^( *[^\\s]+.*?(?:\\?!|!|\\?|\\.{1,3}+) *)$";

    /**
     * Parses text into paragraph.
     * @param newComponent component which should hold data of parsed string
     * @param stringToParse string that should be parsed
     */
    @Override
    public void parse(final TextComponent newComponent,
                      final String stringToParse) {
        final Pattern pattern = Pattern.compile(PARAGRAPH_REGEX,
                Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(stringToParse);
        while (matcher.find()) {
            TextComposite paragraph =
                    new TextComposite(ComponentType.PARAGRAPH);
            String stringParagraph = matcher.group();
            if (successor != null) {
                this.successor.parse(paragraph, stringParagraph.trim());
            }
            newComponent.add(paragraph);
        }
    }
}