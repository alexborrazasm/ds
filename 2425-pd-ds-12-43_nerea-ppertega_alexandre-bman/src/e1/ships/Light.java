package e1.ships;

import e1.Ship;
import e1.ShipType;

public class Light extends Ship {
    public Light(String name, ShipType type, int completedExercises) {
        super(name, type, completedExercises, 80000, 200000);

        if (!(type == ShipType.CL || type == ShipType.AV)) {
            throw new IllegalArgumentException();
        }
    }
}
