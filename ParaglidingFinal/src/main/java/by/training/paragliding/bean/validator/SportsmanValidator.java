package by.training.paragliding.bean.validator;

import by.training.paragliding.bean.entity.Sportsman;
import by.training.paragliding.bean.exception.BeanException;
import com.neovisionaries.i18n.CountryCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SportsmanValidator implements Validator<Sportsman> {
    /**
     * Logger.
     */
    private Logger logger = LogManager.getLogger("main");
    private static final String NAME_PATTERN = "^[a-zA-Z\\s]+";
    private static final String GENDER_PATTERN = "^M | F";
    private static final String RATING_PATTERN = "^[0-9]{0,4}\\.[0-9]}";
    private static final String CIVL_PATTERN = "^[0-9]{2,6}$";
    @Override
    public Sportsman validate(final HttpServletRequest newRequest) throws BeanException {
        Sportsman sportsman = new Sportsman();
        var name = newRequest.getParameter("name");
        var surname =newRequest.getParameter("surname");
        var countryString = newRequest.getParameter("country");
        var genderString = newRequest.getParameter("gender");
        var ratingString = newRequest.getParameter("rating");
        var civlIdString =  newRequest.getParameter("id");


        final List<CountryCode> countryCodes =
                CountryCode.findByName(countryString);
        boolean isValid = name.matches(NAME_PATTERN)
                && surname.matches(NAME_PATTERN)
                && (!countryCodes.isEmpty())
                && genderString.matches(GENDER_PATTERN)
                && ratingString.matches(RATING_PATTERN)
                && civlIdString.matches(CIVL_PATTERN);
        if (isValid) {
            sportsman.setCivlId(Integer.parseInt(civlIdString));
            sportsman.setGender(genderString.charAt(0));
            sportsman.setName(name);
            sportsman.setSurname(surname);
            sportsman.setRating(Float.parseFloat(ratingString));
            sportsman.setCountryCode(
                    CountryCode.findByName(countryString).get(0));
            return sportsman;
        } else {
            throw new BeanException(EXC_MSG);
        }

    }


}
