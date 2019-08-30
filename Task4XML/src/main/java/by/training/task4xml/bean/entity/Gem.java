package by.training.task4xml.bean.entity;

import java.time.LocalDate;
import java.util.Objects;

public class Gem {
    /**
     * Preciousness of the gem.
     */
    private Preciousness preciousness;
    /**
     * Origin of the gem.
     */
    private String origin;
    
    public class VisualParameters {
        private String color;
        private int transparency;
        /**
         * Number of facets.
         */
        private int cuttingMethod = 0;

        public void setColor(final String newColor) {
            color = newColor;
        }

        public void setTransparency(final int newTransparency) {
            transparency = newTransparency;
        }

        public void setCuttingMethod(final int newCuttingMethod) {
            cuttingMethod = newCuttingMethod;
        }

        @Override
        public String toString() {
            return "VisualParameters{" +
                    "color='" + color + '\'' +
                    ", transparency=" + transparency +
                    ", cuttingMethod=" + cuttingMethod +
                    '}';
        }
    }
    private VisualParameters visualParameters = new VisualParameters();
    private float value;
    private long gemId;
    private LocalDate auctionDate;
    private GemType gemType;

    public GemType getGemType() {
        return gemType;
    }

    public void setGemType(final GemType newGemType) {
        gemType = newGemType;
    }

    public Preciousness getPreciousness() {
        return preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public VisualParameters getVisualParameters() {
        return visualParameters;
    }

    public float getValue() {
        return value;
    }

    public long getGemId() {
        return gemId;
    }

    public LocalDate getAuctionDate() {
        return auctionDate;
    }

    public void setPreciousness(final Preciousness newPreciousness) {
        preciousness = newPreciousness;
    }

    public void setOrigin(final String newOrigin) {
        origin = newOrigin;
    }

    public void setValue(final float newValue) {
        value = newValue;
    }

    public void setGemId(final long newGemId) {
        gemId = newGemId;
    }

    public void setAuctionDate(final LocalDate newAuctionDate) {
        auctionDate = newAuctionDate;
    }


    @Override
    public boolean equals(final Object newO) {
        if (this == newO) return true;
        if (!(newO instanceof Gem)) return false;
        Gem gem = (Gem) newO;
        return Float.compare(gem.value, value) == 0 &&
                gemId == gem.gemId &&
                preciousness == gem.preciousness &&
                Objects.equals(origin, gem.origin) &&
                Objects.equals(visualParameters, gem.visualParameters) &&
                Objects.equals(auctionDate, gem.auctionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preciousness, origin,
                visualParameters, value, gemId, auctionDate);
    }

    @Override
    public String toString() {
        return "Gem{" +
                "preciousness=" + preciousness +
                ", origin='" + origin + '\'' +
                ", visualParameters= " + visualParameters +
                ", value=" + value +
                ", gemId=" + gemId +
                ", auctionDate=" + auctionDate +
                '}';
    }

    public enum GemType {
        DIAMOND,
        RUBY,
        SAPPHIRE,
        PEARL,
        AMETHYST,
        GARNET,
        TOURMALINE
    }

    public enum Preciousness {
        PRECIOUS, SEMI_PRECIOUS
    }
}
