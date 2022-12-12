package pl.lssystems.adventofcode.v2022.day02;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.concurrent.atomic.AtomicInteger;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        AtomicInteger score = new AtomicInteger();

        Input.processLinesWithDelimiter(" ", line -> {
            int OP = line[0].charAt(0) - 64;
            int MP = line[1].charAt(0) - 87, dst = Math.abs(OP - MP);
            int RE = OP != MP ? (OP > MP && dst == 1 || OP < MP && dst != 1 ? 0 : 6) : 3;
            score.addAndGet(MP + RE);
        });

        System.out.println("Total score: " + score);
    }

    @Test @TaskInput
    public void assignment2() {
        AtomicInteger score = new AtomicInteger();

        Input.processLinesWithDelimiter(" ", line -> {
            int OP = line[0].charAt(0) - 64;
            int RE = line[1].charAt(0) - 87;
            int MP = RE == 1 ? (OP == 1 ? 3 : OP - 1) : (RE == 3 ? (OP == 3 ? 1 : OP + 1) : OP);
            score.addAndGet(MP + (RE == 1 ? 0 : RE == 3 ? 6 : 3));
        });

        System.out.println("Total score: " + score);
    }

}
