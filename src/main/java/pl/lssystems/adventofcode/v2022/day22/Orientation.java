package pl.lssystems.adventofcode.v2022.day22;

import java.util.Objects;

public class Orientation {
    private final Point position;
    private final Point direction;

    public Orientation(Point position, Point direction) {
        this.position = position;
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public Point getDirection() {
        return direction;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Orientation that = (Orientation) o;
        return position.equals(that.position) && direction.equals(that.direction);
    }

    @Override public int hashCode() {
        return Objects.hash(position, direction);
    }

    @Override public String toString() {
        return "Orientation{" +
                "position=" + position +
                ", direction=" + direction +
                '}';
    }

}
