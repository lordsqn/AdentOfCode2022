package pl.lssystems.adventofcode.v2022.day04;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        AtomicInteger sum = new AtomicInteger();

        Input.processLinesWithDelimiterAsInt("[,-]", line -> {
            Pair<Range<Integer>, Range<Integer>> r = Pair.of(Range.between(line[0], line[1]), Range.between(line[2], line[3]));
            if (r.getLeft().containsRange(r.getRight()) || r.getRight().containsRange(r.getLeft()))
                sum.incrementAndGet();
        });

        System.out.println("Fully overlapped areas: " + sum);
    }

    @Test @TaskInput
    public void assignment2() {
        AtomicInteger sum = new AtomicInteger();

        Input.processLinesWithDelimiterAsInt("[,-]", line -> {
            Pair<Range<Integer>, Range<Integer>> r = Pair.of(Range.between(line[0], line[1]), Range.between(line[2], line[3]));
            if (r.getLeft().isOverlappedBy(r.getRight()))
                sum.incrementAndGet();
        });

        System.out.println("Partially overlapped areas: " + sum);
    }

}
