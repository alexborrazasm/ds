package e1;

public interface State {
    default void startExercise(Ship ship, NavalBase base, String localization) {
        throw new UnsupportedOperationException();
    }

    default void finishExerciseSuccessful(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void finishExerciseDamage(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void startRepair(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void expressRepair(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void finishRepair(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void cancelRepair(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void needMoreRepairs(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void sink(Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }

    default void scrap (Ship ship, NavalBase base) {
        throw new UnsupportedOperationException();
    }
}
