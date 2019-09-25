package by.training.paragliding.servlet;


import by.training.paragliding.controller.Controller;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.dao.DaoFactory;
import by.training.paragliding.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "request", urlPatterns = "*.html")
public class ControllerServlet extends HttpServlet {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");
    private Controller controller;
    private static String DB_URL =
            "jdbc:mysql://localhost:3306/paragliding_db?serverTimezone=UTC"
                    + "&useSSL=false&allowPublicKeyRetrieval=true";
    @Override
    public void init() throws ServletException {
        /*
        todo:Replace with a connection pull
         */
        final String jdbcDriver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(jdbcDriver);
            final Connection connection = DriverManager
                    .getConnection(DB_URL, "paragliding_app",
                            "password");
            final DaoFactory daoFactory = new DaoFactory(connection);
            final ServiceFactory serviceFactory
                    = new ServiceFactory(daoFactory);
            controller = new Controller(serviceFactory);
        } catch (ClassNotFoundException | SQLException newE) {
            newE.printStackTrace();
        }
    }

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
        try {
            controller.executeTask(req, resp);
            RequestDispatcher dispatcher = getServletContext()
                    .getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException | ControllerException e) {
            logger.debug(e);
        }
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
            throws ServletException, IOException {    }


}
