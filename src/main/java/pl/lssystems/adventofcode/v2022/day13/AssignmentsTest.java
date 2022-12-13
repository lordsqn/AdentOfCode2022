package pl.lssystems.adventofcode.v2022.day13;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.concurrent.atomic.AtomicInteger;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        AtomicInteger summary = new AtomicInteger(0);
        Input.processLinesSetsByEmptyLineDelimiter(pair -> {
            if (PacketOps.compare(PacketOps.compile(pair.get(0)), PacketOps.compile(pair.get(1))) == 1)
                summary.addAndGet(PacketOps.getCompareCounter());
        });
        System.out.println("Sum of right order packets indices: " + summary);
    }

    @Test @TaskInput
    public void assignment2() {
        Input.processLinesSetsByLineDelimiter("\n", set -> set.forEach(line -> {
            if (!line.isEmpty()) PacketOps.compileAndCollect(line);
        }));
        PacketOps.compileAndCollect("[[2]]");
        PacketOps.compileAndCollect("[[6]]");

        AtomicInteger counter = new AtomicInteger(0);
        AtomicInteger key = new AtomicInteger(1);
        PacketOps.getSortedCollection().forEach(packet -> {
            String str = packet.toString();
            int i = counter.incrementAndGet();
            if (str.equals("[[2]]") || str.equals("[[6]]"))
                key.accumulateAndGet(i, (x,y) -> x*y);
        });

        System.out.println("Decoder key for distress signal: " + key);
    }

}
