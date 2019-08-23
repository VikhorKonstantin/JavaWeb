package by.training.task3composite.service.parser;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides parsing of a paragraph.
 */
public class ParagraphParser extends AbstractParser {
    /**
     * Regular expression to parse paragraph into sentences.
     */
    private static final String SENTENCE_REGEX =
            "(?:[^!?.])+(?:\\?!|!|\\?|\\.{1,3}+)";

    /**
     * Parses paragraph into sentences.
     *
     * @param newComponent  component which should hold data of parsed string
     * @param stringToParse string that should be parsed
     */
    @Override
    public void parse(final TextComponent newComponent,
                      final String stringToParse) {
        final Pattern pattern = Pattern.compile(SENTENCE_REGEX,
                Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(stringToParse);
        while (matcher.find()) {
            TextComposite sentence = new TextComposite(ComponentType.SENTENCE);
            String stringSentence = matcher.group();
            var successor = Optional.ofNullable(getSuccessor());
            successor.ifPresent(s -> {
                s.parse(sentence, stringSentence.trim());
                newComponent.add(sentence); });
        }
    }
}
