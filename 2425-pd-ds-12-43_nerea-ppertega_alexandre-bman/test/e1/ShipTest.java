package e1;

import e1.ships.Heavy;
import e1.ships.Light;
import e1.ships.UltraHeavy;
import e1.ships.UltraLight;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new UltraLight(null, ShipType.DE, 0));
        assertThrows(IllegalArgumentException.class, () -> new UltraLight("Name", null, 0));
        assertThrows(IllegalArgumentException.class, () -> new UltraLight("Name", ShipType.DE, -1));
        assertThrows(IllegalArgumentException.class, () -> new UltraLight("Name", ShipType.DE, -1000));

        assertThrows(IllegalArgumentException.class, () -> new UltraLight("Name", ShipType.CL, 1000));
        assertThrows(IllegalArgumentException.class, () -> new Light("Name", ShipType.DE, 1000));
        assertThrows(IllegalArgumentException.class, () -> new Heavy("Name", ShipType.CL, 1000));
        assertThrows(IllegalArgumentException.class, () -> new UltraHeavy("Name", ShipType.CL, 1000));

        Ship ship;

        ship = new UltraLight("Name", ShipType.DE, 100);
        ship = new UltraLight("Name", ShipType.DD, 100);
        ship = new Light("Name", ShipType.CL, 100);
        ship = new Light("Name", ShipType.AV, 100);
        ship = new Heavy("Name", ShipType.CA, 100);
        ship = new Heavy("Name", ShipType.CV, 100);
        ship = new UltraHeavy("Name", ShipType.BB, 100);
    }

    @Test
    void testToString() {
        Ship ship = new UltraHeavy("Juan Carlos II", ShipType.BB, 100);

        assertEquals("Juan Carlos II (BB)", ship.toString());
    }
}