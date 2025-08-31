package e3;

import java.util.Objects;

public record Property(PropertyType type, String id, String postalCode, String address, int size, int rooms, int bathrooms) {

    public int getSize() { return size; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return Objects.equals(id, property.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return type + "\n" +
                id + "\n" +
                postalCode + "\n" +
                address + "\n" +
                size + " meters, " +
                rooms + " rooms, " +
                bathrooms + " bathrooms" + "\n";
    }
}
