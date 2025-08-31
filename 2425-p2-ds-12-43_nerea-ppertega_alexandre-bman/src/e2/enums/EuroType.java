package e2.enums;

import static e2.enums.EuroColor.*;

public enum EuroType {
    ONE_CENT(BRONZE, 5),
    TWO_CENTS(BRONZE, 2),
    FIVE_CENTS(BRONZE, 5),
    TEN_CENTS(GOLD, 10),
    TWENTY_CENTS(GOLD, 20),
    FIFTY_CENTS(GOLD, 50),
    ONE_EURO(GOLD_SILVER, 100),
    TWO_EUROS(GOLD_SILVER, 200),;

    private final EuroColor color;

    private final int value;

    EuroType(EuroColor color, int value) {
        this.color = color;
        this.value = value;
    }

    public EuroColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }
}
