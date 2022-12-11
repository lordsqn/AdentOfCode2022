package pl.lssystems.adventofcode.day11;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

public class AssignmentsTest {

    MonkeyBuilder monkeyBuilder = new MonkeyBuilder();
    MonkeyPlay monkeyPlay = new MonkeyPlay();

    public void parseInput() {
        monkeyPlay.printPlaySummary();
        //monkeyPlay.printRoundSummary();
        //monkeyPlay.printGameSummary();
        Utils.processLine("day11/input.txt", line -> {
            monkeyBuilder.parseLine(line);
            if (line.startsWith("    If false:"))
                monkeyPlay.addMonkeyToPlay(monkeyBuilder.build());
        });
    }

    @Test
    public void assignment1() {
        parseInput();
        int rounds = 20;
        monkeyPlay.startPlay(rounds);
        System.out.println("Monkey business for " + rounds + ": " + monkeyPlay.getMonkeyBusiness());
    }

    @Test
    public void assignment2() {
        parseInput();
        int rounds = 10000;
        monkeyPlay.setWorryDivider(false);
        monkeyPlay.startPlay(rounds);
        System.out.println("Monkey business for " + rounds + ": " + monkeyPlay.getMonkeyBusiness());
    }

}
