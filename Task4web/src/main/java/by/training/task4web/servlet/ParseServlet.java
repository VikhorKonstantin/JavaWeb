package by.training.task4web.servlet;


import by.training.task4web.controller.Controller;
import by.training.task4web.controller.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "parse", urlPatterns = "/parse")
public class ParseServlet extends HttpServlet {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("error");
    /**
     * Message separator.
     */
    private static final String SEP = " ";

    private static final String XML_FILE = "input\\gems.xml";
    private static final String XSD_FILE = "input\\gems.xsd";

    /**
     * Called by the server (via the service method)
     * to allow a servlet to handle a GET request.
     *
     * @param req  Http Servlet Request
     * @param resp Http Servlet Response
     * @throws ServletException if an input or output error is
     * detected when the servlet handles the GET request
     * @throws IOException      if the request for the GET could not be handled
     */
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
    }

    /**
     * Called by the server (via the service method)
     * to allow a servlet to handle a POST request.
     *
     * @param req  Http Servlet Request
     * @param resp Http Servlet Response
     * @throws ServletException if an input or output error is
     * detected when the servlet handles the POST request
     * @throws IOException      if the request for the POST could not be handled
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            var actionName = req.getParameter("actionName");
            var radioName = req.getParameter("radios");
            var xmlPath = req.getParameter("xmlFileName");
            var xmlFullPath = getServletContext()
                    .getResource(xmlPath).getPath();
            var xsdPath = req.getParameter("xsdFileName");
            var xsdFullPath = getServletContext()
                    .getResource(xsdPath).getPath();
            logger.error(xsdFullPath);
            var reqString = actionName + SEP
                    + radioName + SEP + xmlFullPath + SEP + xsdFullPath;
            Controller controller = new Controller();
            controller.executeTask(reqString, req, resp);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException | ControllerException e) {
            req.setAttribute("errorMessage", e.getMessage());
            req.setAttribute("errorCause", e.getCause());
            req.setAttribute("errorLocation", (String) this.getServletName());
            var resPath = getServletContext()
                    .getResource("/WEB-INF/jsp/error.jsp").getPath();
            logger.error("EXC: " + resPath, e);
            req.getRequestDispatcher(resPath)
                    .forward(req, resp);
        }

    }


}
