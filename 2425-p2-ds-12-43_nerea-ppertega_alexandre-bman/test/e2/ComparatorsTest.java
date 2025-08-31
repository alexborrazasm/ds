package e2;

import e2.comparators.*;
import e2.enums.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


// TODO: coins w same value, year...
public class ComparatorsTest {
    private static EuroCoin e1, e2, e3, e4;

    @BeforeAll
    static void setUp() {
        e1 = new EuroCoin(EuroType.TWO_EUROS,
                EuroCountry.AD,
                "Escudo de armas",
                2002
        );

        e2 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.SK,
                "Cruz doble",
                2010
        );

        e3 = new EuroCoin(EuroType.TWO_CENTS,
                EuroCountry.DE,
                "Rama de roble",
                2002
        );

        e4 = new EuroCoin(EuroType.FIFTY_CENTS,
                EuroCountry.DE,
                "Puerta de Brandemburgo",
                2012
        );
    }

    @Test
    void testComparatorCountries(){
        Comparator<EuroCoin> comparator = new ComparatorCountries();

        // Collection expected
        List<EuroCoin> expected = new ArrayList<>();
        // Insert coins
        expected.add(e1); // AD
        expected.add(e3); // DE
        expected.add(e4); // DE
        expected.add(e2); // SK

        // Collection to test
        EuroCoinCollection collection = new EuroCoinCollection();
        // Insert coins
        collection.addCoin(e1);
        collection.addCoin(e2);
        collection.addCoin(e3);
        collection.addCoin(e4);

        // Sort collection by country
        collection.sort(comparator);
        collection.sort(null);

        // Check
        assertEquals(expected, collection.getCollection());
    }

    @Test
    void testComparatorValue(){
        Comparator<EuroCoin> comparator = new ComparatorValue();

        // Collection expected
        List<EuroCoin> expected = new ArrayList<>();
        // Insert coins
        expected.add(e1); // 200
        expected.add(e2); // 100
        expected.add(e4); // 5
        expected.add(e3); // 2

        // Collection to test
        EuroCoinCollection collection = new EuroCoinCollection();
        // Insert coins
        collection.addCoin(e2);
        collection.addCoin(e4);
        collection.addCoin(e3);
        collection.addCoin(e1);

        // Sort collection by value
        collection.sort(comparator);
        assertEquals(expected, collection.getCollection());
    }

    @Test
    void testComparatorYear(){
        Comparator<EuroCoin> comparator = new ComparatorYear();

        // Collection expected
        List<EuroCoin> expected = new ArrayList<>();
        // Insert coins
        expected.add(e3); // 2002
        expected.add(e1); // 2002
        expected.add(e2); // 2010
        expected.add(e4); // 2012

        // Collection to test
        EuroCoinCollection collection = new EuroCoinCollection();
        // Insert coins
        collection.addCoin(e2);
        collection.addCoin(e4);
        collection.addCoin(e3);
        collection.addCoin(e1);

        // FIXME
        //assertThrows(IllegalArgumentException, collection.sort(null));

        // Sort collection by year
        collection.sort(comparator);
        assertEquals(expected, collection.getCollection());

        collection.sort(null); // order by natural order
        assertEquals(expected, collection.getCollection());

    }
}
