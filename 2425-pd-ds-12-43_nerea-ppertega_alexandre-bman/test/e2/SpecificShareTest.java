package e2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecificShareTest {
    private static SpecificShare apple;

    @BeforeAll
    static void setUpBeforeClass() {
        apple = new SpecificShare("AAPL", 1000);
    }

    @Test
    void testSpecificShare() {
        assertEquals("AAPL", apple.getCode());
        assertEquals(1000, apple.getPrice());
        assertEquals(1000, apple.getDayHigh());
        assertEquals(1000, apple.getDayLow());

        apple.updatePrice(1200);

        assertEquals(1200, apple.getPrice());
        assertEquals(1200, apple.getDayHigh());
        assertEquals(1000, apple.getDayLow());

        apple.updatePrice(900);

        assertEquals(900, apple.getPrice());
        assertEquals(1200, apple.getDayHigh());
        assertEquals(900, apple.getDayLow());

        // Close price
        apple.setClosePrice(2000);
        assertEquals(2000, apple.getClosePrice());
        assertEquals(2000, apple.getClosePrice());
        assertEquals(2000, apple.getDayHigh());
        assertEquals(900, apple.getDayLow());

        apple.setClosePrice(500);
        assertEquals(500, apple.getClosePrice());
        assertEquals(500, apple.getClosePrice());
        assertEquals(2000, apple.getDayHigh());
        assertEquals(500, apple.getDayLow());

        // Volume
        apple.setVolume(-4);
        assertEquals(-4, apple.getVolume());

        apple.setClosePrice(50);
        assertEquals(50, apple.getClosePrice());
    }

    @Test
    void testUpdatePriceInvalid() {
        assertThrows(IllegalArgumentException.class, () -> apple.updatePrice(-1));
        assertThrows(IllegalArgumentException.class, () -> apple.setClosePrice(-1));
    }

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new SpecificShare("MORETHANAAPLE", 1000));
        assertThrows(IllegalArgumentException.class, () -> new SpecificShare("AAPLE", -1));
        assertThrows(IllegalArgumentException.class, () -> new SpecificShare(null, 0));
    }

}