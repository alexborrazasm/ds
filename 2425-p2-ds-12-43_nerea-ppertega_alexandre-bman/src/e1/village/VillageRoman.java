package e1.village;

import e1.unit.roman.UnitRoman;

public class VillageRoman extends Village<UnitRoman> {

    public VillageRoman(String name, int years, int wallLevel) {
        super(name, years, wallLevel);
    }

    @Override
    public int getAtk() {
        // Better weapons +10%
        return (int)(super.getAtk() * 1.1);
    }

    @Override
    public int getDef() {
        // def + totalUnits * 2 points * level stone wall
        return (int)(super.getDef() + super.getArmySize() * 2 * getWallLevel());
    }
}
