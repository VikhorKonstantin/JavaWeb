package by.training.paragliding.bean.entity;

import com.neovisionaries.i18n.CountryCode;

import java.io.Serializable;
import java.util.Objects;

public class Sportsman implements Serializable {

    /**
     * Creates new empty Sportsman object.
     */
    public Sportsman() {
    }

    /**
     * Creates new Sportsman instance.
     *
     * @param newName        new sportsman name
     * @param newSurname     new sportsman surname.
     * @param newCivlId      new sportsmen id
     * @param newGender      new gender
     * @param newCountryCode new sportsman country code
     * @param newRating      new sportsman Rating
     * @param newImagePath   new sportsman imagePath
     */
    public Sportsman(final int newCivlId, final String newName,
                     final String newSurname, final char newGender,
                     final CountryCode newCountryCode, final float newRating,
                     final String newImagePath) {
        civlId = newCivlId;
        name = newName;
        surname = newSurname;
        gender = newGender;
        countryCode = newCountryCode;
        rating = newRating;
        imagePath = newImagePath;
    }

    /**
     * FAI identifier.
     */
    private int civlId;
    /**
     * User.
     */
    private User user;
    /**
     * Person name.
     */
    private String name;
    /**
     * Person surname.
     */
    private String surname;
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
     * Returns user.
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user-field into newUser.
     *
     * @param newUser new user
     */
    public void setUser(final User newUser) {
        user = newUser;
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


    @Override
    public boolean equals(final Object newO) {
        if (this == newO) return true;
        if (newO == null || getClass() != newO.getClass()) return false;
        Sportsman sportsman = (Sportsman) newO;
        return civlId == sportsman.civlId &&
                gender == sportsman.gender &&
                Float.compare(sportsman.rating, rating) == 0 &&
                Objects.equals(name, sportsman.name) &&
                Objects.equals(surname, sportsman.surname) &&
                countryCode == sportsman.countryCode &&
                Objects.equals(imagePath, sportsman.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(civlId);
    }
}
