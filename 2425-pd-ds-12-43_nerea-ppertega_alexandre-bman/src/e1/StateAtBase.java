package e1;

public class StateAtBase implements State {
    private static final State state = new StateAtBase();

    private StateAtBase() {}

    public static State getInstance() {
        return state;
    }

    @Override
    public void startExercise(Ship ship, NavalBase base, String localization) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        ship.setLocation(localization);
        ship.setState(StateAtExercise.getInstance());
        base.updateIncome(ship.getMissionGain());

        System.out.println("Ship " + ship.toString() + " has started exercise on "
                + localization + " | Expected profit: " + ship.getMissionGain());
    }

    @Override
    public void scrap(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        ship.setState(StateScrapped.getInstance());
        ship.setLocation("ship breaker");
        ship.setActive(false);

        System.out.println("A scrapping request has been submitted for " +
                ship.toString());
    }

    @Override
    public String toString() {
        return "at base";
    }
}
