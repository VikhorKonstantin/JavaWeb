package by.training.task3composite.bean.entity;

public enum PartType {
    TEXT("    ","\n    ", ""),
    PARAGRAPH("", " ", ""),
    SENTENCE("", " ", ""),
    LEXEME("", " ", ""),
    WORD("", " ", ""),
    LETTER("", "", ""),
    PUNCTUATION_MARK("", "", "");

    private String prefix;
    private String suffix;
    private String delimiter;

    PartType(final String newPrefix, final String newDelimiter,
             final String newSuffix) {
        prefix = newPrefix;
        delimiter = newDelimiter;
        suffix= newSuffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
