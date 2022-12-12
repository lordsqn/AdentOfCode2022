package pl.lssystems.adventofcode.v2022.day08;

public enum Direction {
    NORTH(true, false, "Up"), SOUTH(false, true, "Down"),
    EAST(false, false, "Right"), WEST(true, true, "Left");

    final boolean forward;
    final boolean opposite;
    final String altName;

    Direction(boolean forward, boolean opposite, String altName) {
        this.forward = forward;
        this.opposite = opposite;
        this.altName = altName;
    }

    public boolean isForward() {
        return forward;
    }

    public boolean isOpposite() {
        return opposite;
    }

    @Override
    public String toString() {
        return name().charAt(0) + "'" + altName;
    }

}
