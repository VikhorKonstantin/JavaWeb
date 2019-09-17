package by.training.sqltask.servlet;


import by.training.sqltask.controller.Controller;
import by.training.sqltask.controller.exception.ControllerException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "request", urlPatterns = "/request")
public class ReadServlet extends HttpServlet {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");

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
            Controller controller = new Controller();
            controller.executeTask(req, resp);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);

        } catch (ServletException | IOException | ControllerException e) {
            logger.debug(e);
        }

    }


}
