package e1;

import e1.ships.UltraHeavy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavalBaseTest {
    private static NavalBase base;
    private static Ship s1;

    @BeforeEach
    void setUp() {
        base = new NavalBase(0);
        s1 = new UltraHeavy("Juan Carlos I", ShipType.BB, 13);
    }

    @Test
    void testFleet() {
        assertThrows(IllegalArgumentException.class, () -> base.addShip(null));
        assertThrows(IllegalArgumentException.class, () -> base.removeShip(null));
        assertThrows(IllegalArgumentException.class, () -> base.removeShip(s1));
        assertFalse(base.containsShip(s1));
        base.addShip(s1);
        assertTrue(base.containsShip(s1));
        assertThrows(IllegalArgumentException.class, () -> base.addShip(s1));
    }
}