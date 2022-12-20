package pl.lssystems.adventofcode.v2022.day17;

public enum Direction {
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    final int dx;
    final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Position translate(Position location) {
        return new Position(location.getX() + this.dx, location.getY() + this.dy);
    }

}