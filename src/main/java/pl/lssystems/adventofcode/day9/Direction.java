package pl.lssystems.adventofcode.day9;

public enum Direction {
    UP("U"), DOWN("D"), LEFT("L"), RIGHT("R"), NULL("N");

    final String name;

    Direction(String id) {
        this.name = id;
    }

    static Direction getDirection(String id) {
        for (Direction direction : Direction.values())
            if (direction.name.equals(id))
                return direction;

        return Direction.NULL;
    }

}
