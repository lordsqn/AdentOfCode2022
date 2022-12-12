package pl.lssystems.adventofcode.v2022.day05;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.*;

public class AssignmentsTest {

    Piles piles = null;

    @Test @TaskInput
    public void assignment1() {
        Input.processLinesSetsByEmptyLineDelimiter(set -> {
            if (!set.get(0).startsWith("move")) piles = new Piles(set);
            else set.forEach(line -> piles.processMove(line));
        });
        System.out.println("Containers on top: " + piles);
    }

    @Test @TaskInput
    public void assignment2() {
        Input.processLinesSetsByEmptyLineDelimiter(set -> {
            if (!set.get(0).startsWith("move")) piles = new Piles(set);
            else set.forEach(line -> piles.processMoveWithNewCrane(line));
        });
        System.out.println("Containers on top: " + piles);
    }

}
