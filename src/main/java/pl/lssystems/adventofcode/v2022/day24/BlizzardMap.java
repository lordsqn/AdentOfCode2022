package pl.lssystems.adventofcode.v2022.day24;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BlizzardMap {
    private final List<Point> leftBlizzards;
    private final List<Point> rightBlizzards;
    private final List<Point> upBlizzards;
    private final List<Point> downBlizzards;
    private final int xMax;
    private final int yMax;

    public BlizzardMap(List<Point> leftBlizzards, List<Point> rightBlizzards, List<Point> upBlizzards, List<Point> downBlizzards, int xMax, int yMax) {
        this.leftBlizzards = leftBlizzards;
        this.rightBlizzards = rightBlizzards;
        this.upBlizzards = upBlizzards;
        this.downBlizzards = downBlizzards;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public List<Point> getLeftBlizzards() {
        return leftBlizzards;
    }

    public List<Point> getRightBlizzards() {
        return rightBlizzards;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public List<Point> getUpBlizzards() {
        return upBlizzards;
    }

    public List<Point> getDownBlizzards() {
        return downBlizzards;
    }

    public Set<Point> getAllBlizzards() {
        Set<Point> blizzards = new HashSet<>();
        blizzards.addAll(leftBlizzards);
        blizzards.addAll(rightBlizzards);
        blizzards.addAll(upBlizzards);
        blizzards.addAll(downBlizzards);
        return blizzards;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BlizzardMap blizzardMap = (BlizzardMap) o;
        return xMax == blizzardMap.xMax && yMax == blizzardMap.yMax && leftBlizzards.equals(blizzardMap.leftBlizzards) && rightBlizzards.equals(
                blizzardMap.rightBlizzards) && upBlizzards.equals(blizzardMap.upBlizzards) && downBlizzards.equals(
                blizzardMap.downBlizzards);
    }

    @Override public int hashCode() {
        return Objects.hash(leftBlizzards, rightBlizzards, upBlizzards, downBlizzards, xMax, yMax);
    }

    @Override public String toString() {
        return "BlizzardMap{" +
                "leftBlizzards=" + leftBlizzards +
                ", rightBlizzards=" + rightBlizzards +
                ", upBlizzards=" + upBlizzards +
                ", downBlizzards=" + downBlizzards +
                ", xMax=" + xMax +
                ", yMax=" + yMax +
                '}';
    }

}
