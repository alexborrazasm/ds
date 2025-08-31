package e3;

import e2.EuroCoinCollection;
import e2.enums.*;
import e2.EuroCoin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

// TODO: monedas ES salteadas
// TODO: empty collect y nullCountry

public class EuroCoinIteratorTest {
    private static EuroCoin e1, e2, e3, e4, e5, e6, e7;

    @BeforeAll
    static void setUp() {
        e1 = new EuroCoin(EuroType.TWO_EUROS,
                EuroCountry.AD,
                "Juan Carlos I",
                2002
        );

        e2 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.SK,
                "Cruz doble",
                2010
        );


        e3 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.ES,
                "Juan Carlos I",
                2002
        );

        e4 = new EuroCoin(EuroType.ONE_EURO,
                EuroCountry.AT,
                "Wolfgang Amadeus Mozart",
                2010
        );

        e5 = new EuroCoin(EuroType.FIVE_CENTS,
                EuroCountry.ES,
                "Catedral Santiago de Compostela",
                2002
        );

        e6 = new EuroCoin(EuroType.FIFTY_CENTS,
                EuroCountry.ES,
                "Juan Carlos I",
                2010
        );

        e7 = new EuroCoin(EuroType.TEN_CENTS,
                EuroCountry.CY,
                "nave de Kyrenia",
                2009
        );
    }

    @Test
    void testHasNext() {
        EuroCoinCollectionIt c1 = new EuroCoinCollectionIt();
        EuroCoinCollectionIt c2 = new EuroCoinCollectionIt(); // empty collection
        EuroCoinCollectionIt c3 = new EuroCoinCollectionIt(); // empty collection

        c1.addCoin(e1); // AD
        c1.addCoin(e2); // SK
        c1.addCoin(e3); // ES
        c1.addCoin(e4); // AT
        c1.addCoin(e5); // ES
        c1.addCoin(e6); // ES

        // countries to iterate
        c1.setCountryIterator(EuroCountry.ES);
        c2.setCountryIterator(EuroCountry.ES);
        c3.setCountryIterator(null);

        // iterators
        Iterator<EuroCoin> iteratorES = c1.iterator();
        Iterator<EuroCoin> iteratorEmptyColl = c2.iterator();
        Iterator<EuroCoin> iterEmptyCollAndNullCountry = c3.iterator();

        // empty collections
        assertFalse(iteratorEmptyColl.hasNext());
        assertFalse(iterEmptyCollAndNullCountry.hasNext());

        assertTrue(iteratorES.hasNext());
        iteratorES.next();
        iteratorES.next();
        iteratorES.next();
        assertFalse(iteratorES.hasNext());
        c1.addCoin(e4); // doesn't modify elements in collection, coin is already in c1
        assertFalse(iteratorES.hasNext());
        c1.addCoin(e7);
        assertThrows(ConcurrentModificationException.class, ()->iteratorES.hasNext());

        assertFalse(iterEmptyCollAndNullCountry.hasNext());
    }

    @Test
    void testNext() {
        EuroCoinCollectionIt c1 = new EuroCoinCollectionIt();
        EuroCoinCollectionIt c2 = new EuroCoinCollectionIt(); // empty collection
        EuroCoinCollectionIt c3 = new EuroCoinCollectionIt(); // empty collection
        EuroCoinCollectionIt c4 = new EuroCoinCollectionIt();

        c1.addCoin(e1); // AD
        c1.addCoin(e2); // SK
        c1.addCoin(e3); // ES
        c1.addCoin(e4); // AT
        c1.addCoin(e5); // ES
        c1.addCoin(e6); // ES

        c4.addCoin(e1); // AD
        c4.addCoin(e2); // SK
        c4.addCoin(e3); // ES

        // countries to iterate
        c1.setCountryIterator(EuroCountry.ES);
        c2.setCountryIterator(EuroCountry.ES);
        c3.setCountryIterator(null);
        c4.setCountryIterator(null);

        // iterators
        Iterator<EuroCoin> iteratorES = c1.iterator();
        Iterator<EuroCoin> iteratorEmptyColl = c2.iterator();
        Iterator<EuroCoin> iterEmptyCollAndNullCountry = c3.iterator();
        Iterator<EuroCoin> iteratorNullCountry = c4.iterator();

        assertThrows(NoSuchElementException.class, ()->iterEmptyCollAndNullCountry.next());

        // empty collection
        assertThrows(NoSuchElementException.class, ()-> iteratorEmptyColl.next());

        assertEquals(e3, iteratorES.next());
        assertEquals(e5, iteratorES.next());
        assertEquals(e6, iteratorES.next());
        assertThrows(NoSuchElementException.class, ()->iteratorES.next());

        c1.delCoin(e1);
        assertThrows(ConcurrentModificationException.class, ()->iteratorES.next());

        assertEquals(e1, iteratorNullCountry.next());
    }

    @Test
    void testRemove() {
        EuroCoinCollectionIt c1 = new EuroCoinCollectionIt();
        EuroCoinCollectionIt c2 = new EuroCoinCollectionIt(); // empty collection
        EuroCoinCollectionIt c3 = new EuroCoinCollectionIt();
        EuroCoinCollectionIt c4 = new EuroCoinCollectionIt();
        EuroCoinCollectionIt c5 = new EuroCoinCollectionIt();
        EuroCoinCollectionIt c6 = new EuroCoinCollectionIt();
        EuroCoinCollectionIt c7 = new EuroCoinCollectionIt();

        c1.addCoin(e1); // AD
        c1.addCoin(e2); // SK
        c1.addCoin(e3); // ES
        c1.addCoin(e4); // AT
        c1.addCoin(e5); // ES
        c1.addCoin(e6); // ES

        c3.addCoin(e1); // AD
        c3.addCoin(e2); // SK
        c3.addCoin(e4); // AT
        c3.addCoin(e5); // ES
        c3.addCoin(e6); // ES

        c4.addCoin(e1); // AD
        c4.addCoin(e2); // SK
        c4.addCoin(e4); // AT
        c4.addCoin(e5); // ES

        c5.addCoin(e1); // AD
        c5.addCoin(e2); // SK
        c5.addCoin(e3); // ES

        c6.addCoin(e2); // SK
        c6.addCoin(e3); // ES

        c7.addCoin(e3); //ES

        // countries to iterate
        c1.setCountryIterator(EuroCountry.ES);
        c2.setCountryIterator(EuroCountry.ES);
        c3.setCountryIterator(EuroCountry.GR);
        c5.setCountryIterator(null);
        c7.setCountryIterator(EuroCountry.ES);

        // iterators
        Iterator<EuroCoin> iteratorES = c1.iterator();
        Iterator<EuroCoin> iteratorEmptyColl = c2.iterator();
        Iterator<EuroCoin> iteratorGR = c3.iterator();
        Iterator<EuroCoin> iteratorNull = c5.iterator();
        Iterator<EuroCoin> iteratorOneElement = c7.iterator();

        // no coins to iterate
        assertThrows(IllegalStateException.class, ()->iteratorGR.remove());
        // empty collection
        assertThrows(NoSuchElementException.class, ()->iteratorEmptyColl.next());
        assertThrows(IllegalStateException.class, ()->iteratorEmptyColl.remove());

        assertThrows(IllegalStateException.class, ()->iteratorES.remove());
        assertThrows(IllegalStateException.class, ()->iteratorES.remove());
        iteratorES.next();
        iteratorES.remove();
        assertEquals(c3, c1);
        iteratorES.next();
        iteratorES.next();
        iteratorES.remove();
        assertEquals(c4, c1);

        c1.delCoin(e1);
        assertThrows(ConcurrentModificationException.class, ()->iteratorES.remove());

        iteratorNull.next();
        iteratorNull.remove();
        assertEquals(c6, c5);

        iteratorOneElement.next();
        iteratorOneElement.remove();
    }
}
