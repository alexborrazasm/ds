package e2;

// specific subject
public class SpecificShare extends Share {
    private final String code;
    private int closePrice;
    private int price;
    private int dayHigh;
    private int dayLow;
    private int volume;

    public SpecificShare(String code, int price) {
        if (code == null || code.length() > "AAPL".length() || price < 0) {
            throw new IllegalArgumentException();
        }

        this.code = code;
        this.price = price;
        this.closePrice = price;
        this.dayHigh = price;
        this.dayLow = price;
        this.volume = 0;
    }

    // Getters
    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public int getClosePrice() {
        return closePrice;
    }

    public int getDayHigh() {
        return dayHigh;
    }

    public int getDayLow() {
        return dayLow;
    }

    public int getVolume() {
        return volume;
    }

    // Setters
    public void updatePrice(int price) {
        boolean notify = false;

        if (price < 0) {
            throw new IllegalArgumentException();
        }
        this.price = price;

        if (this.dayHigh < price) {
            this.dayHigh = price;
            notify = true;
        }

        if (this.dayLow > price) {
            this.dayLow = price;
            notify = true;
        }

        if (notify) {
            super.notifyInstantObservers(code, dayHigh, dayLow, volume);
        }
    }

    public void setClosePrice(int closePrice) {
        if (closePrice < 0) {
            throw new IllegalArgumentException();
        }
        this.closePrice = closePrice;
        this.updatePrice(closePrice);

        super.notifyCloseObservers(code, closePrice);
    }

    public void setVolume(int volume) {
        this.volume = volume;
        super.notifyInstantObservers(code, dayHigh, dayLow, volume);
    }
}
