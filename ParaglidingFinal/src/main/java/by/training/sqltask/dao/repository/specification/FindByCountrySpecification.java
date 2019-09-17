package by.training.sqltask.dao.repository.specification;

import by.training.sqltask.dao.exception.DaoException;
import com.neovisionaries.i18n.CountryCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByCountrySpecification implements Specification {
    /**
     * CountryCode of sportsmen to find.
     */
    private CountryCode countryCode;

    /**
     * Creates new FindSpecification
     * @param newCountryCode
     */
    public FindByCountrySpecification(final CountryCode newCountryCode) {
        countryCode = newCountryCode;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        final String sqlRequest = "SELECT `civl_id`, `name`, `surname`, `gender`,"
                + " `country`, `rating`, `image_path` FROM `sportsmen` WHERE `country` = ?";
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setString(1, countryCode.getAlpha2());
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
