package pl.lssystems.adventofcode.day11;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

public class AssignmentsTest {

    @Test
    public void assignment1() {
        MonkeyPlay monkeyPlay = new MonkeyPlay();
        MonkeyBuilder monkeyBuilder = new MonkeyBuilder();
        Utils.processLine("day11/input.txt", line -> {
            monkeyBuilder.parseLine(line);
            if (line.startsWith("    If false:"))
                monkeyPlay.addMonkeyToPlay(monkeyBuilder.build());
        });
        int rounds = 20;
        monkeyPlay.startPlay(rounds);
        System.out.println("Monkey business for " + rounds + ": " + monkeyPlay.getMonkeyBusiness());
    }

    @Test
    public void assignment2() {

    }

}
