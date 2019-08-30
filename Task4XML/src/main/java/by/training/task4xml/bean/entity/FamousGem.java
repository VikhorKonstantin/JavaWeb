package by.training.task4xml.bean.entity;

import java.util.Objects;

public class FamousGem extends Gem {
    private String name;
    private String foundationDate;

    public String getName() {
        return name;
    }

    public String getFoundationDate() {
        return foundationDate;
    }

    public void setName(final String newName) {
        name = newName;
    }

    public void setFoundationDate(final String newFoundationDate) {
        foundationDate = newFoundationDate;
    }

    @Override
    public boolean equals(final Object newO) {
        if (this == newO) return true;
        if (!(newO instanceof FamousGem)) return false;
        if (!super.equals(newO)) return false;
        FamousGem famousGem = (FamousGem) newO;
        return Objects.equals(name, famousGem.name) &&
                Objects.equals(foundationDate, famousGem.foundationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, foundationDate);
    }

    @Override
    public String toString() {
        return "FamousGem{ " + super.toString()
                + " name='" + name + '\''
                + ", foundationDate=" + foundationDate + '}';
    }
}
