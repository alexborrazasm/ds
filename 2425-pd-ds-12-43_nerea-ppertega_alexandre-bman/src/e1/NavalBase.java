package e1;

import java.util.ArrayList;
import java.util.List;

public class NavalBase {
    private final List<Ship> fleet;
    private int funds, expectedIncome, expectedExpenses;
    private boolean repair;

    public NavalBase(int funds) {
        this.funds = funds;
        this.fleet = new ArrayList<>();
        this.repair = true;
        this.expectedIncome = 0;
        this.expectedExpenses = 0;
    }

    // Fleet
    public void addShip(Ship ship) {
        if (ship == null) {
            throw new IllegalArgumentException();
        }
        if (fleet.contains(ship)) {
            throw new IllegalArgumentException();
        }
        fleet.add(ship);
    }

    public void removeShip(Ship ship) {
        if (ship == null) {
            throw new IllegalArgumentException();
        }
        if (!fleet.contains(ship)) {
            throw new IllegalArgumentException();
        }
        fleet.remove(ship);
    }

    public boolean containsShip(Ship ship) {
        return fleet.contains(ship);
    }

    // Getters
    public int getFunds() {
        return funds;
    }

    public int getExpectedExpenses() {
        return expectedExpenses;
    }

    public int getExpectedIncome() {
        return expectedIncome;
    }

    public boolean canRepair() {
        return repair;
    }

    // Setters
    public void updateFunds(int amount) {
        funds += amount;
    }

    public void updateExpenses(int amount) {
        expectedExpenses += amount;
    }

    public void updateIncome(int amount) {
        expectedIncome += amount;
    }

    void setRepair(boolean repair) {
        this.repair = repair;
    }

    // states
    public void startExercise(Ship ship, String localization) {
        ship.startExercise(this, localization);
    }

    public void finishExerciseSuccessful(Ship ship) {
        ship.finishExerciseSuccessful(this);
    }

    public void finishExerciseDamage(Ship ship) {
        ship.finishExerciseDamage(this);
    }

    public void startRepair(Ship ship) {
        ship.startRepair(this);
    }

    public void expressRepair(Ship ship) {
        ship.expressRepair(this);
    }

    public void finishRepair(Ship ship) {
        ship.finishRepair(this);
    }

    public void cancelRepair(Ship ship) {
        ship.cancelRepair(this);
    }

    public void needMoreRepairs(Ship ship) {
        ship.needMoreRepairs(this);
    }

    public void scrapShip(Ship ship) {
        ship.scrap(this);
    }

    public void sinkShip(Ship ship) {
        ship.sink(this);
    }

    // Other methods
    public void listActiveVessels() {
        System.out.println("ACTIVE VESSELS\n------------------------------------");
        for (Ship ship : fleet) {
            if (ship.isActive()) {
                System.out.println("Name: " + ship.toString() + " | State: " + ship.getState().toString()
                        + " | Missions: " + ship.getCompletedExercises());
            }
        }
        System.out.println();
    }

    public void listInactiveVessels() {
        System.out.println("INACTIVE VESSELS\n------------------------------------");

        for (Ship ship : fleet) {
            if (!ship.isActive()) {
                System.out.println("Name: " + ship.toString() + " | Reasons: " + ship.getState().toString()
                        + " | Missions: " + ship.getCompletedExercises());
            }
        }
        System.out.println();
    }
}
