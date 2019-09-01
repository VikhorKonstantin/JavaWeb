package by.training.task4xml.controller.command;

import by.training.task4xml.service.LocaleService;
import by.training.task4xml.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Locale;

public class ChooseLocaleAndViewCommands implements Executable {
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    /**
     * Provides locales by String name.
     */
    private static final HashMap<String, Locale> LOCALE_PROVIDER =
            new HashMap<>();
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();

    /**
     *Creates new ChooseLocaleAndViewCommands command.
     */
    public ChooseLocaleAndViewCommands() {
        LOCALE_PROVIDER.put("RU", new Locale("ru", "RU"));
        LOCALE_PROVIDER.put("ENG", new Locale("en", "US"));
        LOCALE_PROVIDER.put("BY", new Locale("be", "BY"));
    }


    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        LocaleService service = new LocaleService();
        try {
            Locale locale = LOCALE_PROVIDER.get(args.trim().toUpperCase());
            final var newLineChar = "\n";
            return service.changeLocale(locale) + newLineChar
                    + service.createCommandsInfo();
        } catch (ServiceException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
