package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    /**
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new User object.
     * @throws BeanException if some exceptions where thrown while object building.
     */
    @Override
    public User buildFromResultSet(final ResultSet newResultSet)
            throws BeanException {
        try {
            final var user = new User();
            user.setId(newResultSet.getInt("userId"));
            var email = newResultSet.getString("email");
            user.setEmail(email);
            var password = newResultSet.getString("password");
            user.setPassword(password);
            var roleInt = newResultSet.getInt("role");
            var role = Role.values()[roleInt];
            user.setRole(role);
            if (role.equals(Role.REGISTERED_SPORTSMAN)) {
                var sBuilder = new SportsmanBuilder();
                var sportsman = sBuilder.buildFromResultSet(newResultSet);
                user.setSportsman(sportsman);
            }
            logger.debug("User {}", user);
            return user;
        } catch (SQLException newE) {
            throw new BeanException(EXC_MSG, newE);
        }
    }
}
