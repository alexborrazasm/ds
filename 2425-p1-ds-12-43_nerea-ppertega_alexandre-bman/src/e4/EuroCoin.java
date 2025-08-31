package e4;

import java.util.Objects;

public record EuroCoin(EuroType type, EuroCountry country, String design, int year) {

    public int getValue() {
        return this.type.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EuroCoin euroCoin = (EuroCoin) o;
        return type == euroCoin.type &&
               Objects.equals(design, euroCoin.design) &&
               country == euroCoin.country;
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
}
