package by.training.task1oop.parser;

import java.util.Arrays;
import java.util.List;

public final class StringParser {
    /**
     * Regular expression for separator.
     */
    public static final String SEP_REGEX = "\\s+";

    /**
     * Unable creating of object.
     */
    private StringParser() { }

    /**
     * @param fileLine line that should be parsed.
     * @return list of strings obtained by splitting a fileLine
     */
    public static List<String> parseString(final String fileLine) {
        List<String> stringList = Arrays.asList(fileLine.
                trim().split(SEP_REGEX));
        return stringList;
    }
}
