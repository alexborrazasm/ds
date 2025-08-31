package e1;

import e1.unit.gauls.UnitGaulsDruid;
import e1.unit.gauls.UnitGaulsPhalanx;
import e1.unit.gauls.UnitGaulsTheutatesThunder;
import e1.unit.roman.UnitRomanEquitesImperatoris;
import e1.unit.roman.UnitRomanLegionary;
import e1.unit.roman.UnitRomanPraetorian;
import e1.unit.teutons.UnitTeutonsAxeman;
import e1.unit.teutons.UnitTeutonsMaceman;
import e1.unit.teutons.UnitTeutonsPaladin;
import e1.village.VillageGauls;
import e1.village.VillageRoman;
import e1.village.VillageTeutons;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VillageTest {
    // Villages
    private static VillageGauls gaulsVillage;
    private static VillageRoman romanVillage;
    private static VillageTeutons teutonsVillage;

    // Units
    // Gauls
    private static UnitGaulsDruid druid;
    private static UnitGaulsPhalanx phalanx;
    private static UnitGaulsTheutatesThunder thunder, thunderHeavyArmor;
    // Roman
    private static UnitRomanEquitesImperatoris imperatoris;
    private static UnitRomanLegionary legionary;
    private static UnitRomanPraetorian praetorian, praetorianArmor;
    // Teutons
    private static UnitTeutonsAxeman axeman;
    private static UnitTeutonsMaceman maceman, macemanAxe;
    private static UnitTeutonsPaladin paladin;


    @BeforeAll
    static void setUp() {
        // Villages
        gaulsVillage = new VillageGauls("villageA", 500, 4);
        romanVillage = new VillageRoman("villageB", 200, 2);
        teutonsVillage = new VillageTeutons("villageC", 1000, 6);

        // Units
        // Gauls
        druid = new UnitGaulsDruid();
        phalanx = new UnitGaulsPhalanx();
        thunder = new UnitGaulsTheutatesThunder(false);
        thunderHeavyArmor = new UnitGaulsTheutatesThunder(true);
        // Roman
        imperatoris = new UnitRomanEquitesImperatoris();
        legionary = new UnitRomanLegionary();
        praetorian = new UnitRomanPraetorian(false);
        praetorianArmor = new UnitRomanPraetorian(true);
        // Teutons
        axeman = new UnitTeutonsAxeman();
        maceman = new UnitTeutonsMaceman(false);
        macemanAxe = new UnitTeutonsMaceman(true);
        paladin = new UnitTeutonsPaladin();
    }

    @Test
    void testGauls() {
        assertEquals(0, gaulsVillage.getAtk());
        assertEquals(0, gaulsVillage.getDef());

        assertTrue(gaulsVillage.addUnit(druid)); // 45 115
        assertTrue(gaulsVillage.addUnit(phalanx)); // 15 40
        assertTrue(gaulsVillage.addUnit(thunder)); // 100 25
        assertFalse(gaulsVillage.addUnit(thunder)); // yet in army
        assertTrue(gaulsVillage.addUnit(thunderHeavyArmor)); // 75 31

        // atk = (45 + 15 + 100 + 75) + 20% = 282
        assertEquals(282, gaulsVillage.getAtk());
        // def = (115 + 40 + 25 * 31) + (4 * 4 * 1.5) = 235
        assertEquals(235, gaulsVillage.getDef());

        gaulsVillage.removeUnit(druid);
        gaulsVillage.removeUnit(phalanx);
        gaulsVillage.removeUnit(thunder);
        gaulsVillage.removeUnit(thunderHeavyArmor);

        assertEquals(0, gaulsVillage.getAtk());
        assertEquals(0, gaulsVillage.getDef());
    }

    @Test
    void testRoman() {
        assertEquals(0, romanVillage.getAtk());
        assertEquals(0, romanVillage.getDef());

        assertTrue(romanVillage.addUnit(imperatoris)); // 120 65
        assertFalse(romanVillage.addUnit(imperatoris)); // yet in army
        assertTrue(romanVillage.addUnit(legionary)); // 40 35
        assertTrue(romanVillage.addUnit(praetorian)); // 30 65
        assertTrue(romanVillage.addUnit(praetorianArmor)); // 30 71

        // atk = (120 + 40 + 30 + 30) + 10% = 242
        assertEquals(242, romanVillage.getAtk());
        // def = (65 + 35 + 65 * 71) + (4 * 2 * 2) = 252
        assertEquals(252, romanVillage.getDef());
    }

    @Test
    void testTeutons() {
        assertEquals(0, teutonsVillage.getAtk());
        assertEquals(0, teutonsVillage.getDef());

        assertTrue(teutonsVillage.addUnit(axeman)); // 60 30
        assertTrue(teutonsVillage.addUnit(maceman)); // 40 20
        assertTrue(teutonsVillage.addUnit(macemanAxe)); // 50 20
        assertTrue(teutonsVillage.addUnit(paladin)); // 55 100
        assertFalse(teutonsVillage.addUnit(paladin)); // yet in array

        // atk = (60 + 40 + 50 + 55) - 5% = 194.75 = 194
        assertEquals(194, teutonsVillage.getAtk());
        // def = (30 + 20 + 20 + 100) + (4 * 6 * 1.5) = 194
        assertEquals(194, teutonsVillage.getDef());
    }

    @Test
    void invalidArgumentVillage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new VillageRoman(null, 100, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new VillageRoman("Hola", -5, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new VillageRoman("Hola", 500, -500);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new VillageRoman("", 500, 500);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            gaulsVillage.addUnit(null);
        });
    }
}