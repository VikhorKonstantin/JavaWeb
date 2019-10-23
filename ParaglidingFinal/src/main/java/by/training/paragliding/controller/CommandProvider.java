package by.training.paragliding.controller;

import by.training.paragliding.controller.command.*;
import by.training.paragliding.controller.command.application.ApplyCompetition;
import by.training.paragliding.controller.command.application.DeleteApplication;
import by.training.paragliding.controller.command.competition.*;
import by.training.paragliding.controller.command.result.SaveResults;
import by.training.paragliding.controller.command.result.ViewCompetitionResults;
import by.training.paragliding.controller.command.result.ViewResultsForm;
import by.training.paragliding.controller.command.sportsman.ViewAllSportsmen;
import by.training.paragliding.controller.command.sportsman.ViewParticipants;
import by.training.paragliding.controller.command.sportsman.ViewSportsmanById;
import by.training.paragliding.controller.command.user.LogIn;
import by.training.paragliding.controller.command.user.LogOut;
import by.training.paragliding.controller.command.user.SingUp;
import by.training.paragliding.controller.command.user.ViewAccountPage;
import by.training.paragliding.controller.exception.ControllerException;
import by.training.paragliding.service.ServiceFactory;
import by.training.paragliding.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides access to commands by CommandName.
 */
final class CommandProvider implements AutoCloseable {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("CommandProvider");
    /**
     * Post commands map.
     */
    private static final Map<String, ThrowingFunction<ServiceFactory,
            Executable, ServiceException>> postMap =
            new HashMap<>();
    /**
     * Get commands map.
     */
    private static final Map<String,
            ThrowingFunction<ServiceFactory,
                    Executable, ServiceException>> getMap =
            new HashMap<>();

    static {
        getMap.put("/sportsmen/id",
                s -> new ViewSportsmanById(s.createSportsmanService()));
        getMap.put("/sportsmen/all",
                s -> new ViewAllSportsmen(s.createSportsmanService()));
        getMap.put("/index", s -> new StartCommand(s
                .createCompetitionService()));
        getMap.put("/singUp", s -> new ViewSingUpPage());
        getMap.put("/logIn", s -> new ViewLogInPage());
        postMap.put("/user/singUp", s ->
                new SingUp(s.createUserService()));
        postMap.put("/user/logIn", s ->
                new LogIn(s.createUserService()));
        getMap.put("/user/logOut", s -> new LogOut());
        getMap.put("/competition/all",
                s -> new ViewCompetitionList(
                        s.createCompetitionService()));
        getMap.put("/competition", s -> new ViewCompetitionById(
                s.createCompetitionService()
        ));
        getMap.put("/competition/edit", s -> new ViewCompetitionsEditPage(
                s.createCompetitionService()
        ));
        postMap.put("/competition/edit", s -> new EditCompetition(
                s.createCompetitionService()
        ));
        getMap.put("/sportsmen/participants", s -> new ViewParticipants(
                s.createCompetitionService(),
                s.createSportsmanService()));
        postMap.put("/application",
                s -> new ApplyCompetition(
                        s.createApplicationService(),
                        s.createCompetitionService()));
        getMap.put("/resultsPage", s -> new ViewResultsForm(
                s.createCompetitionService(),
                s.createSportsmanService()));
        postMap.put("/results", s -> new SaveResults(
                s.createResultService(),
                s.createCompetitionService(),
                s.createSportsmanService()
        ));
        getMap.put("/results", s -> new ViewCompetitionResults(
                s.createResultService(),
                s.createCompetitionService(),
                s.createSportsmanService()
        ));
        postMap.put("/localeChange", s -> new ChangeLocale());
        getMap.put("/user/account", s -> new ViewAccountPage(
                s.createCompetitionService(),
                s.createSportsmanService()));
        postMap.put("/competition/add", s -> new AddCompetition(
                s.createCompetitionService()
        ));
        postMap.put("/application/del", s -> new DeleteApplication(
                s.createApplicationService()
        ));
    }

    /**
     * Service factory.
     */
    private ServiceFactory serviceFactory;

    /**
     * Init executableMap with services created by serviceFactory.
     *
     * @param newServiceFactory serviceFactory used
     *                          to create new service instances
     */
    CommandProvider(final ServiceFactory newServiceFactory) {
        serviceFactory = newServiceFactory;
    }


    @Override
    public void close() throws Exception {
        serviceFactory.close();
    }

    /**
     * @param name name of command.
     * @return Command.
     * @throws ControllerException if ServiceException was thrown
     *                             while creating new service instances
     */
    Executable getPostCommand(final String name) throws ControllerException {
        logger.debug("Command name: {}", name);
        try {
            return postMap.get(name).apply(serviceFactory);
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }
    }

    /**
     * @param name name of command.
     * @return Command.
     * @throws ControllerException if ServiceException was thrown
     *                             while creating new service instances
     */
    Executable getGetCommand(final String name) throws ControllerException {
        logger.debug("Command name: {}", name);
        try {
            return getMap.get(name).apply(serviceFactory);
        } catch (ServiceException newE) {
            throw new ControllerException(newE);
        }
    }

    /**
     * @param <P> param type.
     * @param <R> return type.
     * @param <E> exception type.
     */
    @FunctionalInterface
    private interface ThrowingFunction<P, R, E extends Exception> {
        R apply(P t) throws E;
    }
}


