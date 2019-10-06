package by.training.paragliding.dao.mysql.user;

import by.training.paragliding.bean.builder.Builder;
import by.training.paragliding.bean.builder.UserBuilder;
import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;
import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.mysql.BaseSqlRepository;
import by.training.paragliding.dao.mysql.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository extends BaseSqlRepository<User> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    private static final String IS_EMPTY = "SELECT NULL FROM `users` LIMIT 1";

    private static final String DELETE_USER =
            "DELETE FROM `users` WHERE `id` = ?";

    private static final String INSERT_USER =
            "INSERT INTO `users` (`email`, `password`, `role`)"
                    + " VALUES (?, ?, ?)";

    private static final  String SELECT_BY_ID =
            "SELECT `id`, `email`, `password`, `role`"
                    + " FROM `users` WHERE `id` = ?";
    private final Builder<User> userBuilder = new UserBuilder();

    /**
     * Map which stores pairs Role instance + number
     * from database that represents this role
     */
    private static final Map<Integer, Role> ROLE_MAP = new HashMap<>();

    static {
        ROLE_MAP.put(0, Role.ADMIN);
        ROLE_MAP.put(1, Role.REGISTERED_USER);
        ROLE_MAP.put(2, Role.REGISTERED_SPORTSMEN);
    }

    public UserRepository(final Connection newConnection) {
        super(newConnection);
    }

    /**
     * Returns object by it's id.
     *
     * @param id unique object ID
     * @return object by it's id
     */
    @Override
    public User readById(final int id) throws DaoException {

        try (PreparedStatement statement =
                     connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            User user;
            try (ResultSet resultSet = statement.executeQuery()) {
                user = null;
                if (resultSet.next()) {
                    user = userBuilder.build(resultSet);
                }
            }
            return user;
        } catch (SQLException | BeanException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Adds object to repository.
     *
     * @param newUser user to add
     * @return is object was added
     */
    @Override
    public boolean add(final User newUser) throws DaoException {

        try (PreparedStatement statement =
                     connection.prepareStatement(INSERT_USER,
                             Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, newUser.getEmail());
            statement.setString(2, newUser.getPassword());
            statement.setInt(3, newUser.getRole().ordinal());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Deletes object from repository.
     *
     * @param user object to delete
     * @return true if object was deleted
     */
    @Override
    public boolean delete(final User user) throws DaoException {

        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_USER)) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    /**
     * Returns is repository empty.
     *
     * @return is repository empty.
     */
    @Override
    public boolean isEmpty() throws DaoException {

        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(IS_EMPTY)) {
                return resultSet.next();
            }
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
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
        var resultList = new ArrayList<User>();
        User user;
        try (PreparedStatement statement =
                     specification.createStatement(connection);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                user = userBuilder.build(resultSet);
                resultList.add(user);
            }
            return resultList;
        } catch (SQLException | BeanException newE) {
            throw new DaoException(newE);
        }
    }

    /**
     * Updates all fields of the object with the current values.
     *
     * @param object object to update
     * @return true if object was updated, false if not.
     * @throws DaoException if something goes wrong
     */
    @Override
    public boolean update(final User object) throws DaoException {
        return false;
    }
}
