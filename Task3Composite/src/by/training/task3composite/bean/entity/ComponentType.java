package by.training.task3composite.bean.entity;

public enum ComponentType {
    /**
     * ComponentType: text.
     */
    TEXT("    ", "\n    ", ""),
    /**
     * ComponentType: paragraph.
     */
    PARAGRAPH("", " ", ""),
    /**
     * ComponentType: sentence.
     */
    SENTENCE("", " ", ""),
    /**
     * ComponentType: lexeme.
     */
    LEXEME("", "", ""),
    /**
     * ComponentType: word.
     */
    WORD("", "", ""),
    /**
     * ComponentType: letter.
     */
    LETTER("", "", ""),
    /**
     * ComponentType: punctuation mark.
     */
    PUNCTUATION_MARK("", "", "");

    /**
     * Prefix(used to compose).
     */
    private String prefix;
    /**
     * Suffix(used to compose).
     */
    private String suffix;
    /**
     * Delimiter(used to compose).
     */
    private String delimiter;

    /**
     * Enum constructor.
     *
     * @param newPrefix    new prefix
     * @param newDelimiter new delimiter
     * @param newSuffix    new suffix
     */
    ComponentType(final String newPrefix, final String newDelimiter,
                  final String newSuffix) {
        prefix = newPrefix;
        delimiter = newDelimiter;
        suffix = newSuffix;
    }

    /**
     * Returns prefix.
     *
     * @return prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Returns suffix.
     *
     * @return suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Returns delimiter.
     *
     * @return delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }
}
