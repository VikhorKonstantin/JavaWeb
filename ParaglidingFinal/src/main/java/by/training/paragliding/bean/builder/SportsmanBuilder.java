package by.training.paragliding.bean.builder;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.exception.BeanException;
import com.neovisionaries.i18n.CountryCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SportsmanBuilder implements Builder<Sportsman> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");

    /**
     * @param newResultSet resultSet (result of executing sql statements)
     * @return new Sportsman object.
     * @throws BeanException if some exceptions where
     *                       thrown while object building.
     */
    @Override
    public Sportsman buildFromResultSet(final ResultSet newResultSet)
            throws BeanException {
        final var sportsman = new Sportsman();
        try {
            sportsman.setCivlId(newResultSet.getInt("civl_id"));
            if (newResultSet.wasNull()) {
                return null;
            }
            sportsman.setName(newResultSet.getString("sportsmanName"));
            sportsman.setSurname(newResultSet.getString("surname"));
            sportsman.setGender(newResultSet.getString("gender").charAt(0));
            sportsman.setRating(newResultSet.getFloat("rating"));
            sportsman.setCountryCode(CountryCode.valueOf(
                    newResultSet.getString("country")));
            sportsman.setImagePath(newResultSet.getString("image_path"));
            return sportsman;
        } catch (SQLException newE) {
            throw new BeanException(EXC_MSG, newE);
        }
    }
}
