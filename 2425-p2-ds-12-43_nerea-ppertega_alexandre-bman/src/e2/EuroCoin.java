package e2;

import e2.enums.EuroCountry;
import e2.enums.EuroType;

import java.util.Objects;

public record EuroCoin(EuroType type, EuroCountry country
        , String design, int year) implements  Comparable<EuroCoin> {

    public int getValue() {
        return this.type.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EuroCoin euroCoin = (EuroCoin) o;
        return (type == euroCoin.type &&
                Objects.equals(design, euroCoin.design) &&
                country == euroCoin.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, country, design);
    }

    @Override
    public String toString() {
        return "Coin: " + type +
                "( " + type.getColor() + ", " +  type.getValue() + " cents)\n" +
                "Country: " + country.getCountry() + "( " + country + " )\n" +
                "design: " + design + '\n' +
                "year: " + year + '\n';
    }

    @Override
    public int compareTo(EuroCoin o) {
        int compareValue = (o.getValue() - this.getValue());
        if (compareValue != 0)
            return compareValue;

        int compareCountry = this.country.getCountry().compareTo(o.country.getCountry());
        if (compareCountry != 0)
            return compareCountry;

        return this.design.compareTo(o.design);
    }
}
