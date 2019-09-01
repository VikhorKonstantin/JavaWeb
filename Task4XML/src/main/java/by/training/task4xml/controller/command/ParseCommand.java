package by.training.task4xml.controller.command;


import by.training.task4xml.bean.entity.Gem;
import by.training.task4xml.service.BaseGemsBuilder;
import by.training.task4xml.service.GemsDomBuilder;
import by.training.task4xml.service.GemsSaxBuilder;
import by.training.task4xml.service.ParseDirector;
import by.training.task4xml.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ParseCommand implements Executable {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger();
    /**
     * logger message.
     */
    private static final String LOG_MESSAGE = "Invalid args in line: ";
    /**
     * Command to parse with SAX parser.
     */
    private static final String SAX = "SAX";
    /**
     * Command to parse with DOM parser.
     */
    private static final String DOM = "DOM";
    /**
     * Command to parse with StAX parser.
     */
    private static final String STAX = "STAX";
    
    private final Map<String, BaseGemsBuilder> builderMap = new HashMap<>();

    public ParseCommand() {
        builderMap.put(SAX, new GemsSaxBuilder());
        builderMap.put(DOM, new GemsDomBuilder());
    }

    /**
     * @param args for service method
     * @return response
     */
    @Override
    public String execute(final String args) {
        BaseGemsBuilder builder;
        try {
            final String delimiter = "\\s+";
            var argsArray = args.split(delimiter);
            final int commandIndex = 0;
            final int xmlFileIndex = 1;
            final int xsdFileIndex = 2;
            var commandStr = argsArray[commandIndex];
            builder = builderMap.get(commandStr.toUpperCase());
            var xmlFileName = argsArray[xmlFileIndex];
            var xsdFileName = argsArray[xsdFileIndex];
            var gems = ParseDirector.createUser(builder, xmlFileName, xsdFileName);
            return gems.stream().map(Gem::toString)
                    .collect(Collectors.joining("\n"));
        } catch (ServiceException | ArrayIndexOutOfBoundsException e) {
            logger.info(LOG_MESSAGE, e);
            return LOG_MESSAGE + e.getMessage();
        }
    }
}
