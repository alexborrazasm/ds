package e1.unit.roman;

public class UnitRomanPraetorian extends UnitRoman {
    private boolean armor;

    public UnitRomanPraetorian(boolean armor) {
        super(30, 65);
        this.armor = armor;
    }

    @Override
    public int getDef() {
        if (armor) {  // equipped armor + 10% def
            return (int)(super.getDef() * 1.1);
        } else {
            return super.getDef();
        }
    }

    public void setArmor() {
        this.armor = true;
    }

    public void unSetArmor() {
        this.armor = false;
    }

    @Override
    public String toString() {
        return  super.toString() + " - Praetorian" +
                ((armor) ? " with armor\n" : '\n');
    }
}
