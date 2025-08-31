package e1.unit;

public abstract class Unit {
    private int atk;
    private int def;

    public Unit(int atk, int def) {
        if (atk <= 0) {
            throw new IllegalArgumentException();
        }
        if (def <= 0) {
            throw new IllegalArgumentException();
        }
        this.atk = atk;
        this.def = def;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {return def; }

    @Override
    public String toString() {
        return  "Soldiery";
    }
}
