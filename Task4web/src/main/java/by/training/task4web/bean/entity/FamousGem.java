package by.training.task4web.bean.entity;

import java.beans.JavaBean;
import java.util.Objects;

/**
 * Describe famous gem entity.
 */
@JavaBean
public class FamousGem extends Gem {
    /**
     * Name of the stone.
     */
    private String name;
    /**
     * Foundation date of the stone.
     */
    private String foundationDate;

    /**
     * Returns name of the stone.
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns foundation date of the stone.
     * @return String foundationDate
     */
    public String getFoundationDate() {
        return foundationDate;
    }

    /**
     * Sets name of the stone into newName.
     * @param newName new name
     */
    public void setName(final String newName) {
        name = newName;
    }
    /**
     * Sets foundationDate of the stone into newFoundationDate.
     * @param newFoundationDate new foundationDate
     */
    public void setFoundationDate(final String newFoundationDate) {
        foundationDate = newFoundationDate;
    }

    /**
     *Indicates whether some other object is "equal to" this one.
     * @param newO the reference object with which to compare.
     * @return true if this object is the same as
     * the obj argument; false otherwise.
     */
    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (!(newO instanceof FamousGem)) {
            return false;
        }
        if (!super.equals(newO)) {
            return false;
        }
        FamousGem famousGem = (FamousGem) newO;
        return Objects.equals(name, famousGem.name)
                && Objects.equals(foundationDate, famousGem.foundationDate);
    }

    /**
     * Returns a hash code value for the object.
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, foundationDate);
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "FamousGem{ " + super.toString()
                + " name='" + name + '\''
                + ", foundationDate=" + foundationDate + '}';
    }
}
