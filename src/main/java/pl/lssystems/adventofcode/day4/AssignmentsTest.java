package pl.lssystems.adventofcode.day4;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AssignmentsTest {

    public Pair<Range<Integer>, Range<Integer>> parseRanges(String line) {
        List<Integer> s = Arrays.stream(line.split("[,-]")).map(Integer::valueOf).collect(Collectors.toList());
        return Pair.of(Range.between(s.get(0), s.get(1)), Range.between(s.get(2), s.get(3)));
    }

    @Test
    public void assignment1() {
        AtomicInteger sum = new AtomicInteger();

        Utils.processLine("day4/input.txt", line -> {
            Pair<Range<Integer>, Range<Integer>> r = parseRanges(line);
            if (r.getLeft().containsRange(r.getRight()) || r.getRight().containsRange(r.getLeft()))
                sum.incrementAndGet();
        });

        System.out.println("Fully overlapped areas: " + sum);
    }

    @Test
    public void assignment2() {
        AtomicInteger sum = new AtomicInteger();

        Utils.processLine("day4/input.txt", line -> {
            Pair<Range<Integer>, Range<Integer>> r = parseRanges(line);
            if (r.getLeft().isOverlappedBy(r.getRight()))
                sum.incrementAndGet();
        });

        System.out.println("Partially overlapped areas: " + sum);
    }

}
