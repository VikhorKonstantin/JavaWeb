package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {
    /**
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new User object.
     * @throws BeanException if some exceptions where thrown while object building.
     */
    @Override
    public User buildFromResultSet(final ResultSet newResultSet) throws BeanException {
        try {
            final var user = new User();
            user.setId(newResultSet.getInt("id"));
            var email = newResultSet.getString("email");
            user.setEmail(email);
            var password = newResultSet.getString("password");
            user.setPassword(password);
            var roleInt = newResultSet.getInt("role");
            var role = Role.values()[roleInt];
            user.setRole(role);
            return user;
        } catch (SQLException newE) {
            throw new BeanException(EXC_MSG, newE);
        }
    }
}
