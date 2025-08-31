package e3;

import e2.enums.*;
import e2.EuroCoin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EuroCoinCollectionIt implements Iterable<EuroCoin> {
    private final List<EuroCoin> collection;
    private int modCount = 0;
    private EuroCountry countryIterator;

    public EuroCoinCollectionIt() {
        this.collection = new ArrayList<>();
    }

    public boolean addCoin(EuroCoin coin) {
        if (coin == null) {
            throw new IllegalArgumentException();
        }

        if (!containsCoin(coin)) {
            collection.add(coin);
            modCount++;
            return true;
        }
        return false;
    }

    public boolean delCoin(EuroCoin coin) {
        if (collection.remove(coin)){
            modCount++;
            return true;
        }
        return false;
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

    public void setCountryIterator(EuroCountry countryIterator) {
        this.countryIterator = countryIterator;
    }

    @Override
    public Iterator<EuroCoin> iterator(){
        return new EuroCoinIterator(this, this.countryIterator);
    }

    public int getModCount() {
        return modCount;
    }

    public List<EuroCoin> getCollection() {
        return collection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EuroCoinCollectionIt that = (EuroCoinCollectionIt) o;

        if (this.countCoins() != that.countCoins()) return false;

        // compare each coin in collections
        for (EuroCoin coin : this.collection) {
            if (!that.containsCoin(coin)) {
                return false;
            }
        }
        return true;
    }

    public EuroCoin getCoin (int position) {
        return collection.get(position);
    }
}



