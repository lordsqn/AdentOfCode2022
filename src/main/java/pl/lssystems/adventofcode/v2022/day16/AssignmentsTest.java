package pl.lssystems.adventofcode.v2022.day16;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.*;
import java.util.stream.Collectors;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        Volcano volcano = new Volcano();
        Input.processLinesWithPattern("Valve ([A-Z]+) has flow rate=([0-9]+); tunnel[s]? lead[s]? to valve[s]? ([A-Z, ]+)", line ->
                volcano.addValve(new Valve(line[0], Integer.parseInt(line[1]), Arrays.stream(line[2].split(", ")).collect(Collectors.toList()))));
        volcano.calculateCosts();
        System.out.println("Maximum pressure release: " + volcano.maximumPressureRelease());
    }

    @Test @TaskInput
    public void assignment2() {
        Volcano volcano = new Volcano();
        Input.processLinesWithPattern("Valve ([A-Z]+) has flow rate=([0-9]+); tunnel[s]? lead[s]? to valve[s]? ([A-Z, ]+)", line ->
                volcano.addValve(new Valve(line[0], Integer.parseInt(line[1]), Arrays.stream(line[2].split(", ")).collect(Collectors.toList()))));
        volcano.calculateCosts();
        System.out.println("Maximum pressure release with elephant help: " + volcano.maximumPressureReleaseWithElephantHelp());
    }

}
