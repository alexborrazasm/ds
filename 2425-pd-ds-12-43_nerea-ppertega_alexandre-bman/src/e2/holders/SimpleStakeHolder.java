package e2.holders;

import e2.CloseObserverShare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// specific observer
public class SimpleStakeHolder implements CloseObserverShare {
    private final Map<String, List<Integer>> data;

    public SimpleStakeHolder() {
        data = new HashMap<>();
    }

    private void addData(String key, Integer value) {
        data.putIfAbsent(key, new ArrayList<>());
        data.get(key).add(value);
    }

    public List<Integer> getData(String key) {
        return data.getOrDefault(key, new ArrayList<>());
    }

    // Observer
    @Override
    public void updateClose(String code, int closePrice) {
        System.out.printf("""
            ----------------------CLOSE_PRICE----------------------
            COMPLEX STAKE HOLDER received a close update
            %s with closing price %d
            -------------------------------------------------------
            """, code, closePrice);
        // Store data
        this.addData(code, closePrice);
    }
}