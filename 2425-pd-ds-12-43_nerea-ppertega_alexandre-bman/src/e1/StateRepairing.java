package e1;

public class StateRepairing implements State {
    private static final State state = new StateRepairing();

    private StateRepairing() {}

    public static State getInstance() {
        return state;
    }

    @Override
    public void finishRepair(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        ship.setLocation("base");
        ship.setState(StateAtBase.getInstance());
        ship.setActive(true);
        base.updateExpenses(-ship.getRepairCost());
        base.updateFunds(-ship.getRepairCost());
        base.setRepair(true);

        System.out.println("A repair for " + ship.toString() +
                " has been finish | Repair cost: " + ship.getRepairCost());
    }

    @Override
    public void cancelRepair(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        ship.setLocation("base");
        ship.setState(StateDamaged.getInstance());
        ship.setPendingRepair(true);
        base.setRepair(true);
        base.updateExpenses(-ship.getRepairCost());

        System.out.println("A repair request for " + ship.toString() +
                " has been cancel");
    }

    @Override
    public void needMoreRepairs(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        ship.setLocation("base");
        ship.setState(StateDamaged.getInstance());
        ship.setPendingRepair(true);
        base.updateExpenses(-ship.getRepairCost());
        base.updateFunds(-ship.getRepairCost());
        base.setRepair(true);

        System.out.println("A repair for " + ship.toString() +
                " has been finish but need more repairs | Repair cost: " +
                ship.getRepairCost());
    }

    @Override
    public String toString() {
        return "repairing";
    }
}
