package pl.lssystems.adventofcode.v2022.day18;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        Boulders boulders = new Boulders();
        Input.processLinesWithDelimiterAsInt(",", line -> boulders.addDroplet(line[0], line[1], line[2]));
        System.out.println("Surface area of scanned lava droplet: " + boulders.dropletSurfaceArea());
    }

    @Test @TaskInput
    public void assignment2() {
        Boulders boulders = new Boulders();
        Input.processLinesWithDelimiterAsInt(",", line -> boulders.addDroplet(line[0], line[1], line[2]));
        System.out.println("Exterior surface area of scanned lava droplet: " + boulders.exteriorSurfaceArea());
    }

}

