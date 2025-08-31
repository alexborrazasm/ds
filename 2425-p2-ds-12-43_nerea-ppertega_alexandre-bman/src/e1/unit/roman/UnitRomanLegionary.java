package e1.unit.roman;

public class UnitRomanLegionary extends UnitRoman {

    public UnitRomanLegionary() {
        super(40, 35);
    }

    @Override
    public String toString() {
        return super.toString() + " - Legionary\n";
    }
}
