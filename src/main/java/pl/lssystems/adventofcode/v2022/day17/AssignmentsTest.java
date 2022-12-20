package pl.lssystems.adventofcode.v2022.day17;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        CaveTetris caveTetris = new CaveTetris(Input.readAsString());
        System.out.println("Rock tower height after 2022 rocks fall: " + caveTetris.run(2022));
    }

    @Test @TaskInput
    public void assignment2() {
        CaveTetris caveTetris = new CaveTetris(Input.readAsString());
        System.out.println("Rock tower height after bilion rocks fall: " + caveTetris.run(1000000000000L));
    }

}
