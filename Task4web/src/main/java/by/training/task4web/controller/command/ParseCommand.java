package by.training.task4web.controller.command;


import by.training.task4web.controller.exception.ControllerException;
import by.training.task4web.service.BaseGemsBuilder;
import by.training.task4web.service.GemsDomBuilder;
import by.training.task4web.service.GemsSaxBuilder;
import by.training.task4web.service.GemsStaxBuilder;
import by.training.task4web.service.ParseDirector;
import by.training.task4web.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ParseCommand implements Executable {
    /**
     * Logger.
     */
    private static Logger logger = LogManager.getLogger("error");
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
    /**
     * Connects ParserName with Builder.
     */
    private final Map<String, BaseGemsBuilder> builderMap = new HashMap<>();

    /**
     * Init builderMap.
     */
    public ParseCommand() {
        builderMap.put(SAX, new GemsSaxBuilder());
        builderMap.put(DOM, new GemsDomBuilder());
        builderMap.put(STAX, new GemsStaxBuilder());
    }
    /**
     * Execute command.
     * @param args for service method
     * @param req http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     * while command execution or request invalid
     */
    @Override
    public void execute(final String args, final HttpServletRequest req,
                        final HttpServletResponse resp)
            throws ControllerException {
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
            var gems = ParseDirector.createGems(builder,
                    xmlFileName, xsdFileName);
            req.setAttribute("gems", gems);
        } catch (ServiceException | ArrayIndexOutOfBoundsException e) {
            throw new ControllerException(e);
        }
    }
}
