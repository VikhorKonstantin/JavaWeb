package by.training.task3composite.controller;

import by.training.task3composite.controller.command.*;

import java.util.HashMap;
import java.util.Map;

final class CommandProvider {
    /**
     * commands map.
     */
    private final Map<String, Executable> executableMap = new HashMap<>();

    /**
     * init executableMap.
     */
    CommandProvider() {
        executableMap.put("PARSE", new ParseCommand());
        executableMap.put("SORT_PARAGRAPH", new SortParagraphsCommand());
        executableMap.put("SORT_WORDS", new SortWordsCommand());
        executableMap.put("SORT_SENTENCES", new SortSentencesCommand());
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name.toUpperCase());
    }
}
