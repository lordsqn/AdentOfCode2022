package pl.lssystems.adventofcode.v2022.day01;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        List<Integer> sums = new ArrayList<Integer>() {{add(0);}};
        Input.processLinesSetsByEmptyLineDelimiter(set -> {
            set.forEach(line -> sums.set(sums.size()-1, sums.get(sums.size()-1) + Integer.parseInt(line)));
            sums.add(0);
        });
        int highestCarriedCalories = sums.stream().max(Integer::compareTo).orElse(-1);
        System.out.println("Elf with highest carry calories count: " + highestCarriedCalories);
    }

    @Test @TaskInput
    public void assignment2() {
        List<Integer> sums = new ArrayList<Integer>() {{add(0);}};
        Input.processLinesSetsByEmptyLineDelimiter(set -> {
            set.forEach(line -> sums.set(sums.size()-1, sums.get(sums.size()-1) + Integer.parseInt(line)));
            sums.add(0);
        });

        sums.sort(Integer::compareTo);
        Collections.reverse(sums);

        int sumOfTopCarriers = sums.subList(0, 3).stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of top 3 highest carriers: " + sumOfTopCarriers);
    }

}
