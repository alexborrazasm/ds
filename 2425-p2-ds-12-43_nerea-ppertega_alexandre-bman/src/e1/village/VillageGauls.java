package e1.village;

import e1.unit.gauls.UnitGauls;

public class VillageGauls extends Village<UnitGauls> {

    public VillageGauls(String name, int years, int wallLevel) {
        super(name, years, wallLevel);
    }

    @Override
    public int getAtk() {
        // Panoramix potions +20%
        return (int)(super.getAtk() * 1.2);
    }

    @Override
    public int getDef() {
        // def + totalUnits * 1.5 points * level wood wall
        return (int)(super.getDef() + super.getArmySize() * 1.5 * super.getWallLevel());
    }
}
