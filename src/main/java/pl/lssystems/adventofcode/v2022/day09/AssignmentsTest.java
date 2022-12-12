package pl.lssystems.adventofcode.v2022.day09;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        Head head = new Head(new Tail('T'));
        //Board.drawEveryFrame();
        Input.processLinesWithDelimiter(" ", line -> head.move(Direction.getDirection(line[0]), Integer.parseInt(line[1])));
        Board.get().drawFinalFrame(head);
        System.out.println("Number of head moves: " + Move.getUniqueMovesRecord().size());
    }

    @Test @TaskInput
    public void assignment2() {
        Head head = new Head(new Tail('1', new Tail('2', new Tail('3', new Tail('4', new Tail('5', new Tail('6', new Tail('7', new Tail('8', new Tail('9'))))))))));
        //Board.drawEveryFrame();
        Tail.setTrackHead(false);
        Input.processLinesWithDelimiter(" ", line -> head.move(Direction.getDirection(line[0]), Integer.parseInt(line[1])));
        Board.get().drawFinalFrame(head);
        System.out.println("Number of tail moves: " + (Move.getUniqueMovesRecord().size()));
    }

}
