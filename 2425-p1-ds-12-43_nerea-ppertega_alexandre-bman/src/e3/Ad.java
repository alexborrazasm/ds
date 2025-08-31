package e3;

import java.util.Objects;

public class Ad {
    private String agency;
    private Property property;
    private AdType type;
    private double price;

    public Ad(String agency, Property property, AdType type, double price) {
        this.agency = agency;
        this.property = property;
        this.type = type;
        this.price = price;
    }

    public Ad(Ad a1) { this(a1.agency, a1.property, a1.type, a1.price); }

    public boolean isPropertyEqual(Ad a2) {
        if (a2 == null) {
            return false;
        }
        return this.property.equals(a2.property);
    }

    public boolean isPriceNormal() {
        if (this.type == AdType.PURCHASE) {
            double MIN_PURCHASE = 10000;
            return this.price >= MIN_PURCHASE;
        } else if (this.type == AdType.RENTAL) {
            double MIN_RENTAL = 200, MAX_RENTAL = 20000;
            return this.price <= MAX_RENTAL && this.price >= MIN_RENTAL;
        } else {
            return false;
        }
    }

    public double priceMetersEuros() {
        if (this.property.getSize() == 0) {
            throw new ArithmeticException();
        }

        return this.price / this.property.getSize();
    }

    public double getPriceInEuros() {
        return price;
    }

    public void dropPrice(double percent) {
        if (percent > 100 || percent < 1) {
            throw new IllegalArgumentException();
        }

        this.price = this.price - (percent * this.price) / 100.0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return Double.compare(ad.price, this.price) == 0 &&
               Objects.equals(ad.agency, this.agency) &&
               this.isPropertyEqual(ad) &&
               this.type == ad.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, property, type, price);
    }
}
