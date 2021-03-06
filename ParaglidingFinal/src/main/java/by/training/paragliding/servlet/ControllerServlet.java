package by.training.paragliding.servlet;


import by.training.paragliding.controller.Controller;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.TransactionFactoryImpl;
import by.training.paragliding.dao.mysql.connection.ConnectionFactory;
import by.training.paragliding.dao.mysql.connection.ConnectionFactoryImpl;
import by.training.paragliding.dao.mysql.connection.ConnectionPoolImpl;
import by.training.paragliding.dao.mysql.connection.ConnectionValidator;
import by.training.paragliding.dao.mysql.connection.ConnectionValidatorImpl;
import by.training.paragliding.service.transaction.TransactionBasedServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = "*.html")
public class ControllerServlet extends HttpServlet {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("ControllerServlet");
    /**
     * Supported ize of a connection pool.
     */
    private static final int POOL_SIZE = 100;

    @Override
    public void init() throws ServletException {
        try {
            ConnectionFactory connectionFactory =
                    new ConnectionFactoryImpl("database.properties");
            ConnectionValidator connectionValidator =
                    new ConnectionValidatorImpl();
            ConnectionPoolImpl.getInstance()
                    .initialize(POOL_SIZE, connectionFactory,
                            connectionValidator);
        } catch (DaoException newE) {
            logger.error(newE);
        }
    }

    /**
     * Called by the server (via the service method)
     * to allow a servlet to handle a GET request.
     *
     * @param req  Http Servlet Request
     * @param resp Http Servlet Response
     * @throws ServletException if an input or output error is
     *                          detected when the servlet handles the GET request
     * @throws IOException      if the request for the GET could not be handled
     */
    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Exception e) {
            logger.error(e);
            req.setAttribute("errorMessage", e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }

    /**
     * Called by the server (via the service method)
     * to allow a servlet to handle a POST request.
     *
     * @param req  Http Servlet Request
     * @param resp Http Servlet Response
     * @throws ServletException if an input or output error is
     *                          detected when the servlet handles the POST request
     * @throws IOException      if the request for the POST could not be handled
     */
    @Override
    protected void doPost(final HttpServletRequest req,
                          final HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Exception e) {
            logger.error(e);
            req.setAttribute("errorMessage", e.getMessage());
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }


    private void process(final HttpServletRequest req,
                         final HttpServletResponse resp)
            throws Exception {
        try(Controller controller = receiveController()) {
            var result = controller.executeTask(req, resp);
            if (result.isForward()) {
                RequestDispatcher dispatcher = getServletContext()
                        .getRequestDispatcher(result.getUrl());
                logger.debug("FORWARD TO: {}", result.getUrl());
                dispatcher.forward(req, resp);
            } else {
                logger.debug("REDIRECT TO: {}{}",
                        req.getContextPath(), result.getUrl());
                resp.sendRedirect(req.getContextPath() + result.getUrl());
            }
        }


    }

    private Controller receiveController() throws DaoException,
            ControllerException {
        return new Controller(new TransactionBasedServiceFactory(
                new TransactionFactoryImpl()));
    }

    @Override
    public void destroy() {
        ConnectionPoolImpl.getInstance().shutdown();
    }
}
