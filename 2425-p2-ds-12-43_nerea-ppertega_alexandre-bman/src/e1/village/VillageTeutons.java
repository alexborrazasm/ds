package e1.village;

import e1.unit.teutons.UnitTeutons;

public class VillageTeutons extends Village<UnitTeutons> {

    public VillageTeutons(String name, int years, int wallLevel) {
        super(name, years, wallLevel);
    }

    @Override
    public int getAtk() {
        // Dunked -5%
        return (int)(super.getAtk() * 0.95);
    }

    @Override
    public int getDef() {
        // def + totalUnits * 1 points * level dirt wall
        return (int)(super.getDef() + super.getArmySize() * getWallLevel());
    }
}
