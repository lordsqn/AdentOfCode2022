package pl.lssystems.adventofcode.v2022.day23;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        UnstableDiffusion unstableDiffusion = new UnstableDiffusion(Input.readLinesAsChars());
        System.out.println("Empty ground tiles contained in rectangle: " + unstableDiffusion.countEmptyRectangles(10));
    }

    @Test @TaskInput
    public void assignment2() {
        UnstableDiffusion unstableDiffusion = new UnstableDiffusion(Input.readLinesAsChars());
        System.out.println("Number of the first round where no Elf moves: " + unstableDiffusion.countRoundsWithStillElves());
    }

}