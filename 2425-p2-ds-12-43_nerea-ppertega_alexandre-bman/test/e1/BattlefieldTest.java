package e1;

import e1.unit.gauls.UnitGaulsDruid;
import e1.unit.gauls.UnitGaulsPhalanx;
import e1.unit.gauls.UnitGaulsTheutatesThunder;
import e1.unit.roman.UnitRoman;
import e1.unit.roman.UnitRomanEquitesImperatoris;
import e1.unit.roman.UnitRomanLegionary;
import e1.unit.roman.UnitRomanPraetorian;
import e1.village.VillageGauls;
import e1.village.VillageRoman;
import e1.village.VillageTeutons;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class BattlefieldTest {
    // Villages
    private static VillageGauls gaulsVillage;
    private static VillageRoman romanVillage;
    private static VillageTeutons teutonsVillage0, teutonsVillage1;

    // Units
    // Gauls
    private static UnitGaulsDruid druid;
    private static UnitGaulsPhalanx phalanx;
    private static UnitGaulsTheutatesThunder thunder, thunderHeavyArmor;
    // Roman
    private static UnitRomanEquitesImperatoris imperatoris;
    private static UnitRomanLegionary legionary;
    private static UnitRomanPraetorian praetorian, praetorianArmor;

    // Battlefield
    private static Battlefield battlefield;

    @BeforeAll
    static void setUp() {
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

        // Village A Gauls 282 235
        gaulsVillage = new VillageGauls("villageA", 500, 4);
        gaulsVillage.addUnit(druid);
        gaulsVillage.addUnit(phalanx);
        gaulsVillage.addUnit(thunder);
        gaulsVillage.addUnit(thunderHeavyArmor);
        // Village B Roman 252 242
        romanVillage = new VillageRoman("villageB", 200, 2);
        romanVillage.addUnit(imperatoris);
        romanVillage.addUnit(legionary);
        romanVillage.addUnit(praetorian);
        romanVillage.addUnit(praetorianArmor);
        // Village same atk and def
        teutonsVillage0 = new VillageTeutons("villageC", 5, 2);
        teutonsVillage1 = new VillageTeutons("villageD", 5, 2);

        battlefield = new Battlefield();
    }

    @Test
    void invalidArgumentTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            battlefield.battle(gaulsVillage,null);});

        assertThrows(IllegalArgumentException.class, () -> {
            battlefield.battle(null, gaulsVillage);});

        assertThrows(IllegalArgumentException.class, () -> {
            battlefield.battle(null,null);});

        assertThrows(IllegalArgumentException.class, () -> {
           battlefield.battle(gaulsVillage,gaulsVillage);
        });
    }

    @Test
    void battle() {
        List<String> expected1 = List.of(
                "### Starts the battle! --> villageB Attacks villageA! ###\n",
                "villageB have the following soldiery:\n",
                "Roman Soldiery - Equites Imperatoris\n",
                "Roman Soldiery - Legionary\n",
                "Roman Soldiery - Praetorian\n",
                "Roman Soldiery - Praetorian with armor\n",
                "Total villageB attack power: 242\n",
                "\n",
                "villageA have the following soldiery:\n",
                "Gauls Soldiery - Druid\n",
                "Gauls Soldiery - Phalanx\n",
                "Gauls Soldiery - Theutates Thunder\n",
                "Gauls Soldiery - Theutates Thunder with heavy armor\n",
                "Total villageA defense power: 235\n",
                "\n",
                "villageB with an age of 200 years WINS!\n"
        );

        List<String> expected2 = List.of(
                "### Starts the battle! --> villageA Attacks villageB! ###\n",
                "villageA have the following soldiery:\n",
                "Gauls Soldiery - Druid\n",
                "Gauls Soldiery - Phalanx\n",
                "Gauls Soldiery - Theutates Thunder\n",
                "Gauls Soldiery - Theutates Thunder with heavy armor\n",
                "Total villageA attack power: 282\n",
                "\n",
                "villageB have the following soldiery:\n",
                "Roman Soldiery - Equites Imperatoris\n",
                "Roman Soldiery - Legionary\n",
                "Roman Soldiery - Praetorian\n",
                "Roman Soldiery - Praetorian with armor\n",
                "Total villageB defense power: 252\n",
                "\n",
                "villageA with an age of 500 years WINS!\n"
        );

        List<String> expected3 = List.of(
                "### Starts the battle! --> villageC Attacks villageD! ###\n",
                "villageC have the following soldiery:\n",
                "Total villageC attack power: 0\n",
                "\n",
                "villageD have the following soldiery:\n",
                "Total villageD defense power: 0\n",
                "\n",
                "It's a DRAW!\n"
        );


        List<String> expected4 = List.of(
                "### Starts the battle! --> villageA Attacks villageB! ###\n",
                "villageA have the following soldiery:\n",
                "Gauls Soldiery - Druid\n",
                "Gauls Soldiery - Phalanx\n",
                "Gauls Soldiery - Theutates Thunder\n",
                "Gauls Soldiery - Theutates Thunder with heavy armor\n",
                "Total villageA attack power: 282\n",
                "\n",
                "villageB have the following soldiery:\n",
                "Roman Soldiery - Equites Imperatoris\n",
                "Roman Soldiery - Legionary\n",
                "Roman Soldiery - Praetorian\n",
                "Roman Soldiery - Praetorian with armor\n",
                "Roman Soldiery - Praetorian with armor\n",
                "Total villageB defense power: 327\n",
                "\n",
                "villageB with an age of 200 years WINS!\n"
        );

        assertEquals(expected1, battlefield.battle(romanVillage,
                gaulsVillage));
        assertEquals(expected2, battlefield.battle(gaulsVillage,
                romanVillage));
        assertEquals(expected3, battlefield.battle(teutonsVillage0,
                teutonsVillage1));

        UnitRoman unit = new UnitRomanPraetorian(true);
        romanVillage.addUnit(unit);
        assertEquals(expected4, battlefield.battle(gaulsVillage,
                romanVillage));
    }
}