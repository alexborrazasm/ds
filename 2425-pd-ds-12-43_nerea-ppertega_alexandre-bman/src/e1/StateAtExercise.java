package e1;

public class StateAtExercise implements State {
    private static final State state = new StateAtExercise();

    private StateAtExercise() {}

    public static State getInstance() {
        return state;
    }

    @Override
    public void finishExerciseSuccessful(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        base.updateIncome(-ship.getMissionGain());
        base.updateFunds(ship.getMissionGain());
        ship.setState(StateAtBase.getInstance());
        ship.setLocation("base");
        ship.addCompletedExercises();

        System.out.println("The ship" + ship.toString() +
                " has just arrived at base | Mission successful | Income: " +
                ship.getMissionGain());
    }

    @Override
    public void finishExerciseDamage(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        base.updateIncome(-ship.getMissionGain());
        base.updateFunds(ship.getMissionGain());
        ship.setActive(false);
        ship.setPendingRepair(true);
        ship.setState(StateDamaged.getInstance());
        ship.setLocation("base");
        ship.addCompletedExercises();

        System.out.println("The ship" + ship.toString() +
                " has just arrived at base | Mission successful but damaged | Income: "
                + ship.getMissionGain());

    }

    @Override
    public void sink(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        ship.setState(StateSunk.getInstance());
        ship.setActive(false);
        base.updateIncome(-ship.getMissionGain());

        System.out.println("The ship " + ship.toString() + " has sunk at "
                + ship.getLocation());
    }

    @Override
    public String toString() {
        return "in mission";
    }
}
