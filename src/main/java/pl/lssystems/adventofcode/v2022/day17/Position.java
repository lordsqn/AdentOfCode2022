package pl.lssystems.adventofcode.v2022.day17;

import java.util.Objects;

public class Position {

    private long x;
    private long y;

    public Position(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(long newX) {
        this.x = newX;
    }

    public void setY(long newY) {
        this.y = newY;
    }

    public Position add(long deltaX, long deltaY) {
        return new Position(x + deltaX, y + deltaY);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override public int hashCode() {
        return Objects.hash(x, y);
    }

}