package e1;

public class StateScrapped implements State {
    private static final State state = new StateScrapped();

    private StateScrapped() {}

    public static State getInstance() {
        return state;
    }

    @Override
    public String toString() {
        return "decommissioned";
    }
}
