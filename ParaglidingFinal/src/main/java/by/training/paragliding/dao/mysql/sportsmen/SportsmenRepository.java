package by.training.paragliding.dao.mysql.sportsmen;

import by.training.paragliding.bean.builder.SportsmanBuilder;
import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class SportsmenRepository extends BaseSqlRepository<Sportsman> {

    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");

    private static final String TABLE_NAME = "sportsmen";

    private static final String SELECT_SPORTSMAN_BY_ID =
            "SELECT `civl_id`, `user_id`, `name`, `surname`, `gender`,"
            + " `country`, `rating`, `image_path` "
            + "FROM `sportsmen` WHERE `civl_id` = ?";



    private static final String DELETE_BY_ID =
            "DELETE FROM `sportsmen` WHERE `civl_id` = ?";

    private static final String INSERT_SPORTSMEN =
            "INSERT INTO `sportsmen` (`civl_id`, `name`, `surname`,"
            + " `gender`, `country`, `rating`, `image_path`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SPORTSMAN = "UPDATE `sportsmen` "
            + "SET `name`=?, `surname`=?,"
            + " `gender`=?, `country`=?, `rating`=?, `image_path`=?"
            + " WHERE `civl_id` = ?";



    public SportsmenRepository(final Connection newConnection) {
        super(newConnection, new SportsmanBuilder());
    }


    /**
     * Returns object by it's id.
     *
     * @param id unique object ID
     * @return object by it's id
     */
    @Override
    public Sportsman readById(final int id) throws DaoException {
        return readByProperties(SELECT_SPORTSMAN_BY_ID, id);
    }

    /**
     * Adds object to repository.
     *
     * @param newSportsman plane to add
     * @return is object was added
     */
    @Override
    public boolean add(final Sportsman newSportsman) throws DaoException {
        return executeUpdate(INSERT_SPORTSMEN, newSportsman.getCivlId(),
                newSportsman.getName(), newSportsman.getSurname(),
                String.valueOf(newSportsman.getGender()),
                newSportsman.getCountryCode().getAlpha2(),
                newSportsman.getRating(),
                newSportsman.getImagePath());
    }

    /**
     * Deletes object from repository.
     *
     * @param sportsman object to delete
     * @return true if object was deleted
     */
    @Override
    public boolean delete(final Sportsman sportsman) throws DaoException {
        return executeUpdate(DELETE_BY_ID, sportsman.getCivlId());
    }

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     */
    @Override
    public boolean isEmpty() throws DaoException {
        return isEmpty(TABLE_NAME);
    }

    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    @Override
    public List<Sportsman> query(final Specification specification)
            throws DaoException {
        return executeQuery(specification);

    }

    /**
     * Updates all fields of the newSportsman with the current values.
     *
     * @param newSportsman Sportsman to update
     * @return true if Sportsman was updated, false if not.
     */
    @Override
    public boolean update(final Sportsman newSportsman) throws DaoException {
        return executeUpdate(UPDATE_SPORTSMAN, newSportsman.getName(),
                newSportsman.getSurname(),
                String.valueOf(newSportsman.getGender()),
                String.valueOf(newSportsman.getCountryCode().getAlpha2()),
                newSportsman.getRating(),
                newSportsman.getImagePath(),
                newSportsman.getCivlId());

    }
}
