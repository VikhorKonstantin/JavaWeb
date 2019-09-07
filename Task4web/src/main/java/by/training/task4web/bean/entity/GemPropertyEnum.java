package by.training.task4web.bean.entity;

/**
 * Describe fields of entity objects.
 */
public enum GemPropertyEnum {
    /**
     * Represents gemId field.
     */
    GEM_ID("gemId"),
    /**
     * Represents auctionDate field.
     */
    AUCTION_DATE("auctionDate"),
    /**
     * Represents preciousness field.
     */
    PRECIOUSNESS("preciousness"),
    /**
     * Represents gemType field.
     */
    GEM_TYPE("gemType"),
    /**
     * Represents origin field.
     */
    ORIGIN("origin"),
    /**
     * Represents visualParameters field.
     */
    VISUAL_PARAMS("visualParameters"),
    /**
     * Represents color field.
     */
    COLOR("color"),
    /**
     * Represents transparency field.
     */
    TRANSPARENCY("transparency"),
    /**
     * Represents cuttingMethod field.
     */
    CUTTING_METHOD("cuttingMethod"),
    /**
     * Represents value field.
     */
    VALUE("value"),
    /**
     * Represents foundationDate field.
     */
    FOUNDATION_DATE("foundationDate"),
    /**
     * Represents name field.
     */
    NAME("name");
    /**
     * A String representation of the field.
     */
    private String value;

    GemPropertyEnum(final String newValue) {
        value = newValue;
    }

    /**
     * Returns a String representation of the field.
     * @return a String representation of the field.
     */
    public String getValue() {
        return value;
    }
}
