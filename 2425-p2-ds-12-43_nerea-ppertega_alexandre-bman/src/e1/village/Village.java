package e1.village;

import e1.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public abstract class Village<U extends Unit> {
    private final String name;
    private int years;
    private int wallLevel;
    private final List<U> army;

    public Village(String name, int years, int wallLevel) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (years < 0) {
            throw new IllegalArgumentException();
        }
        if (wallLevel < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.years = years;
        this.wallLevel = wallLevel;
        this.army = new ArrayList<>();
    }

    public boolean addUnit(U unit) {
        if (unit == null) {
            throw new IllegalArgumentException();
        }
        if (!army.contains(unit)) {
            army.add(unit);
            return true;
        }
        return false;
    }

    public void removeUnit(U unit) {
        army.remove(unit);
    }

    public int getArmySize() {
        return army.size();
    }

    public String getName() {
        return name;
    }

    public int getYears() {
        return years;
    }

    public int getWallLevel() {
        return wallLevel;
    }

    public int getAtk() {
        int atk = 0;
        for (U unit : army) {
            atk = atk + unit.getAtk();
        }
        return atk;
    }

    public int getDef() {
        int def = 0;
        for (U unit : army) {
            def = def + unit.getDef();
        }
        return def;
    }

    public List<String> getArmyStringList() {
        List<String> result = new ArrayList<>();
        for (U unit : this.army) {
            result.add(unit.toString());
        }
        return result;
    }
}