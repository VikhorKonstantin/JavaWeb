package by.training.paragliding.bean.entity;

import com.neovisionaries.i18n.CountryCode;

public class Sportsman {
    /**
     * Person name.
     */
    private String name;
    /**
     * Person surname.
     */
    private String surname;
    /**
     * FAI identifier.
     */
    private int civlId;
    /**
     * Person gender.
     */
    private char gender;
    /**
     * ISO CountryCode of country a sportsman compete for.
     */
    private CountryCode countryCode;
    /**
     * FAI rating of a sportsmen.
     */
    private float rating;
    /**
     * Path in file system of sportsmen avatar.
     */
    private String imagePath;

    /**
     * Returns imagePath.
     *
     * @return imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Sets new value for Sportsman imagePath.
     *
     * @param newImagePath new value for Sportsman imagePath
     */
    public void setImagePath(final String newImagePath) {
        imagePath = newImagePath;
    }

    /**
     * Returns Sportsman name.
     *
     * @return Sportsman name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets new value for Sportsman name.
     *
     * @param newName new value for Sportsman name
     */
    public void setName(final String newName) {
        name = newName;
    }

    /**
     * Returns Sportsman surname.
     *
     * @return Sportsman surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets new value for Sportsman surname.
     *
     * @param newSurname new value for Sportsman surname
     */
    public void setSurname(final String newSurname) {
        surname = newSurname;
    }

    /**
     * Returns Sportsman civlId.
     *
     * @return Sportsman civlId
     */
    public int getCivlId() {
        return civlId;
    }

    /**
     * Sets new value for Sportsman civlId.
     *
     * @param newCivlId new value for Sportsman civlId
     */
    public void setCivlId(final int newCivlId) {
        civlId = newCivlId;
    }

    /**
     * Returns Sportsman gender.
     *
     * @return Sportsman gender
     */
    public char getGender() {
        return gender;
    }

    /**
     * Sets new value for Sportsman gender.
     *
     * @param newGender new value for Sportsman gender
     */
    public void setGender(final char newGender) {
        gender = newGender;
    }

    /**
     * Returns Sportsman countryCode.
     *
     * @return Sportsman countryCode
     */
    public CountryCode getCountryCode() {
        return countryCode;
    }

    /**
     * Sets new value for Sportsman countryCode.
     *
     * @param newCountryCode new value for Sportsman countryCode
     */
    public void setCountryCode(final CountryCode newCountryCode) {
        countryCode = newCountryCode;
    }

    /**
     * Returns Sportsman rating.
     *
     * @return Sportsman rating
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets new value for Sportsman rating.
     *
     * @param newRating new value for Sportsman rating
     */
    public void setRating(final float newRating) {
        rating = newRating;
    }

    /**
     * Returns a String representation of the Sportsmen.
     *
     * @return a String representation of the Sportsmen
     */
    @Override
    public String toString() {
        return "Sportsman{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", civlId=" + civlId +
                ", gender=" + gender +
                ", countryCode=" + countryCode +
                ", rating=" + rating +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
