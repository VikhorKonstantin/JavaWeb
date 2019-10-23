package by.training.paragliding.dao.mysql.user;

import by.training.paragliding.bean.builder.UserBuilder;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;

public class UserRepository extends BaseSqlRepository<User> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("UserRepository");

    private static final String TABLE_NAME = "users";
    private static final String UPDATE_USER = "UPDATE `users` SET `email` = ?,"
            + " `password` = ?, `role` = ? WHERE `id` = ?";

    private static final String DELETE_USER =
            "DELETE FROM `users` WHERE `id` = ?";

    private static final String INSERT_USER =
            "INSERT INTO `users` (`email`, `password`, `role`)"
                    + " VALUES (?, ?, ?)";

    private static final String SELECT_BY_ID =
            "SELECT `id` as userId,\n"
                    + "       `email`,\n"
                    + "       `password`,\n"
                    + "       `role`,\n"
                    + "       `civl_id`,\n"
                    + "       `name` AS sportsmanName,\n"
                    + "       `surname`,\n"
                    + "       `gender`,\n"
                    + "       `country`,\n"
                    + "       `rating`\n"
                    + "FROM `users`\n"
                    + "         LEFT JOIN sportsmen s on users.id = s.user_id "
                    + "WHERE `id` = ?";

    public UserRepository(final Connection newConnection) {
        super(newConnection, new UserBuilder());
    }

    /**
     * Returns object by it's id.
     *
     * @param id unique object ID
     * @return object by it's id
     */
    @Override
    public User readById(final int id) throws DaoException {
        return readByProperties(SELECT_BY_ID, id);
    }

    /**
     * Adds object to repository.
     *
     * @param newUser user to add
     * @return is object was added
     */
    @Override
    public boolean add(final User newUser) throws DaoException {
        return executeUpdate(INSERT_USER, newUser.getEmail(),
                newUser.getPassword(),
                newUser.getRole().ordinal());
    }

    /**
     * Deletes object from repository.
     *
     * @param user object to delete
     * @return true if object was deleted
     */
    @Override
    public boolean delete(final User user) throws DaoException {
        return executeUpdate(DELETE_USER, user.getId());
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
     * Returns size of repository.
     *
     * @return size of repository
     * @throws DaoException if something goes wrong
     */
    @Override
    public int size() throws DaoException {
        return size(TABLE_NAME);
    }

    /**
     * Execute query.
     *
     * @param specification specification of a query.
     * @return List of objects satisfied specification.
     */
    @Override
    public List<User> query(final Specification specification)
            throws DaoException {
        return executeQuery(specification);
    }

    /**
     * Updates all fields of the object with the current values.
     *
     * @param newUser object to update
     * @return true if object was updated, false if not.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean update(final User newUser) throws DaoException {
        return executeUpdate(UPDATE_USER, newUser.getEmail(),
                newUser.getPassword(), newUser.getRole().ordinal(),
                newUser.getId());
    }
}
