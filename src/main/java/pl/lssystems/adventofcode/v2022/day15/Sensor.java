package pl.lssystems.adventofcode.v2022.day15;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public class Sensor {
    private final Point position;
    private final Point beacon;
    private final int closestBeaconDistance;

    public Sensor(Point position, Point beacon, int closestBeaconDistance) {
        this.position = position;
        this.beacon = beacon;
        this.closestBeaconDistance = closestBeaconDistance;
    }

    public Point getPosition() {
        return position;
    }

    public Point getBeacon() {
        return beacon;
    }

    public int getClosestBeaconDistance() {
        return closestBeaconDistance;
    }

    public Set<Point> surroundingPoints() {
        int distance = closestBeaconDistance + 1;
        return IntStream.range(getPosition().getY() - distance, position.getY() + distance + 1)
                .boxed()
                .flatMap(y -> {
                    int xDistance = distance - Math.abs(getPosition().getY() - y);
                    return Stream.of(new Point(getPosition().getX() - xDistance, y), new Point(getPosition().getX() + xDistance, y));
                })
                .collect(toSet());
    }

    public boolean ruleOutBeacon(Point point) {
        return this.position.distanceTo(point) <= closestBeaconDistance;
    }

}
