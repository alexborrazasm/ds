package e1.ships;

import  e1.Ship;
import e1.ShipType;

public class Heavy extends Ship {
    public Heavy(String name, ShipType type, int completedExercises) {
        super(name, type, completedExercises, 120000, 300000);

        if (!(type == ShipType.CA || type == ShipType.CV)) {
            throw new IllegalArgumentException();
        }
    }
}

