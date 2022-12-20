package pl.lssystems.adventofcode.v2022.day18;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;
    private final int z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Position[] createNeighbours() {
        Position[] positions = new Position[6];
        positions[0] = new Position(this.x, y, z + 1); //top
        positions[1] = new Position(this.x, y, z - 1); // down
        positions[2] = new Position(x, y - 1, z); //left
        positions[3] = new Position(x, y + 1, z); // right
        positions[4] = new Position(x + 1, y, z); //front
        positions[5] = new Position(x - 1, y, z); //center
        return positions;
    }

    public int calculateDistance(Position o) {
        return Math.abs(o.x - x) + Math.abs(o.y - y) + Math.abs(o.z - z);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return x == position.x && y == position.y && z == position.z;
    }

    @Override public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override public String toString() {
        return "Position{x=" + x + ", y=" + y + ", z=" + z + '}';
    }

}