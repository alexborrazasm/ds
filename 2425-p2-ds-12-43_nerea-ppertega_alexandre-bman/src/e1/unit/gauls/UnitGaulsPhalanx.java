package e1.unit.gauls;

public class UnitGaulsPhalanx extends UnitGauls{

    public UnitGaulsPhalanx() {
        super(15, 40);
    }

    @Override
    public String toString() {
        return super.toString() + " - Phalanx\n";
    }
}
