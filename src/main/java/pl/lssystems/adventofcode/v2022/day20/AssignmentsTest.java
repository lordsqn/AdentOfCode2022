package pl.lssystems.adventofcode.v2022.day20;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        Groove groove = new Groove(Input.readLines());
        groove.mix();

        System.out.println("Sum of the three numbers that form the grove coordinates: " + groove.getCoordinates());
    }

    @Test @TaskInput
    public void assignment2() {
        Groove groove = new Groove(Input.readLines());
        groove.multiplyByEncryptionKey(811589153L);

        for (int i = 0; i < 10; i++)
            groove.mix();

        System.out.println("Sum of the three numbers that form the grove coordinates after decryption: " + groove.getCoordinates());
    }

}