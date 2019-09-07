package by.training.task4web.bean.entity;

import java.beans.JavaBean;
import java.time.LocalDate;
import java.util.Objects;

@JavaBean
public class Gem {
    /**
     * Preciousness of the gem.
     */
    private Preciousness preciousness;
    /**
     * Origin of the gem.
     */
    private String origin;

    @JavaBean
    public class VisualParameters {
        /**
         * Color of the stone.
         */
        private String color;
        /**
         * Transparency of the stone.
         */
        private int transparency;
        /**
         * Number of facets.
         */
        private int cuttingMethod = 0;

        /**
         * Sets color into newColor.
         *
         * @param newColor new color
         */
        public void setColor(final String newColor) {
            color = newColor;
        }

        /**
         * Sets transparency into newTransparency.
         *
         * @param newTransparency new transparency
         */
        public void setTransparency(final int newTransparency) {
            transparency = newTransparency;
        }

        /**
         * Sets cuttingMethod into newCuttingMethod.
         *
         * @param newCuttingMethod new cuttingMethod
         */
        public void setCuttingMethod(final int newCuttingMethod) {
            cuttingMethod = newCuttingMethod;
        }

        /**
         * Returns color of the stone.
         *
         * @return color of the stone
         */
        public String getColor() {
            return color;
        }

        /**
         * Returns transparency of the stone.
         *
         * @return transparency of the stone
         */
        public int getTransparency() {
            return transparency;
        }

        /**
         * Returns cuttingMethod of the stone.
         *
         * @return cuttingMethod of the stone
         */
        public int getCuttingMethod() {
            return cuttingMethod;
        }

        /**
         * Returns a String representation of the stone.
         *
         * @return a String representation of the stone.
         */
        @Override
        public String toString() {
            return "VisualParameters{" + "color='" + color + '\''
                    + ", transparency=" + transparency
                    + ", cuttingMethod=" + cuttingMethod + '}';
        }
    }

    /**
     * Visual parameters of the stone.
     */
    private VisualParameters visualParameters = new VisualParameters();
    /**
     * Value (in carats) of the stone.
     */
    private float value;
    /**
     * Id of the stone.
     */
    private long gemId;
    /**
     * Date of the auction where the gem was bought.
     */
    private LocalDate auctionDate;
    /**
     * Type of the stone.
     */
    private GemType gemType;

    /**
     * Returns a gemType of the stone.
     *
     * @return a gemType of the stone
     */
    public GemType getGemType() {
        return gemType;
    }

    /**
     * Sets gemType of the stone into newGemType.
     *
     * @param newGemType new gemType.
     */
    public void setGemType(final GemType newGemType) {
        gemType = newGemType;
    }

    /**
     * Returns a preciousness of the stone.
     *
     * @return a preciousness of the stone
     */
    public Preciousness getPreciousness() {
        return preciousness;
    }

    /**
     * Returns a origin of the stone.
     *
     * @return a origin of the stone
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Returns a visualParameters of the stone.
     *
     * @return a visualParameters of the stone
     */
    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    /**
     * Returns a value of the stone.
     *
     * @return a value of the stone
     */
    public float getValue() {
        return value;
    }

    /**
     * Returns a gemId of the stone.
     *
     * @return a gemId of the stone
     */
    public long getGemId() {
        return gemId;
    }

    /**
     * Returns a auctionDate of the stone.
     *
     * @return a auctionDate of the stone
     */
    public LocalDate getAuctionDate() {
        return auctionDate;
    }

    /**
     * Sets preciousness of the stone into newPreciousness.
     *
     * @param newPreciousness new preciousness.
     */
    public void setPreciousness(final Preciousness newPreciousness) {
        preciousness = newPreciousness;
    }

    /**
     * Sets origin of the stone into newOrigin.
     *
     * @param newOrigin new origin.
     */
    public void setOrigin(final String newOrigin) {
        origin = newOrigin;
    }

    /**
     * Sets value of the stone into newValue.
     *
     * @param newValue new value.
     */
    public void setValue(final float newValue) {
        value = newValue;
    }

    /**
     * Sets gemId of the stone into newGemId.
     *
     * @param newGemId new gemId.
     */
    public void setGemId(final long newGemId) {
        gemId = newGemId;
    }

    /**
     * Sets auctionDate of the stone into newAuctionDate.
     *
     * @param newAuctionDate new auctionDate.
     */
    public void setAuctionDate(final LocalDate newAuctionDate) {
        auctionDate = newAuctionDate;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param newO the reference object with which to compare.
     * @return true if this object is the same as
     * the obj argument; false otherwise.
     */
    @Override
    public boolean equals(final Object newO) {
        if (this == newO) {
            return true;
        }
        if (!(newO instanceof Gem)) {
            return false;
        }
        Gem gem = (Gem) newO;
        return Float.compare(gem.value, value) == 0
                && gemId == gem.gemId
                && preciousness == gem.preciousness
                && Objects.equals(origin, gem.origin)
                && Objects.equals(visualParameters, gem.visualParameters)
                && Objects.equals(auctionDate, gem.auctionDate)
                && gemType == gem.gemType;
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for the object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(preciousness, origin,
                visualParameters, value, gemId, auctionDate);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "Gem{" + "preciousness=" + preciousness
                + ", origin='" + origin + '\'' + ", visualParameters= "
                + visualParameters + ", value="
                + value + ", gemId="
                + gemId + ", auctionDate="
                + auctionDate + '}';
    }

    public enum GemType {
        /**
         * Diamond is a precious stone. May have different colors.
         */
        DIAMOND,
        /**
         * Ruby is a precious stone, usual color is red.
         */
        RUBY,
        /**
         * Sapphire is a precious stone, usual color is blue.
         */
        SAPPHIRE,
        /**
         * Pearl is a precious stone, usual color is white.
         */
        PEARL,
        /**
         * AMETHYST is a precious stone.  May have different colors.
         */
        AMETHYST,
        /**
         * GARNET is a semi-precious stone, usual color is red.
         */
        GARNET,
        /**
         * TOURMALINE is a semi-precious stone, usual color is red.
         */
        TOURMALINE
    }

    public enum Preciousness {
        /**
         * PRECIOUS type.
         */
        PRECIOUS,
        /**
         * SEMI_PRECIOUS type.
         */
        SEMI_PRECIOUS
    }
}
