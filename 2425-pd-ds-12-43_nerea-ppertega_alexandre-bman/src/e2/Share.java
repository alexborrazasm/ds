package e2;

import java.util.ArrayList;
import java.util.List;

// subject
public abstract class Share {
    private final List<CloseObserverShare> observersClose = new ArrayList<>();
    private final List<InstantObserverShare> observersInstant = new ArrayList<>();

    public void attachCloseObserver(CloseObserverShare observer) {
        if (observer == null || observersClose.contains(observer)) {
            throw new IllegalArgumentException();
        }
        observersClose.add(observer);
    }

    public void attachInstantObserver(InstantObserverShare observer) {
        if (observer == null || observersInstant.contains(observer)) {
            throw new IllegalArgumentException();
        }
        observersInstant.add(observer);
    }

    public void detachCloseObserver(CloseObserverShare observer) {
        if (observer == null || !observersClose.contains(observer)) {
            throw new IllegalArgumentException();
        }
        observersClose.remove(observer);
    }

    public void detachInstantObserver(InstantObserverShare observer) {
        if (observer == null || !observersInstant.contains(observer)) {
            throw new IllegalArgumentException();
        }
        observersInstant.remove(observer);
    }

    public boolean isCloseObserver(CloseObserverShare obs) {
        return this.observersClose.contains(obs);
    }

    public boolean isInstantObserver(InstantObserverShare obs) {
        return this.observersInstant.contains(obs);
    }

    public void notifyInstantObservers(String code, int dayHigh, int dayLow, int volume) {
        for (InstantObserverShare o : observersInstant) {
            o.updateInstants(code, dayHigh, dayLow, volume);
        }
    }

    public void notifyCloseObservers(String code, int closePrice) {
        for (CloseObserverShare o : observersClose) {
            o.updateClose(code, closePrice);
        }
    }
}
