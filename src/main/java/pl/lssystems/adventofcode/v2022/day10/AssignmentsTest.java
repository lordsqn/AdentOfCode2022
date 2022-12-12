package pl.lssystems.adventofcode.v2022.day10;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        CPU cpu = new CPU();
        Input.processLines(cpu::parseInstruction);
        System.out.println("Finish Info | Cycle: " + cpu.getCycle() + ", RegisterX: " + cpu.getRegisterX());
        System.out.println("Summary signal strength: " + cpu.getSummarySignalStrength());
    }

    @Test @TaskInput
    public void assignment2() {
        CPU cpu = new CPU();
        CRT crt = new CRT(cpu);
        Input.processLines(cpu::parseInstruction);
        System.out.println("Current frame:\n" + crt.renderFrame());
    }

}
