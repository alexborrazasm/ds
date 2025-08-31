package e1.unit.gauls;

public class UnitGaulsTheutatesThunder extends UnitGauls {
    private boolean heavyArmor;

    public UnitGaulsTheutatesThunder(boolean armor) {
        super(100, 25);
        this.heavyArmor = armor;
    }

    @Override
    public int getAtk() {
        if (heavyArmor) { // equipped armor -25% atk +25% def
            return (int)(super.getAtk() * 0.75);
        } else {
            return super.getAtk();
        }
    }

    @Override
    public int getDef() {
        if (heavyArmor) { // equipped armor -25% atk +25% def
            return (int)(super.getDef() * 1.25);
        } else {
            return super.getDef();
        }
    }

    public void setHeavyArmor() {
        this.heavyArmor = true;
    }

    public void unSetHeavyArmor() {
        this.heavyArmor = false;
    }

    @Override
    public String toString() {
        return  super.toString() + " - Theutates Thunder" +
                ((heavyArmor) ? " with heavy armor\n" : '\n');
    }
}
