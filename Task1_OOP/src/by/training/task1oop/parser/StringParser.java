package by.training.task1oop.parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class StringParser {
    /**
     * Regular expression for separator.
     */
    private static final String SEP_REGEX = "\\s+";

    /**
     * Unable creating of object.
     */
    private StringParser() { }

    /**
     * @param fileLine line that should be parsed.
     * @return list of strings obtained by splitting a fileLine
     */
    public static List<String> parseString(final String fileLine) {
        Optional<String> stringOptional = Optional.ofNullable(fileLine);
        return stringOptional.map(s -> List.of(
                s.trim().split(SEP_REGEX))).orElseGet(ArrayList::new);

    }
}
