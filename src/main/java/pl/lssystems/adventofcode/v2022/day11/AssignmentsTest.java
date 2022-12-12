package pl.lssystems.adventofcode.v2022.day11;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    MonkeyPlay monkeyPlay = new MonkeyPlay();

    @Test @TaskInput
    public void assignment1() {
        monkeyPlay.printGameSummary();
        Input.processLinesSetsByEmptyLineDelimiter(set -> monkeyPlay.addMonkeyToPlay(new Monkey(set)));
        int rounds = 20;
        monkeyPlay.startPlay(rounds);
        System.out.println("Monkey business for " + rounds + ": " + monkeyPlay.getMonkeyBusiness());
    }

    @Test @TaskInput
    public void assignment2() {
        monkeyPlay.printGameSummary();
        Input.processLinesSetsByEmptyLineDelimiter(set -> monkeyPlay.addMonkeyToPlay(new Monkey(set)));
        int rounds = 10000;
        monkeyPlay.setWorryDivider(false);
        monkeyPlay.startPlay(rounds);
        System.out.println("Monkey business for " + rounds + ": " + monkeyPlay.getMonkeyBusiness());
    }

}
