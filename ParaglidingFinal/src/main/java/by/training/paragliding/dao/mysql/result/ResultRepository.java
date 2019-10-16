package by.training.paragliding.dao.mysql.result;

import by.training.paragliding.bean.builder.ResultBuilder;
import by.training.paragliding.bean.entity.Result;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;

import java.sql.Connection;
import java.util.List;

public class ResultRepository extends BaseSqlRepository<Result> {
    private static final String INSERT_RESULT =
            "INSERT INTO `results`"
                    + " (sportsman_id, competition_id, score) "
                    + "VALUES (?, ?, ?);";
    private static final String DELETE_RESULT =
            "DELETE FROM `results`"
                    + " WHERE sportsman_id = ? AND competition_id = ?";
    private static final String UPDATE_RESULT =
            "UPDATE `results` SET `score` = ? "
                    + "WHERE sportsman_id = ? AND competition_id = ?";
    private static final String TABLE_NAME = "results";

    public ResultRepository(final Connection newConnection) {
        super(newConnection, new ResultBuilder());
    }

    /**
     * Returns object by it's id.
     *
     * @param id unique object identifier
     * @return object by it's id
     * @throws DaoException if something goes wrong
     */
    @Override
    public Result readById(final int id) throws DaoException {
        throw new DaoException("Unsupported operation. Result has no id");
    }

    /**
     * Adds object to repository.
     *
     * @param newResult object to add
     * @return is object was added
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean add(final Result newResult) throws DaoException {
        return executeUpdate(INSERT_RESULT, newResult.getSportsmanId(),
                newResult.getCompetitionId(), newResult.getScore());
    }

    /**
     * Deletes object from repository.
     *
     * @param newResult object to delete
     * @return true if object was deleted
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean delete(final Result newResult) throws DaoException {
        return executeUpdate(DELETE_RESULT, newResult.getSportsmanId(),
                newResult.getCompetitionId());
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
     * @param newResult object to update
     * @return true if object was updated, false if not.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean update(final Result newResult) throws DaoException {
        return executeUpdate(UPDATE_RESULT, newResult.getScore(),
                newResult.getSportsmanId(), newResult.getCompetitionId());
    }

    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     * @throws DaoException if something goes wrong
     */
    @Override
    public List<Result> query(final Specification specification) throws DaoException {
        return executeQuery(specification);
    }
}
