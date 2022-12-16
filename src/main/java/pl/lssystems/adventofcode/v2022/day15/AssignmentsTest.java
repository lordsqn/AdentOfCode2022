package pl.lssystems.adventofcode.v2022.day15;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        List<Sensor> sensors = new ArrayList<>();
        Input.processLinesWithPatternAsInt("Sensor at x=(-?[0-9]+), y=(-?[0-9-]+): closest beacon is at x=(-?[0-9]+), y=(-?[0-9]+)", matches -> {
            Point position = new Point(matches[0], matches[1]);
            Point beacon = new Point(matches[2], matches[3]);
            sensors.add(new Sensor(position, beacon, position.distanceTo(beacon)));
        });
        Zone zone = new Zone(sensors);
        //System.out.println(beaconExclusionZone.countPositionWithoutBeacons(10));
        System.out.println(zone.countPositionWithoutBeacons(2000000));
    }

    @Test @TaskInput
    public void assignment2() {
        List<Sensor> sensors = new ArrayList<>();
        Input.processLinesWithPatternAsInt("Sensor at x=(-?[0-9]+), y=(-?[0-9-]+): closest beacon is at x=(-?[0-9]+), y=(-?[0-9]+)", matches -> {
            Point position = new Point(matches[0], matches[1]);
            Point beacon = new Point(matches[2], matches[3]);
            sensors.add(new Sensor(position, beacon, position.distanceTo(beacon)));
        });
        Zone zone = new Zone(sensors);
        //System.out.println("Tuning frequency: " + beaconExclusionZone.findDistressBeacon(20));
        System.out.println("Tuning frequency: " + zone.findDistressBeacon(4000000));
    }

}
