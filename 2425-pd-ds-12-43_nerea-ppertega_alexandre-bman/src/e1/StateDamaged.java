package e1;

import e1.ships.UltraLight;

public class StateDamaged implements State {
    private static final State state = new StateDamaged();

    private StateDamaged() {}

    public static State getInstance() {
        return state;
    }

    @Override
    public void startRepair(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }
        if (base.getFunds() < ship.getRepairCost()) {
            throw new RuntimeException("Don't have enough funds");
        }
        if (!base.canRepair()) {
            throw new RuntimeException("Cannot repair at this moment");
        }

        ship.setState(StateRepairing.getInstance());
        ship.setLocation("shipyard");
        ship.setPendingRepair(false);
        base.setRepair(false);
        base.updateExpenses(ship.getRepairCost());

        System.out.println("A repair request has been submitted for " + ship.toString()
                + " | Expected repair cost: " + ship.getRepairCost());
    }

    @Override
    public void expressRepair(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }
        if (!(ship instanceof UltraLight)) {
            throw new RuntimeException("This ship cannot do express repair");
        }
        if (!base.canRepair()) {
            throw new RuntimeException("Cannot repair at this moment");
        }

        ship.setState(StateAtBase.getInstance());
        ship.setPendingRepair(false);
        ship.setActive(true);

        System.out.println("A express repair has been done for " + ship.toString());
    }

    @Override
    public void scrap(Ship ship, NavalBase base) {
        if (!base.containsShip(ship)) {
            throw new RuntimeException("I don't manage that ship");
        }

        ship.setState(StateScrapped.getInstance());
        ship.setLocation("ship breaker");
        ship.setPendingRepair(false);

        System.out.println("A scrapping request has been submitted for " +
                ship.toString());
    }

    @Override
    public String toString() {
        return "damaged";
    }
}
