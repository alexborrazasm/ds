package e2;

import e2.holders.SimpleStakeHolder;
import e2.holders.complex.ComplexStakeHolder;
import e2.holders.complex.ShareData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class InstantObserverShareTest {
    private static ComplexStakeHolder complex;
    private static SpecificShare apple, microsoft;

    @BeforeAll
    static void setUp() {
        complex = new ComplexStakeHolder();

        apple = new SpecificShare("AAPL", 2000);
        microsoft = new SpecificShare("MSFT", 2000);

        apple.attachInstantObserver(complex);
        microsoft.attachInstantObserver(complex);
    }

    @Test
    void testInstant() {
        //Apple
        apple.setVolume(10);
        apple.updatePrice(3000);
        apple.setClosePrice(3200);
        apple.updatePrice(1200);

        ArrayList<ShareData> expected = new ArrayList<>();

        expected.add(new ShareData(2000, 2000, 10));
        expected.add(new ShareData(3000, 2000, 10));
        expected.add(new ShareData(3200, 2000, 10));
        expected.add(new ShareData(3200, 1200, 10));

        assertEquals(expected, complex.getDataInstant("AAPL"));

        // Microsoft
        microsoft.setVolume(15);
        microsoft.updatePrice(3000);

        expected = new ArrayList<>();

        expected.add(new ShareData(2000, 2000, 15));
        expected.add(new ShareData(3000, 2000, 15));

        assertEquals(expected, complex.getDataInstant("MSFT"));
    }
}