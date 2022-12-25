package pl.lssystems.adventofcode.v2022.day24;

import java.util.Objects;

public class Position {
    private final Point point;
    private final int stage;

    public Position(Point point, int stage) {
        this.point = point;
        this.stage = stage;
    }

    public Point getPoint() {
        return point;
    }

    public int getStage() {
        return stage;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return stage == position.stage && point.equals(position.point);
    }

    @Override public int hashCode() {
        return Objects.hash(point, stage);
    }

    @Override public String toString() {
        return "Position{" +
                "point=" + point +
                ", stage=" + stage +
                '}';
    }

}
