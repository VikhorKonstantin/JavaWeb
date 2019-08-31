package by.training.task4xml.service;

import by.training.task4xml.service.exception.ServiceException;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class LocaleService {
    /**
     * Exception message.
     */
    private static final String EXC_MSG = "Null Locale in args. ";
    /**
     * Manage resources.
     */
    private ResourceManager resourceManager = ResourceManager.INSTANCE;

    /**
     * Changes locale.
     *
     * @param newLocale new locale
     * @return response
     * @throws ServiceException if newLocale null
     */
    public String changeLocale(final Locale newLocale)
            throws ServiceException {
        if (newLocale != null) {
            resourceManager.changeResource(newLocale);
            final String resKey = "locale_changed";
            return resourceManager.getString(resKey);
        } else {
            throw new ServiceException(EXC_MSG);
        }

    }

    /**
     * Creates start info by locale.
     *
     * @return start info
     */
    public String createStartInfo() {
        final String choseLocaleKey = "choose_locale";
        return resourceManager.getString(choseLocaleKey);

    }

    /**
     * Creates commands info by locale.
     *
     * @return commands info
     */
    public String createCommandsInfo() {
        final String startInfoKey = "startInfo";
        final String startInfo = resourceManager.getString(startInfoKey);
        final String xmlFileNameKey = "nameOfXmlFile";
        final String xsdFileNameKey = "nameOfXsdFile";
        final String xmlFileName = resourceManager.getString(xmlFileNameKey);
        final String xsdFileName = resourceManager.getString(xsdFileNameKey);
        final String parserTypeKey = "parserType";
        final String parserType = resourceManager.getString(parserTypeKey);
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(startInfo).add("PARSE " + parserType + " "
                + xmlFileName + " " + xsdFileName)
                .add("CHANGE_LOCALE\t" + "RU\\ENG\\BY");
        return joiner.toString();
    }

    /**
     * Resource Manager.
     */
    private enum ResourceManager {
        /**
         * Instance.
         */
        INSTANCE;
        /**
         * Base name of resources.
         */
        private final String resourceName = "property.text";
        /**
         * Instance of ResourceBundle.
         */
        private ResourceBundle resourceBundle;

        /**
         * Creates new ResourceManager.
         */
        ResourceManager() {
            resourceBundle = ResourceBundle.getBundle(resourceName,
                    Locale.getDefault());
        }

        /**
         * Changes locale.
         *
         * @param locale new locale
         */
        public void changeResource(final Locale locale) {
            resourceBundle = ResourceBundle.getBundle(resourceName, locale);
        }

        /**
         * Returns String by key.
         *
         * @param key key of required String
         * @return String
         */
        public String getString(final String key) {
            return resourceBundle.getString(key);
        }
    }
}
