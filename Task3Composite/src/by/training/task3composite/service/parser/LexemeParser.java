package by.training.task3composite.service.parser;

import by.training.task3composite.bean.entity.ComponentType;
import by.training.task3composite.bean.entity.Leaf;
import by.training.task3composite.bean.entity.TextComponent;
import by.training.task3composite.bean.entity.TextComposite;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses lexemes.
 */
public class LexemeParser extends AbstractParser {
    /**
     * Name of capture group that captures punctuation marks before word.
     */
    private static final String PUNCT_BEFOR = "bp";
    /**
     * Name of capture group that captures word.
     * between possible punctuation marks
     */
    private static final String WORD_PART = "txt";
    /**
     * Name of capture group that captures punctuation marks after word.
     */
    private static final String PUNCT_AFTER = "ap";
    /**
     * Regular expression to parse lexeme into word and punctuation marks.
     */
    private static final String PUNCTUATION_REGEX =
            "(?<bp>\\p{Punct}*)(?<txt>\\p{Alnum}+)+(?<ap>\\p{Punct}*)";

    /**
     * Parses lexeme into word and punctuation marks.
     *
     * @param newComponent  component which should hold data of parsed string
     * @param stringToParse string that should be parsed
     */
    @Override
    public void parse(final TextComponent newComponent,
                      final String stringToParse) {
        final Pattern pattern = Pattern.compile(PUNCTUATION_REGEX,
                Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(stringToParse);
        while (matcher.find()) {
            var bp = matcher.group(PUNCT_BEFOR);
            if (!bp.isEmpty()) {
                for (int i = 0; i < bp.length(); ++i) {
                    Leaf punctuationMark = new Leaf(
                            ComponentType.PUNCTUATION_MARK, bp.charAt(i));
                    newComponent.add(punctuationMark);
                }
            }
            var txt = matcher.group(WORD_PART);
            TextComposite word = new TextComposite(ComponentType.WORD);
            var successor = Optional.ofNullable(getSuccessor());
            successor.ifPresent(s -> {
                s.parse(word, txt);
                newComponent.add(word); });
            var ap = matcher.group(PUNCT_AFTER);
            if (!ap.isEmpty()) {
                for (int i = 0; i < ap.length(); ++i) {
                    Leaf punctuationMark = new Leaf(
                            ComponentType.PUNCTUATION_MARK, ap.charAt(i));
                    newComponent.add(punctuationMark);
                }
            }
        }

    }
}
