package by.training.paragliding.dao.mysql.application;

import by.training.paragliding.bean.builder.ApplicationBuilder;
import by.training.paragliding.bean.entity.Application;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.util.List;

public class ApplicationRepository extends BaseSqlRepository<Application> {

    private static final String INSERT_APPLICATION =
            "INSERT INTO `applications`"
            + " (sportsman_id, competition_id) "
            + "VALUES (?, ?);";
    private static final String DELETE_APPLICATION =
            "DELETE FROM `applications`"
                    + " WHERE sportsman_id=? AND competition_id=?";
    private static final String TABLE_NAME = "applications";


    public ApplicationRepository(final Connection newConnection) {
        super(newConnection, new ApplicationBuilder());
    }

    /**
     * Returns object by it's id.
     *
     * @param id unique object identifier
     * @return object by it's id
     * @throws DaoException if something goes wrong
     */
    @Override
    public Application readById(final int id) throws DaoException {
        throw new DaoException("Unsupported operation. Application has no id");
    }

    /**
     * Adds object to repository.
     *
     * @param newApplication object to add
     * @return is object was added
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean add(final Application newApplication) throws DaoException {
        return executeUpdate(INSERT_APPLICATION,
                newApplication.getSportsmanId(),
                newApplication.getCompetitionId());
    }

    /**
     * Deletes object from repository.
     *
     * @param newApplication object to delete
     * @return true if object was deleted
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean delete(final Application newApplication)
            throws DaoException {
        return executeUpdate(DELETE_APPLICATION,
                newApplication.getSportsmanId(),
                newApplication.getCompetitionId());
    }

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean isEmpty() throws DaoException {
        return isEmpty(TABLE_NAME);
    }

    /**
     * Updates all fields of the object with the current values.
     *
     * @param object object to update
     * @return true if object was updated, false if not.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean update(final Application object) throws DaoException {
        throw new DaoException("Unsupported operation. It's makes no sense");
    }

    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     * @throws DaoException if something goes wrong
     */
    @Override
    public List<Application> query(final Specification specification)
            throws DaoException {
        return executeQuery(specification);
    }
}
