package e1;

public class StateSunk implements State {
    private static final State state = new StateSunk();

    private StateSunk() {}

    public static State getInstance() {
        return state;
    }

    @Override
    public String toString() {
        return "sunk";
    }
}
