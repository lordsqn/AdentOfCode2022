package pl.lssystems.adventofcode.v2022.day15;

import java.util.List;
import java.util.stream.IntStream;

public class Zone {

    private final List<Sensor> sensors;

    public Zone(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public int countPositionWithoutBeacons(int y) {
        int minX = getSensors().stream().mapToInt(sensor -> sensor.getPosition().getX() - sensor.getClosestBeaconDistance()).min().orElse(0);
        int maxX = getSensors().stream().mapToInt(sensor -> sensor.getPosition().getX() + sensor.getClosestBeaconDistance()).max().orElse(0);

        return (int) (IntStream.range(minX, maxX + 1).filter(x -> sensors.stream().anyMatch(sensor ->
                sensor.ruleOutBeacon(new Point(x, y)))).count() - sensors.stream().map(Sensor::getBeacon).filter(beacon -> beacon.getY() == y).distinct().count());
    }

    public long findDistressBeacon(int max) {
        Point distressBeacon = sensors.stream()
                .flatMap(sensor -> sensor.surroundingPoints().stream())
                .filter(point -> point.getY() >= 0 && point.getX() >= 0 && point.getY() <= max && point.getX() <= max)
                .filter(point -> sensors.stream().noneMatch(sensor -> sensor.ruleOutBeacon(point)))
                .findAny()
                .orElseThrow(RuntimeException::new);

        return distressBeacon.getX() * 4000000L + distressBeacon.getY();
    }

}