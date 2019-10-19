package by.training.paragliding.controller.command;

import by.training.paragliding.controller.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLocale implements Executable {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    private static final String REFERER_HEADER = "Referer";
    private static final String LOCALE_ATTR = "locale";

    /**
     * Execute command.
     *
     * @param req  http request
     * @param resp http response
     * @throws ControllerException if something goes wrong
     *                             while command execution or request invalid
     */
    @Override
    public ExecutionResult execute(final HttpServletRequest req,
                                   final HttpServletResponse resp)
            throws ControllerException {
        final String localeString = req.getParameter(LOCALE_ATTR);
        var cookie = new Cookie(LOCALE_ATTR, localeString);
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        var session = req.getSession();
        session.setAttribute(LOCALE_ATTR, localeString);
        String referer = req.getHeader(REFERER_HEADER);
        var contextPath = req.getContextPath();
        referer = referer.substring(
                referer.lastIndexOf(contextPath) + contextPath.length());

        return new ExecutionResult(false,
                referer);
    }

}
