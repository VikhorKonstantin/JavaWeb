package by.training.paragliding.bean.validator;

import by.training.paragliding.bean.entity.Role;
import by.training.paragliding.bean.entity.User;
import by.training.paragliding.bean.exception.BeanException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class UserValidator implements Validator<User> {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger("main");
    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    private static final String PASSWORD_PATTERN = "[0-9a-zA-z]{8,32}";


    @Override
    public User validate(final HttpServletRequest newRequest)
            throws BeanException {
        User user = new User();
        boolean isValid;
        String email = newRequest.getParameter("email");
        String password = newRequest.getParameter("password");
        String isSportsmanString = Optional.ofNullable(
                newRequest.getParameter("isSportsman"))
                .orElse(Boolean.FALSE.toString());
        logger.debug("email {}, password {}, isSportsman {}",
                email, password, isSportsmanString);
        isValid = email.matches(EMAIL_PATTERN)
                && password.matches(PASSWORD_PATTERN)
                && (isSportsmanString.equals(Boolean.FALSE.toString())
                || isSportsmanString.equals(Boolean.TRUE.toString()));
        if (isValid) {
            Role role;
            boolean isSportsman = Boolean.parseBoolean(isSportsmanString);
            if (isSportsman) {
                role = Role.REGISTERED_SPORTSMAN;

            } else {
                role = Role.REGISTERED_USER;
            }
            user.setRole(role);
            user.setEmail(email);
            user.setPassword(password);
            return user;
        } else {
            throw new BeanException(EXC_MSG);
        }

    }
}
