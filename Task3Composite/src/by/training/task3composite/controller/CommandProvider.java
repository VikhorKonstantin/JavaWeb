package by.training.task3composite.controller;

import by.training.task3composite.controller.command.ChooseLocaleAndViewCommands;
import by.training.task3composite.controller.command.Executable;
import by.training.task3composite.controller.command.ParseCommand;
import by.training.task3composite.controller.command.SortLexemesCommand;
import by.training.task3composite.controller.command.SortParagraphsCommand;
import by.training.task3composite.controller.command.SortSentencesCommand;
import by.training.task3composite.controller.command.SortWordsCommand;

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
        executableMap.put("SORT_LEXEMES", new SortLexemesCommand());
        executableMap.put("CHANGE_LOCALE", new ChooseLocaleAndViewCommands());
    }

    /**
     * @param name name of command.
     * @return Command.
     */
    Executable getCommand(final String name) {
        return executableMap.get(name.toUpperCase());
    }
}
