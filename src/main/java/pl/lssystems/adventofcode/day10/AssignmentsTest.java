package pl.lssystems.adventofcode.day10;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

public class AssignmentsTest {

    @Test
    public void assignment1() {
        CPU cpu = new CPU();
        Utils.processLine("day10/input.txt", cpu::parseInstruction);
        System.out.println("Finish Info | Cycle: " + cpu.getCycle() + ", RegisterX: " + cpu.getRegisterX());
        System.out.println("Summary signal strength: " + cpu.getSummarySignalStrength());
    }

    @Test
    public void assignment2() {
        CPU cpu = new CPU();
        CRT crt = new CRT(cpu);
        Utils.processLine("day10/input.txt", cpu::parseInstruction);
        System.out.println("Current frame:\n" + crt.renderFrame());
    }

}
