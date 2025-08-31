package e2;

import e2.holders.SimpleStakeHolder;
import e2.holders.complex.ComplexStakeHolder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ShareTest {
    private static Share share;
    private static CloseObserverShare closeObs1, closeObs2;
    private static InstantObserverShare instantObs1, instantObs2;
    private static ComplexStakeHolder toDetach;

    @BeforeAll
    static void setUpBeforeClass() {
        share = new SpecificShare("IBEX", 1200);
        instantObs1 = new ComplexStakeHolder();
        instantObs2 = new ComplexStakeHolder();
        closeObs1 = new SimpleStakeHolder();
        closeObs2 = new ComplexStakeHolder();
        toDetach = new ComplexStakeHolder();
    }

    @Test
    void testShare() {
        // Attach Detach CloseObserver
        share.attachCloseObserver(closeObs1);
        assertTrue(share.isCloseObserver(closeObs1));
        assertFalse(share.isCloseObserver(closeObs2));
        share.detachCloseObserver(closeObs1);
        assertFalse(share.isCloseObserver(closeObs1));

        share.attachCloseObserver(closeObs2);
        assertThrows(IllegalArgumentException.class, () -> share.attachCloseObserver(closeObs2));

        // Attach Detach InstantObserver
        share.attachInstantObserver(instantObs1);
        assertTrue(share.isInstantObserver(instantObs1));
        assertFalse(share.isInstantObserver(instantObs2));
        share.detachInstantObserver(instantObs1);
        assertFalse(share.isInstantObserver(instantObs1));

        share.attachInstantObserver(instantObs2);
        assertThrows(IllegalArgumentException.class, () -> share.attachInstantObserver(instantObs2));
    }

    @Test
    void testInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> share.attachCloseObserver(null));
        assertThrows(IllegalArgumentException.class, () -> share.detachCloseObserver(null));
        assertThrows(IllegalArgumentException.class, () -> share.attachInstantObserver(null));
        assertThrows(IllegalArgumentException.class, () -> share.detachInstantObserver(null));
        assertThrows(IllegalArgumentException.class, () -> share.detachCloseObserver(toDetach));
        assertThrows(IllegalArgumentException.class, () -> share.detachInstantObserver(toDetach));
    }
  
}