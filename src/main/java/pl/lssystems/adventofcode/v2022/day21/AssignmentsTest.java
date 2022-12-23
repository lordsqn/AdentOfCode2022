package pl.lssystems.adventofcode.v2022.day21;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {


    @Test @TaskInput
    public void assignment1() {
        MonkeyMatch monkeyMatch = new MonkeyMatch(Input.readLinesWithDelimiter(": "));
        System.out.println("Monkey root yells number: " + monkeyMatch.monkeyRootYells());
    }

    @Test @TaskInput
    public void assignment2() {
        MonkeyMatch monkeyMatch = new MonkeyMatch(Input.readLinesWithDelimiter(": "));
        System.out.println("Number that need to be yell to pass root's equality test: " + monkeyMatch.rootsEqualityTest());
    }

}