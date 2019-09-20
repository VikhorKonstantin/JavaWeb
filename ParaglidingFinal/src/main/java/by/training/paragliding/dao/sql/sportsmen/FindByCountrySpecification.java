package by.training.paragliding.dao.sql.sportsmen;

import by.training.paragliding.dao.exception.DaoException;
import by.training.paragliding.dao.sql.Specification;
import com.neovisionaries.i18n.CountryCode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FindByCountrySpecification implements Specification {
    private static final String SQL = "SELECT `civl_id`, `name`,"
            + " `surname`, `gender`,"
            + " `country`, `rating`, `image_path`"
            + " FROM `sportsmen` WHERE `country` = ?";
    /**
     * CountryCode of sportsmen to find.
     */
    private CountryCode countryCode;

    /**
     * Creates new FindSpecification
     * @param newCountryCode count
     */
    public FindByCountrySpecification(final CountryCode newCountryCode) {
        countryCode = newCountryCode;
    }

    @Override
    public PreparedStatement createStatement(final Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, countryCode.getAlpha2());
            return statement;
        } catch (SQLException newE) {
            throw new DaoException(newE);
        }
    }
}
