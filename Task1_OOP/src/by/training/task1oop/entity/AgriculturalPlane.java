package by.training.task1oop.entity;

import java.util.Objects;

public class AgriculturalPlane extends Plane {

    /**
     * capacity of a tank for chemical mixture.
     */
    private int chemicalTankCapacity;
    /**
     * efficiency of processing of crops (hectares per hour).
     */
    private int cropsProcessingEfficiency;

    /**
     * @param newId                        planeId.
     * @param newCapacity                  seatingCapacity.
     * @param newPayload                   payload.
     * @param newConsumption               fuel consumption.
     * @param newName                      plane's name.
     * @param newChemicalTankCapacity      chemicalTankCapacity
     * @param newCropsProcessingEfficiency cropsProcessingEfficiency
     */
    public AgriculturalPlane(final long newId, final int newCapacity,
                             final int newPayload, final int newConsumption,
                             final String newName,
                             final int newChemicalTankCapacity,
                             final int newCropsProcessingEfficiency) {
        super(newId, newCapacity, newPayload, newConsumption, newName);
        this.chemicalTankCapacity = newChemicalTankCapacity;
        this.cropsProcessingEfficiency = newCropsProcessingEfficiency;
    }

    /**
     * @return chemicalTankCapacity
     */
    public int getChemicalTankCapacity() {
        return chemicalTankCapacity;
    }

    /**
     * @return cropsProcessingEfficiency
     */
    public int getCropsProcessingEfficiency() {
        return cropsProcessingEfficiency;
    }
    /**
     * @param o object to compare with.
     * @return true(false) if objects equals(not equals)
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgriculturalPlane)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AgriculturalPlane that = (AgriculturalPlane) o;
        return chemicalTankCapacity == that.chemicalTankCapacity
                && cropsProcessingEfficiency == that.cropsProcessingEfficiency;
    }
    /**
     * @return hash code  of a plane.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chemicalTankCapacity,
                cropsProcessingEfficiency);
    }
    /**
     * @return string representing the specified object.
     */
    @Override
    public String toString() {
        return "AgriculturalPlane{" + super.toString()
                + "chemicalTankCapacity=" + chemicalTankCapacity
                + ", cropsProcessingEfficiency=" + cropsProcessingEfficiency
                + '}';
    }
}
