package pl.lssystems.adventofcode.v2022.day25;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        System.out.println("SNAFU number to supply into Bob's console: " + SNAFU.encode(Input.readLines().stream().mapToLong(SNAFU::decode).sum()));
    }

}