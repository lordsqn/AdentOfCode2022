package pl.lssystems.adventofcode.v2022.day22;

import java.util.Objects;

public class Instruction {
    private final int steps;
    private final Turn turn;

    public Instruction(int steps, Turn turn) {
        this.steps = steps;
        this.turn = turn;
    }

    public int getSteps() {
        return steps;
    }

    public Turn getTurn() {
        return turn;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Instruction that = (Instruction) o;
        return steps == that.steps && turn == that.turn;
    }

    @Override public int hashCode() {
        return Objects.hash(steps, turn);
    }

    @Override public String toString() {
        return "Instruction{" +
                "steps=" + steps +
                ", turn=" + turn +
                '}';
    }

}
