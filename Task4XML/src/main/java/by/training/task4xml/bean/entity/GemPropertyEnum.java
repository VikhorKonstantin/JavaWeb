package by.training.task4xml.bean.entity;

public enum GemPropertyEnum {
    GEM_ID("gemId"),
    AUCTION_DATE("auctionDate"),
    PRECIOUSNESS("preciousness"),
    GEM_TYPE("gemType"),
    ORIGIN("origin"),
    COLOR("color"),
    TRANSPARENCY("transparency"),
    CUTTING_METHOD("cuttingMethod"),
    VALUE("value"),
    FOUNDATION_DATE("foundationDate"),
    NAME("name");
    private String value;

    GemPropertyEnum(final String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
