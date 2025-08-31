package e3;

import e2.enums.*;
import e2.EuroCoin;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EuroCoinIterator implements Iterator<EuroCoin> {
    private final EuroCoinCollectionIt euroCoinCollectionIt;
    private EuroCountry euroCountry;
    private int position;
    private boolean removed;
    private int expectedModCount;

    public EuroCoinIterator(EuroCoinCollectionIt euroCoinCollectionIt,
                            EuroCountry euroCountry) {
        this.euroCoinCollectionIt = euroCoinCollectionIt;
        this.euroCountry = euroCountry;
        this.position = -1;
        removed = true;
        this.expectedModCount = euroCoinCollectionIt.getModCount();
    }

    private int getNextPosition() {
        // unspecified country
        if (euroCountry == null){
            if (position + 1 < euroCoinCollectionIt.getCollection().size()){
                return position + 1;
            }
            else{
                return -1;
            }
        }
        // specified country
        else{
            for (int i = position + 1; i < euroCoinCollectionIt.getCollection().size(); i++){
                if (euroCoinCollectionIt.getCollection().get(i).country().equals(euroCountry)){
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        // list was modified during iteration
        if (expectedModCount != euroCoinCollectionIt.getModCount()){
            throw new ConcurrentModificationException();
        }
        return getNextPosition() >= 0; // getNextPosition >= 0 -> true
    }

    @Override
    public EuroCoin next() {
        // list was modified during iteration
        if (expectedModCount != euroCoinCollectionIt.getModCount()){
            throw new ConcurrentModificationException();
        }

        int newPosition = getNextPosition();

        if (newPosition >= 0){
            position = newPosition;
            return euroCoinCollectionIt.getCollection().get(position);
        }
        else{
            position = -1;
            throw new NoSuchElementException();
        }
    }

    @Override
    public void remove() {
        // list was modified during iteration
        if (expectedModCount != euroCoinCollectionIt.getModCount()){
            throw new ConcurrentModificationException();
        }
        // verify if remove() was called without next()
        if (position >= 0) {
            EuroCoin pos = this.euroCoinCollectionIt.getCoin(position);
            this.euroCoinCollectionIt.delCoin(pos);
            expectedModCount++;
            position--;
        }
        else{
            throw new IllegalStateException() ;
        }
    }
}