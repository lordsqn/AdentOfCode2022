package pl.lssystems.adventofcode.day6;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AssignmentsTest {

    public int findNDistinctCharacters(char[] c, int numberOfChars) {
        return IntStream.range(numberOfChars, c.length)
                .map(i -> IntStream.range(0, numberOfChars)
                        .map(j -> -j + numberOfChars)
                        .mapToObj(j -> c[i-j])
                        .collect(Collectors.toSet()).size() == numberOfChars ? i : Integer.MAX_VALUE)
                .min().orElse(-1);
    }

    @Test
    public void assignment1() {
        Utils.processLine("day6/input.txt", line -> {
            System.out.println("Index of signal marker: " + findNDistinctCharacters(line.toCharArray(), 4));
        });
    }

    @Test
    public void assignment2() {
        Utils.processLine("day6/input.txt", line -> {
            System.out.println("Index of message marker: " + findNDistinctCharacters(line.toCharArray(), 14));
        });
    }

}
