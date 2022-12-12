package pl.lssystems.adventofcode.v2022.day06;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        Input.processLinesAsChars(chars -> System.out.println("Index of signal marker: " + findNDistinctCharacters(chars, 4)));
    }

    @Test @TaskInput
    public void assignment2() {
        Input.processLinesAsChars(chars -> System.out.println("Index of message marker: " + findNDistinctCharacters(chars, 14)));
    }

    public int findNDistinctCharacters(char[] c, int numberOfChars) {
        return IntStream.range(numberOfChars, c.length)
                .map(i -> IntStream.range(0, numberOfChars)
                        .map(j -> -j + numberOfChars)
                        .mapToObj(j -> c[i-j])
                        .collect(Collectors.toSet()).size() == numberOfChars ? i : Integer.MAX_VALUE)
                .min().orElse(-1);
    }

}
