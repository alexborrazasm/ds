package e1;

public abstract class Ship {
    private final String name;
    private String location;
    private final ShipType type;
    private State state;
    private int completedExercises;
    private final int missionGain, repairCost;
    private boolean active, pendingRepair;

    public Ship(String name, ShipType type, int completedExercises, int missionGain, int repairCost) {
        if (completedExercises < 0 || type == null || name == null)
            throw new IllegalArgumentException();

        this.name = name;
        this.type = type;
        this.state = StateAtBase.getInstance();
        this.completedExercises = completedExercises;
        this.location = "base";
        this.active = true;
        this.pendingRepair = false;
        this.missionGain = missionGain;
        this.repairCost = repairCost;
    }

    @Override
    public String toString() {
        return (name + " (" + type.toString() + ")");
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ShipType getType() {
        return type;
    }

    public State getState() {
        return state;
    }

    public int getCompletedExercises() {
        return completedExercises;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPendingRepair() {
        return pendingRepair;
    }

    public int getMissionGain() {
        return missionGain;
    }

    public int getRepairCost() {
        return repairCost;
    }

    // Setters
    void setLocation(String location) {
        this.location = location;
    }

    void setState(State state) {
        this.state = state;
    }

    void addCompletedExercises() {
        this.completedExercises++;
    }

    void setActive(boolean active) {
        this.active = active;
    }

    void setPendingRepair(boolean pendingRepair) {
        this.pendingRepair = pendingRepair;
    }

    // States
    public void startExercise(NavalBase base, String location) {
        state.startExercise(this, base, location);
    }

    public void finishExerciseSuccessful(NavalBase base) {
        state.finishExerciseSuccessful(this, base);
    }

    public void finishExerciseDamage(NavalBase base) {
        state.finishExerciseDamage(this, base);
    }

    public void startRepair(NavalBase base) {
        state.startRepair(this, base);
    }

    public void finishRepair(NavalBase base) {
        state.finishRepair(this, base);
    }

    public void cancelRepair(NavalBase base) {
        state.cancelRepair(this, base);
    }

    public void needMoreRepairs(NavalBase base) {
        state.needMoreRepairs(this, base);
    }

    public void expressRepair(NavalBase base) {
        state.expressRepair(this, base);
    }

    public void scrap(NavalBase base) {
        state.scrap(this, base);
    }

    public void sink(NavalBase base) {
        state.sink(this, base);
    }
}
