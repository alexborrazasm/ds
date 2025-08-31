package e2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EuroCoinCollection {
    private final List<EuroCoin> collection;

    public EuroCoinCollection() {
        this.collection = new ArrayList<>();
    }

    public boolean addCoin(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException();
        }

        if (!containsCoin(coin)) {
            collection.add(coin);
            return true;
        }
        return false;
    }

    public boolean delCoin(EuroCoin coin) {
        return collection.remove(coin);
    }

    public int countCoins() {
        return collection.size();
    }

    public int totalValue() {
        int total = 0;
        for (EuroCoin coin : collection) {
            total += coin.getValue();
        }
        return total;
    }

    public boolean containsCoin(EuroCoin coin) {
        return collection.contains(coin);
    }

    public void sort(Comparator<EuroCoin> comparator) {
        if (comparator != null) {
            collection.sort(comparator);
        }
    }

    public void sort() {
        Collections.sort(collection);
    }

    public List<EuroCoin> getCollection() {
        return collection;
    }
}