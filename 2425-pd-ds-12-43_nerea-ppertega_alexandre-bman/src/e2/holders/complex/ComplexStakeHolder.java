package e2.holders.complex;

import e2.CloseObserverShare;
import e2.InstantObserverShare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// specific observer
public class ComplexStakeHolder implements CloseObserverShare, InstantObserverShare {
    private final Map<String, List<ShareData>> instantData;
    private final Map<String, List<Integer>> closeData;

    public ComplexStakeHolder() {
        this.instantData = new HashMap<>();
        this.closeData = new HashMap<>();
    }

    // Instant map
    private void addDataInstant(String key, ShareData shareData) {
        instantData.computeIfAbsent(key, k -> new ArrayList<>()).add(shareData);
    }

    public List<ShareData> getDataInstant(String key) {
        return instantData.getOrDefault(key, new ArrayList<>());
    }

    // Close data
    private void addCloseData(String key, Integer value) {
        closeData.putIfAbsent(key, new ArrayList<>());
        closeData.get(key).add(value);
    }

    public List<Integer> getCloseData(String key) {
        return closeData.getOrDefault(key, new ArrayList<>());
    }

    // Observers
    @Override
    public void updateClose(String code, int closePrice) {
        System.out.printf("""
            ----------------------CLOSE_PRICE----------------------
            COMPLEX STAKE HOLDER received a close update
            %s with closing price %d
            -------------------------------------------------------
            """, code, closePrice);

        // Store data
        this.addCloseData(code, closePrice);
    }

    public void updateInstants(String code, int dayHigh, int dayLow, int volume) {
        System.out.printf("""
            ====================== INSTANT_PRICE =====================
            COMPLEX STAKE HOLDER received an instant update:
            Code: %s
            | High: %d
            | Low: %d
            | Volume: %d
            =========================================================
            """, code, dayHigh, dayLow, volume);

        // Store data
        this.addDataInstant(code, new ShareData(dayHigh, dayLow, volume));
        }
    }