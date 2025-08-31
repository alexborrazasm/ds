package e2.comparators;

import e2.EuroCoin;

import java.util.Comparator;

public class ComparatorYear implements Comparator<EuroCoin> {

    @Override
    public int compare(EuroCoin o1, EuroCoin o2) {
        return (o1.year() - o2.year());
    }
}
