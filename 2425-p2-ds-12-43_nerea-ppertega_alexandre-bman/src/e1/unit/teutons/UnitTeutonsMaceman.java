package e1.unit.teutons;

public class UnitTeutonsMaceman extends UnitTeutons {
    private boolean steelMace;

    public UnitTeutonsMaceman(boolean steelMace) {
        super(40, 20);
        this.steelMace = steelMace;
    }

    @Override
    public int getAtk() {
        if (steelMace) { // steel mace upgrade +25% atk
            return (int)(super.getAtk() * 1.25);
        } else {
            return super.getAtk();
        }
    }

    public void upgradeMace() {
        this.steelMace = true;
    }

    public void downgradeMace() {
        this.steelMace = false;
    }

    @Override
    public String toString() {
        return  super.toString() + " - Maceman" +
                ((steelMace) ? " with iron mace\n" : '\n');
    }
}
