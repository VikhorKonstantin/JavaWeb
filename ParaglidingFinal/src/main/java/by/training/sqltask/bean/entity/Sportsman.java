package by.training.sqltask.bean.entity;

import com.neovisionaries.i18n.CountryCode;

public class Sportsman {
    private String name;
    private String surname;
    //private List<Competition>
    private int civlId;
    private char gender;
    private CountryCode countryCode;
    private float rating;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String newImagePath) {
        imagePath = newImagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        name = newName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String newSurname) {
        surname = newSurname;
    }

    public int getCivlId() {
        return civlId;
    }

    public void setCivlId(final int newCivlId) {
        civlId = newCivlId;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(final char newGender) {
        gender = newGender;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(final CountryCode newCountryCode) {
        countryCode = newCountryCode;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(final float newRating) {
        rating = newRating;
    }

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
