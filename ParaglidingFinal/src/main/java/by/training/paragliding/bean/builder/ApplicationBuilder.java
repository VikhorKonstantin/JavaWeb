package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.bean.exception.BeanException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ApplicationBuilder implements Builder<Application> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /**
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new T object.
     * @throws BeanException if some exceptions where
     * thrown while object building.
     */
    @Override
    public Application buildFromResultSet(final ResultSet newResultSet)
            throws BeanException {
        try {
            final var application = new Application();
            var competitionId = newResultSet.getInt("competitionId");
            var civlId = newResultSet.getInt("civl_id");
            application.setCompetitionId(competitionId);
            application.setSportsmanId(civlId);
            return application;
        } catch (SQLException newE) {
            throw new BeanException(EXC_MSG, newE);
        }

    }
}
