package pl.lssystems.adventofcode.day9;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

public class AssignmentsTest {

    @Test
    public void assignment1() {
        Head head = new Head(new Tail('T'));
        //Board.drawEveryFrame();
        Utils.processLine("day9/input.txt", line -> {
            String[] command = line.split(" ");
            head.move(Direction.getDirection(command[0]), Integer.parseInt(command[1]));
        });
        System.out.println(Board.get().drawFinalFrame(head));
        System.out.println("Number of tail moves: " + Move.getUniqueMovesRecord().size());
    }

    @Test
    public void assignment2() {
        Head head = new Head(new Tail('1', new Tail('2', new Tail('3', new Tail('4', new Tail('5', new Tail('6', new Tail('7', new Tail('8', new Tail('9'))))))))));
        //Board.drawEveryFrame();
        Utils.processLine("day9/input.txt", line -> {
            String[] command = line.split(" ");
            head.move(Direction.getDirection(command[0]), Integer.parseInt(command[1]));
        });
        System.out.println(Board.get().drawFinalFrame(head));
        System.out.println("Number of tail moves: " + (Move.getUniqueMovesRecord().size() + 1/*Starting position*/));
    }

}
