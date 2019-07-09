package by.training.task1oop.parser;


import java.util.Arrays;
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
        if(stringOptional.isPresent()){
            return Arrays.asList(stringOptional.orElseGet(String::new).
                    trim().split(SEP_REGEX));
        } else {
            return null;
        }

    }
}
