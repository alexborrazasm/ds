package e1.unit.roman;

import e1.unit.Unit;

public class UnitRoman extends Unit {

    public UnitRoman(int atk, int def) {
        super(atk, def);
    }

    @Override
    public String toString() {
        return  "Roman " + super.toString();
    }
}
