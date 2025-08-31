package e1.ships;

import e1.NavalBase;
import e1.Ship;
import e1.ShipType;

public class UltraLight extends Ship {
    public UltraLight(String name, ShipType type, int completedExercises) {
        super(name, type, completedExercises, 50000, 100000);

        if (!(type == ShipType.DE || type == ShipType.DD)) {
            throw new IllegalArgumentException();
        }
    }

    public void expressRepair(NavalBase base) {
        super.getState().expressRepair(this, base);
    }
}