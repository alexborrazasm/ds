package e2.comparators;

import e2.EuroCoin;

import java.util.Comparator;

public class ComparatorCountries implements Comparator<EuroCoin> {

    @Override
    public int compare(EuroCoin o1, EuroCoin o2) {
        return (o1.country().getCountry().compareTo(o2.country().getCountry()));
    }
}
