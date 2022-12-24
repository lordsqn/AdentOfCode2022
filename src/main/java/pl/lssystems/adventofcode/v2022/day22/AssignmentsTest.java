package pl.lssystems.adventofcode.v2022.day22;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        MonkeyMap monkeyMap = new MonkeyMap(Input.readLines());
        monkeyMap.calculatePosition();
        System.out.println("Final password: " + monkeyMap.getPassword());
    }

    @Test @TaskInput
    public void assignment2() {
        MonkeyMap monkeyMap = new MonkeyMap(Input.readLines());
        monkeyMap.calculatePositionAlt();
        System.out.println("Final password: " + monkeyMap.getPassword());
    }

}