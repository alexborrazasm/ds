package e4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EuroCoinTest {

    private static EuroCoin e1, e2, e3 , e4;

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
    }

    @Test
    void isPropertyEqual() {
        assertEquals(e1, e2);
        assertEquals(e1, e3);
        assertNotEquals(e1, e4);
        assertNotEquals(e2, null);
    }

    @Test
    void testHashCode() {
        assertEquals(e1.hashCode(), e2.hashCode());
        assertEquals(e1.hashCode(), e3.hashCode());
        assertNotEquals(e1.hashCode(), e4.hashCode());
    }

    @Test
    void testToString() {
        String expected = """        
        Coin: ONE_EURO( GOLD_SILVER, 100 cents)
        Country: Spain( ES )
        design: Juan Carlos I
        year: 2002
        """;

        String actual = e1.toString();
        assertEquals(expected, actual);
    }
}