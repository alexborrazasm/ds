package e1;

import e1.ships.Heavy;
import e1.ships.Light;
import e1.ships.UltraHeavy;
import e1.ships.UltraLight;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StateTest {

    @Test
    void testAtBase() {
        Ship s1, s2;

        s1 = new UltraLight("La Perla", ShipType.DE, 0);
        s2 = new UltraLight("La Niña", ShipType.DE, 0);

        NavalBase base = new NavalBase(100000);
        base.addShip(s1);
        base.addShip(s2);

        // At base
        assertInstanceOf(StateAtBase.class, s1.getState());
        assertInstanceOf(StateAtBase.class, s2.getState());

        // AtBase it's active true
        assertTrue(s1.isActive());
        assertFalse(s1.isPendingRepair());
        assertEquals("base", s1.getLocation());

        // States without transition
        assertThrows(RuntimeException.class, () -> base.finishExerciseSuccessful(s1));
        assertThrows(RuntimeException.class, () -> base.finishExerciseDamage(s1));
        assertThrows(RuntimeException.class, () -> base.startRepair(s1));
        assertThrows(RuntimeException.class, () -> base.expressRepair(s1));
        assertThrows(RuntimeException.class, () -> base.finishRepair(s1));
        assertThrows(RuntimeException.class, () -> base.cancelRepair(s1));
        assertThrows(RuntimeException.class, () -> base.needMoreRepairs(s1));
        assertThrows(RuntimeException.class, () -> base.sinkShip(s1));

        // States with transition in at base
        // to atExercise
        base.startExercise(s1, "English Channel");
        assertInstanceOf(StateAtExercise.class, s1.getState());
        assertTrue(s1.isActive());
        assertEquals(s1.getLocation(), "English Channel");

        // to scrapped can't transition
        base.scrapShip(s2);
        assertEquals("ship breaker", s2.getLocation());
        assertFalse(s2.isActive());
        assertInstanceOf(StateScrapped.class, s2.getState());

        base.listInactiveVessels();
        base.listActiveVessels();
    }

    @Test
    void testAtExercise() {
        Ship s3, s4, s5;

        s3 = new UltraLight("La Santa María", ShipType.DE, 0);
        s4 = new UltraLight("La Pinta", ShipType.DE, 0);
        s5 = new UltraLight("Titanic", ShipType.DE, 0);

        NavalBase base = new NavalBase(10000);
        base.addShip(s3);
        base.addShip(s4);
        base.addShip(s5);

        base.startExercise(s3, "English Channel");
        base.startExercise(s4, "English Channel");
        base.startExercise(s5, "English Channel");

        // Check s3
        assertInstanceOf(StateAtExercise.class, s3.getState());
        assertTrue(s3.isActive());
        assertEquals("English Channel", s3.getLocation());

        // Check s4 s5
        assertInstanceOf(StateAtExercise.class, s4.getState());
        assertInstanceOf(StateAtExercise.class, s5.getState());

        // States without transition
        assertThrows(RuntimeException.class, () -> base.startExercise(s3, "English Channel"));
        assertThrows(RuntimeException.class, () -> base.startRepair(s3));
        assertThrows(RuntimeException.class, () -> base.expressRepair(s3));
        assertThrows(RuntimeException.class, () -> base.finishRepair(s3));
        assertThrows(RuntimeException.class, () -> base.cancelRepair(s3));
        assertThrows(RuntimeException.class, () -> base.needMoreRepairs(s3));
        assertThrows(RuntimeException.class, () -> base.scrapShip(s3));

        // States with transition in at exercise
        // Sunk
        base.sinkShip(s3);
        assertInstanceOf(StateSunk.class, s3.getState());
        assertFalse(s3.isActive());
        assertEquals(s3.getLocation(), "English Channel");

        // finisExerciseSuccessful
        base.finishExerciseSuccessful(s4);
        assertInstanceOf(StateAtBase.class, s4.getState());
        assertTrue(s4.isActive());
        assertEquals(s4.getLocation(), "base");

        // finishExerciseDamaged
        base.finishExerciseDamage(s5);
        assertInstanceOf(StateDamaged.class, s5.getState());
        assertFalse(s5.isActive());
        assertEquals(s5.getLocation(), "base");

        base.listInactiveVessels();
        base.listActiveVessels();
    }

    @Test
    void testDamaged()
    {
        Ship s6, s7CantExpressRepair, s8, s9;

        s6 = new UltraLight("s60", ShipType.DE, 0);
        s7CantExpressRepair = new Light("s110", ShipType.AV, 0);
        s8 = new UltraLight("s81", ShipType.DD, 0);
        s9 = new UltraLight("s91", ShipType.DD, 0);

        NavalBase base = new NavalBase(0);
        base.addShip(s6);
        base.addShip(s7CantExpressRepair);
        base.addShip(s8);

        // Start Exercise
        base.startExercise(s6, "English Channel");
        base.startExercise(s7CantExpressRepair, "English Channel");
        base.startExercise(s8, "English Channel");

        // base dont manage s9
        assertThrows(RuntimeException.class, () -> base.startExercise(s9, "English Channel"));
        base.addShip(s9);

        base.startExercise(s9, "English Channel");

        // Finish damaged
        base.finishExerciseDamage(s6);
        base.finishExerciseDamage(s7CantExpressRepair);
        base.finishExerciseDamage(s8);
        base.finishExerciseDamage(s9);

        // Check state
        assertInstanceOf(StateDamaged.class, s6.getState());
        assertInstanceOf(StateDamaged.class, s7CantExpressRepair.getState());
        assertInstanceOf(StateDamaged.class, s8.getState());
        assertInstanceOf(StateDamaged.class, s9.getState());

        assertEquals(1, s8.getCompletedExercises());

        // States without transition
        assertThrows(RuntimeException.class, () -> base.startExercise(s6, "English Channel"));
        assertThrows(RuntimeException.class, () -> base.finishExerciseSuccessful(s6));
        assertThrows(RuntimeException.class, () -> base.finishExerciseDamage(s6));
        assertThrows(RuntimeException.class, () -> base.expressRepair(s7CantExpressRepair));
        assertThrows(RuntimeException.class, () -> base.finishRepair(s6));
        assertThrows(RuntimeException.class, () -> base.cancelRepair(s6));
        assertThrows(RuntimeException.class, () -> base.needMoreRepairs(s6));
        assertThrows(RuntimeException.class, () -> base.sinkShip(s6));

        // States with transition in at exercise
        // To repair
        base.updateFunds(-(s6.getMissionGain() + s7CantExpressRepair.getMissionGain() +
                s8.getMissionGain() + s9.getMissionGain()));
        assertEquals(0, base.getFunds()); // Base dont have money
        assertThrows(RuntimeException.class, () -> base.startRepair(s8));
        base.updateFunds(100000);

        base.startRepair(s8);
        assertInstanceOf(StateRepairing.class, s8.getState());
        assertFalse(s8.isActive());
        assertEquals(s8.getLocation(), "shipyard");

        // Only one ship can be repaired at a time
        assertThrows(RuntimeException.class, () -> base.startRepair(s7CantExpressRepair));
        assertInstanceOf(StateDamaged.class, s7CantExpressRepair.getState());
        assertFalse(s7CantExpressRepair.isActive());

        // Express repair if ultra light ship
        assertThrows(RuntimeException.class , () -> base.expressRepair(s6)); // only 1 ship at time
        base.finishRepair(s8);
        base.expressRepair(s6);
        assertInstanceOf(StateAtBase.class, s6.getState());
        assertTrue(s6.isActive());
        assertEquals(s6.getLocation(), "base");

        assertThrows(RuntimeException.class, () -> base.finishRepair(s7CantExpressRepair));

        // Scrap
        base.scrapShip(s7CantExpressRepair);
        assertInstanceOf(StateScrapped.class, s7CantExpressRepair.getState());
        assertFalse(s7CantExpressRepair.isActive());
        assertEquals(s7CantExpressRepair.getLocation(), "ship breaker");

        base.listInactiveVessels();
        base.listActiveVessels();
    }

    @Test
    void testRepairing() {
        Ship s1, s2;

        s1 = new UltraHeavy("SS A. B. Hammond", ShipType.BB, 200);
        s2 = new UltraHeavy("SS A. Mitchell Palmer", ShipType.BB , 100);

        NavalBase base = new NavalBase(0);

        base.addShip(s1);
        base.addShip(s2);

        assertFalse(s1.isPendingRepair());

        assertEquals(0, base.getFunds());
        assertEquals(0, base.getExpectedIncome());
        assertEquals(0, base.getExpectedExpenses());

        base.startExercise(s1, "sea");
        base.startExercise(s2, "sea");

        assertEquals(s1.getMissionGain() + s2.getMissionGain(), base.getExpectedIncome());

        assertFalse(s1.isPendingRepair());

        base.finishExerciseDamage(s1);
        base.finishExerciseDamage(s2);

        assertTrue(s1.isPendingRepair());

        assertEquals(0, base.getExpectedIncome());
        assertEquals(s1.getMissionGain() + s2.getMissionGain(), base.getFunds());

        // go to repairing
        assertThrows(RuntimeException.class, () -> base.startRepair(s1)); // not enough founds
        base.updateFunds(1000000);
        base.startRepair(s1);
        assertInstanceOf(StateRepairing.class, s1.getState());
        assertEquals(s1.getRepairCost(), base.getExpectedExpenses());
        assertFalse(s1.isActive());
        assertFalse(s1.isPendingRepair());

        // Only 1 ship
        assertThrows(RuntimeException.class, () -> base.startRepair(s2));

        // States without transition
        assertThrows(RuntimeException.class, () -> base.startExercise(s1, "Something"));
        assertThrows(RuntimeException.class, () -> base.finishExerciseSuccessful(s1));
        assertThrows(RuntimeException.class, () -> base.finishExerciseDamage(s1));
        assertThrows(RuntimeException.class, () -> base.startRepair(s1));
        assertThrows(RuntimeException.class, () -> base.expressRepair(s1));
        assertThrows(RuntimeException.class, () -> base.sinkShip(s1));
        assertThrows(RuntimeException.class, () -> base.scrapShip(s1));

        // s1 cancel repair
        base.cancelRepair(s1);
        assertInstanceOf(StateDamaged.class, s1.getState());
        assertEquals(0, base.getExpectedIncome());
        assertFalse(s1.isActive());
        assertTrue(s1.isPendingRepair());

        // go repairing again
        base.startRepair(s1);
        assertInstanceOf(StateRepairing.class, s1.getState());
        assertEquals(s1.getRepairCost(), base.getExpectedExpenses());
        assertFalse(s1.isActive());
        assertFalse(s1.isPendingRepair());

        int funds = base.getFunds();

        // need more repair
        base.needMoreRepairs(s1);
        assertInstanceOf(StateDamaged.class, s1.getState());
        assertFalse(s1.isActive());
        assertTrue(s1.isPendingRepair());
        assertEquals(funds - s1.getRepairCost(), base.getFunds());

        funds = base.getFunds();

        // go repairing again 2
        base.startRepair(s1);
        assertInstanceOf(StateRepairing.class, s1.getState());
        assertEquals(s1.getRepairCost(), base.getExpectedExpenses());
        assertFalse(s1.isActive());
        assertFalse(s1.isPendingRepair());

        // finish repair
        base.finishRepair(s1);
        assertInstanceOf(StateAtBase.class, s1.getState());
        assertEquals(funds - s1.getRepairCost(), base.getFunds());
        assertEquals(0, base.getExpectedExpenses());
        assertTrue(s1.isActive());

        base.listInactiveVessels();
        base.listActiveVessels();
    }

    @Test
    void testScrapped() {
        Ship s9 = new Heavy("s55p", ShipType.CA, 0);
        NavalBase base = new NavalBase(0);
        base.addShip(s9);

        base.scrapShip(s9);
        assertInstanceOf(StateScrapped.class, s9.getState());
        assertEquals("ship breaker", s9.getLocation());
        assertFalse(s9.isActive());

        // States without transition
        assertThrows(RuntimeException.class, () -> base.startExercise(s9, "Something"));
        assertThrows(RuntimeException.class, () -> base.finishExerciseSuccessful(s9));
        assertThrows(RuntimeException.class, () -> base.finishExerciseDamage(s9));
        assertThrows(RuntimeException.class, () -> base.startRepair(s9));
        assertThrows(RuntimeException.class, () -> base.expressRepair(s9));
        assertThrows(RuntimeException.class, () -> base.finishRepair(s9));
        assertThrows(RuntimeException.class, () -> base.cancelRepair(s9));
        assertThrows(RuntimeException.class, () -> base.needMoreRepairs(s9));
        assertThrows(RuntimeException.class, () -> base.sinkShip(s9));
        assertThrows(RuntimeException.class, () -> base.scrapShip(s9));

        base.listInactiveVessels();
        base.listActiveVessels();
    }

    @Test
    void testSunk() {
        Ship s10 = new UltraHeavy("s118k", ShipType.BB, 0);
        NavalBase base = new NavalBase(0);
        base.addShip(s10);

        base.startExercise(s10, "sea");
        base.sinkShip(s10);
        assertInstanceOf(StateSunk.class, s10.getState());
        assertEquals("sea", s10.getLocation());
        assertFalse(s10.isActive());

        // States without transition
        assertThrows(RuntimeException.class, () -> base.startExercise(s10, "Something"));
        assertThrows(RuntimeException.class, () -> base.finishExerciseSuccessful(s10));
        assertThrows(RuntimeException.class, () -> base.finishExerciseDamage(s10));
        assertThrows(RuntimeException.class, () -> base.startRepair(s10));
        assertThrows(RuntimeException.class, () -> base.expressRepair(s10));
        assertThrows(RuntimeException.class, () -> base.finishRepair(s10));
        assertThrows(RuntimeException.class, () -> base.cancelRepair(s10));
        assertThrows(RuntimeException.class, () -> base.needMoreRepairs(s10));
        assertThrows(RuntimeException.class, () -> base.sinkShip(s10));
        assertThrows(RuntimeException.class, () -> base.scrapShip(s10));

        base.listInactiveVessels();
        base.listActiveVessels();
    }

    @Test
    void testManageBase() {
        Ship ship = new UltraLight("SS Abraham Clark", ShipType.DD, 0);
        NavalBase base = new NavalBase(1000000);

        assertThrows(RuntimeException.class, () -> base.scrapShip(ship));
        // to exercise
        assertThrows(RuntimeException.class, () -> base.startExercise(ship, "sea"));
        base.addShip(ship);
        base.startExercise(ship, "sea");

        assertTrue(base.containsShip(ship));
        base.removeShip(ship);
        assertFalse(base.containsShip(ship));

        // to damaged
        assertThrows(RuntimeException.class, () -> base.sinkShip(ship));
        assertThrows(RuntimeException.class, () -> base.finishExerciseSuccessful(ship));
        assertThrows(RuntimeException.class, () -> base.finishExerciseDamage(ship));
        base.addShip(ship);
        base.finishExerciseDamage(ship);
        base.removeShip(ship);

        // to repairing
        assertThrows(RuntimeException.class, () -> base.expressRepair(ship));
        assertThrows(RuntimeException.class, () -> base.scrapShip(ship));
        assertThrows(RuntimeException.class, () -> base.startRepair(ship));
        base.addShip(ship);
        base.startRepair(ship);
        base.removeShip(ship);

        assertThrows(RuntimeException.class, () -> base.finishRepair(ship));
        assertThrows(RuntimeException.class, () -> base.cancelRepair(ship));
        assertThrows(RuntimeException.class, () -> base.needMoreRepairs(ship));
    }

    @Test
    void testToString() {
        assertEquals("at base", StateAtBase.getInstance().toString());
        assertEquals("in mission", StateAtExercise.getInstance().toString());
        assertEquals("damaged", StateDamaged.getInstance().toString());
        assertEquals("repairing", StateRepairing.getInstance().toString());
        assertEquals("decommissioned", StateScrapped.getInstance().toString());
        assertEquals("sunk", StateSunk.getInstance().toString());
    }
}