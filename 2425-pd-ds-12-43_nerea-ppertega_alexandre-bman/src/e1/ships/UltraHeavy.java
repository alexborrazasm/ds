package e1.ships;

import e1.Ship;
import e1.ShipType;

public class UltraHeavy extends Ship {
    public UltraHeavy(String name, ShipType type, int completedExercises) {
        super(name, type, completedExercises, 200000, 590000);

        if (!(type == ShipType.BB)) {
            throw new IllegalArgumentException();
        }
    }
}