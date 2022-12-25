package pl.lssystems.adventofcode.v2022.day24;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        BlizzardBasin blizzardBasin = new BlizzardBasin(Input.readLines());
        System.out.println("Lowest number of minutes required to avoid the blizzards and reach the goal: " + blizzardBasin.run(0));
    }

    @Test @TaskInput
    public void assignment2() {
        BlizzardBasin blizzardBasin = new BlizzardBasin(Input.readLines());
        System.out.println("Lowest number of minutes required to reach the goal, when going back to the start, then reach the goal again: " + blizzardBasin.run(2));
    }

}