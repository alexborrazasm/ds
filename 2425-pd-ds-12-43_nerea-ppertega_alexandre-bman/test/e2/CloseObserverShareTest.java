package e2;

import e2.holders.SimpleStakeHolder;
import e2.holders.complex.ComplexStakeHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class CloseObserverShareTest {
    private static SimpleStakeHolder simple;
    private static ComplexStakeHolder complex;
    private static SpecificShare apple, microsoft;

    @BeforeAll
    static void setUp() {
        simple = new SimpleStakeHolder();
        complex = new ComplexStakeHolder();

        apple = new SpecificShare("AAPL", 2000);
        microsoft = new SpecificShare("MSFT", 2000);

        apple.attachCloseObserver(simple);
        apple.attachCloseObserver(complex);

        microsoft.attachCloseObserver(simple);
        microsoft.attachCloseObserver(complex);
    }

    @Test
    void testSimple() {
        apple.updatePrice(2200);
        apple.setVolume(12);
        apple.setClosePrice(2400);

        microsoft.updatePrice(2200);
        microsoft.setClosePrice(2000);
        microsoft.updatePrice(2300);
        microsoft.setClosePrice(2500);
        microsoft.updatePrice(3000);
        microsoft.setClosePrice(2800);

        ArrayList<Integer> expectedMicrosoft = new ArrayList<>();
        expectedMicrosoft.add(2000);
        expectedMicrosoft.add(2500);
        expectedMicrosoft.add(2800);

        assertEquals(expectedMicrosoft, simple.getData("MSFT"));
        assertEquals(expectedMicrosoft, complex.getCloseData("MSFT"));

        ArrayList<Integer> expectedApple = new ArrayList<>();
        expectedApple.add(2400);

        assertEquals(expectedApple, simple.getData("AAPL"));
        assertEquals(expectedApple, complex.getCloseData("AAPL"));
    }
}