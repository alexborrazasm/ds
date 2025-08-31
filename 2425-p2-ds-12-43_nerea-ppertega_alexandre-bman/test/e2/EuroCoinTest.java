package e2;

import e2.enums.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EuroCoinTest {

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

        e5 = new EuroCoin(EuroType.FIFTY_CENTS,
                EuroCountry.IT,
                "No Estatua Ecuestre de Marco Aurelio",
                2002
        );

        e6 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.HR,
                "Juan Carlos I",
                2002
        );
    }

    @Test
    void isPropertyEqual() {
        Integer notCoin = 1234;
        assertNotEquals(e2, notCoin);
        assertEquals(e1, e2);
        assertEquals(e1, e3);
        assertNotEquals(e1, e4); // type
        assertNotEquals(e2, null);
        assertNotEquals(e4, e5); // design
        assertNotEquals(e1, e6);
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

    @Test
    void testCompareTo(){
        // Natural order firstly by value (those of the higher value are first)
        assertTrue((e1.compareTo(e4) < 0));
        assertTrue((e4.compareTo(e1) > 0));
        assertTrue((e1.compareTo(e2) == 0)); // e1 = e2
        assertTrue((e4.compareTo(e5) < 0));
        assertTrue((e4.compareTo(e5) < 0));
        assertTrue((e1.compareTo(e6) > 0));
    }
}