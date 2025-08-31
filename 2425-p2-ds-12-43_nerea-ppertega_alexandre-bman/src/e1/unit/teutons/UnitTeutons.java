package e1.unit.teutons;

import e1.unit.Unit;

public class UnitTeutons extends Unit {

    public UnitTeutons(int atk, int def) {
        super(atk, def);
    }

    @Override
    public String toString() {
        return  "Teutons " + super.toString();
    }
}
