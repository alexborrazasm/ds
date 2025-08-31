package e1.unit.gauls;

import e1.unit.Unit;

public class UnitGauls extends Unit {

    public UnitGauls(int atk, int def) {
        super(atk, def);
    }

    @Override
    public String toString() {
        return  "Gauls " + super.toString();
    }
}
