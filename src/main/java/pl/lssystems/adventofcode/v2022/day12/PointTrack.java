package pl.lssystems.adventofcode.v2022.day12;

import java.awt.*;
import java.util.Objects;

public class PointTrack {
    private final Point position;
    private final int steps;

    public PointTrack(Point position, int steps) {
        this.position = position;
        this.steps = steps;
    }

    public Point getPosition() {
        return position;
    }

    public int getSteps() {
        return steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointTrack pathState = (PointTrack) o;
        return steps == pathState.steps && position.equals(pathState.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, steps);
    }

}
