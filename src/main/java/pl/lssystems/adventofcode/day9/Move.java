package pl.lssystems.adventofcode.day9;

import java.util.*;

public class Move {
    private static final List<Move> movesRecord = new ArrayList<>();
    private final Direction direction;
    private final int toX;
    private final int toY;

    private Move(Direction direction, int toX, int toY) {
        this.direction = direction;
        this.toX = toX;
        this.toY = toY;
    }

    public int getX() {
        return toX;
    }

    public int getY() {
        return toY;
    }

    public static void addMove(Direction direction, int toX, int toY) {
        movesRecord.add(new Move(direction, toX, toY));
    }

    public static Set<Move> getUniqueMovesRecord() {
        return new HashSet<>(movesRecord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return toX == move.toX && toY == move.toY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toX, toY);
    }

}
