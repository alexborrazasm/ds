package e1;

import e1.unit.gauls.UnitGauls;
import e1.unit.gauls.UnitGaulsDruid;
import e1.unit.gauls.UnitGaulsPhalanx;
import e1.unit.gauls.UnitGaulsTheutatesThunder;
import e1.unit.roman.UnitRomanEquitesImperatoris;
import e1.unit.roman.UnitRomanLegionary;
import e1.unit.roman.UnitRomanPraetorian;
import e1.unit.teutons.UnitTeutons;
import e1.unit.teutons.UnitTeutonsAxeman;
import e1.unit.teutons.UnitTeutonsMaceman;
import e1.unit.teutons.UnitTeutonsPaladin;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnitTest {
    // Gauls
    private static UnitGaulsDruid druid;
    private static UnitGaulsPhalanx phalanx;
    private static UnitGaulsTheutatesThunder thunder;
    // Roman
    private static UnitRomanEquitesImperatoris imperatoris;
    private static UnitRomanLegionary legionary;
    private static UnitRomanPraetorian praetorian;
    // Teutons
    private static UnitTeutonsAxeman axeman;
    private static UnitTeutonsMaceman maceman;
    private static UnitTeutonsPaladin paladin;

    @BeforeAll
    static void setUp() {
        // Gauls
        druid = new UnitGaulsDruid();
        phalanx = new UnitGaulsPhalanx();
        thunder = new UnitGaulsTheutatesThunder(false);
        // Roman
        imperatoris = new UnitRomanEquitesImperatoris();
        legionary = new UnitRomanLegionary();
        praetorian = new UnitRomanPraetorian(false);
        // Teutons
        axeman = new UnitTeutonsAxeman();
        maceman = new UnitTeutonsMaceman(false);
        paladin = new UnitTeutonsPaladin();
    }

    @Test
    void checkUnitStats() {
        // Druid 45 atk 115 def
        assertEquals(45, druid.getAtk());
        assertEquals(115, druid.getDef());
        // Phalanx 15 atk 40 def
        assertEquals(15, phalanx.getAtk());
        assertEquals(40, phalanx.getDef());
        // Theutates Thunder 100 atk 25
        assertEquals(100, thunder.getAtk());
        assertEquals(25, thunder.getDef());

        // Equites Imperatoris 120 atk 65 def
        assertEquals(120, imperatoris.getAtk());
        assertEquals(65, imperatoris.getDef());
        // Legionary 40 atk 35 def
        assertEquals(40, legionary.getAtk());
        assertEquals(35, legionary.getDef());
        // Praetorian 30 atk 65 def
        assertEquals(30, praetorian.getAtk());
        assertEquals(65, praetorian.getDef());

        // Axeman 60 atk 30 def
        assertEquals(60, axeman.getAtk());
        assertEquals(30, axeman.getDef());
        // Maceman 40 atk 20 def
        assertEquals(40, maceman.getAtk());
        assertEquals(20, maceman.getDef());
        // Paladin 55 atk 100 def
        assertEquals(55, paladin.getAtk());
        assertEquals(100, paladin.getDef());
    }

    @Test
    void heavyArmorGauls() {
        // thunder without armor
        assertEquals(100, thunder.getAtk());
        assertEquals(25, thunder.getDef());
        // thunder with armor
        thunder.setHeavyArmor();
        // atk 100 - 25% (armor)
        assertEquals(75, thunder.getAtk());
        // def 25 + 25% (armor) = 31.35 = 31
        assertEquals(31, thunder.getDef());
        // without armor, again
        thunder.unSetHeavyArmor();
        assertEquals(100, thunder.getAtk());
        assertEquals(25, thunder.getDef());
    }

    @Test
    void armorRoman() {
        // Praetorian without armor
        assertEquals(65, praetorian.getDef());
        // equip armor
        praetorian.setArmor();
        // def 65 + 10% (armor) = 65 + 6 = 71
        assertEquals(71, praetorian.getDef());
        // without armor, again
        praetorian.unSetArmor();
        assertEquals(65, praetorian.getDef());
    }

    @Test
    void upgradeMazeTeutons() {
        // Mazeman without upgrade
        assertEquals(40, maceman.getAtk());
        // Upgrade mace
        maceman.upgradeMace();
        // atk 40 + 25% = 50
        assertEquals(50, maceman.getAtk());
        // Downgrade mace
        maceman.downgradeMace();
        assertEquals(40, maceman.getAtk());
    }

    @Test
    void testToString() {
        // Druid
        String expected = "Gauls Soldiery - Druid\n";
        assertEquals(expected, druid.toString());
        // Phalanx
        expected = "Gauls Soldiery - Phalanx\n";
        assertEquals(expected, phalanx.toString());
        // Theutates Thunder
        expected = "Gauls Soldiery - Theutates Thunder\n";
        assertEquals(expected, thunder.toString());
        // Theutates Thunder with heavy armor
        expected = "Gauls Soldiery - Theutates Thunder with heavy armor\n";
        thunder.setHeavyArmor();
        assertEquals(expected, thunder.toString());
        thunder.unSetHeavyArmor();
        // Equites Imperatoris
        expected = "Roman Soldiery - Equites Imperatoris\n";
        assertEquals(expected, imperatoris.toString());
        // Legionary
        expected = "Roman Soldiery - Legionary\n";
        assertEquals(expected, legionary.toString());
        // Praetorian
        expected = "Roman Soldiery - Praetorian\n";
        assertEquals(expected, praetorian.toString());
        // Praetorian with armor
        expected = "Roman Soldiery - Praetorian with armor\n";
        praetorian.setArmor();
        assertEquals(expected, praetorian.toString());
        praetorian.unSetArmor();
        // Axeman
        expected = "Teutons Soldiery - Axeman\n";
        assertEquals(expected, axeman.toString());
        // Maceman
        expected = "Teutons Soldiery - Maceman\n";
        assertEquals(expected, maceman.toString());
        // Maceman upgrade
        expected = "Teutons Soldiery - Maceman with iron mace\n";
        maceman.upgradeMace();
        assertEquals(expected, maceman.toString());
        maceman.downgradeMace();
        // Paladin
        expected = "Teutons Soldiery - Paladin\n";
        assertEquals(expected, paladin.toString());
    }

    @Test
    void invalidArgumentUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new UnitGauls(-5,100);});

        assertThrows(IllegalArgumentException.class, () -> {
            new UnitTeutons(500,-1);});
    }
}