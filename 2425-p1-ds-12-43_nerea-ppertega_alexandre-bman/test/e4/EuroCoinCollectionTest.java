package e4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EuroCoinCollectionTest {

    private static EuroCoin e1, e2, e3 , e4, e5, e6;

    @BeforeAll
    static void setUp() {

        e1 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.ES,
                "Juan Carlos I",
                2002
        );

        e2 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.ES,
                "Juan Carlos I",
                2002
        ); // Equals to e1

        e3 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.ES,
                "Juan Carlos I",
                2012
        ); // Change year its equals to e2 and e1

        e4 = new EuroCoin(EuroType.FIFTY_CENTS,
                EuroCountry.IT,
                "Estatua Ecuestre de Marco Aurelio",
                2002
        );

        e5 = new EuroCoin(EuroType.TWO_EUROS,
                EuroCountry.IT,
                "Plaza San Marco",
                2017
        );

        e6 = new EuroCoin(EuroType.FIVE_CENTS,
                EuroCountry.ES,
                "Catedral de Santiago",
                2004
        );
    }

    @Test
    void insertDeleteCoins() {
        EuroCoinCollection c1 = new EuroCoinCollection();

        assertTrue(c1.addCoin(e1));
        assertFalse(c1.addCoin(e2)); // e1 equals e2
        assertFalse(c1.addCoin(e3)); // equals to e1 e2
        assertTrue(c1.addCoin(e4));
        assertThrows(IllegalArgumentException.class, () -> c1.addCoin(null));

        assertEquals(2, c1.countCoins());

        assertTrue(c1.delCoin(e1));
        assertFalse(c1.delCoin(e1));
        assertFalse(c1.delCoin(e2));
        assertFalse(c1.delCoin(e3));
        assertTrue(c1.delCoin(e4));

        assertEquals(0, c1.countCoins());
    }

    @Test
    void totalValueTest() {
        EuroCoinCollection c2 = new EuroCoinCollection();

        assertEquals(0, c2.totalValue());

        c2.addCoin(e1); // 100 cents

        assertEquals(100, c2.totalValue());

        assertTrue(c2.addCoin(e4)); // + 50
        assertTrue(c2.addCoin(e5)); // + 200
        assertTrue(c2.addCoin(e6)); // + 5

        assertEquals(355, c2.totalValue()); // 100 + 50 + 200 + 5
    }
}

