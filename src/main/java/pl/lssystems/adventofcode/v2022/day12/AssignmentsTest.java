package pl.lssystems.adventofcode.v2022.day12;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        Grid grid = new Grid(Input.readLinesAsChars());
        System.out.println(grid.findShortestPath());
    }

    @Test @TaskInput
    public void assignment2() {
        Grid grid = new Grid(Input.readLinesAsChars());
        grid.setPosition(false);
        System.out.println(grid.findShortestPath());
    }

}
