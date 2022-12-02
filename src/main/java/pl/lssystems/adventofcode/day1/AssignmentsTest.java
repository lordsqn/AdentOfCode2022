package pl.lssystems.adventofcode.day1;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentsTest {

    public void calculateSumOfCaloriesForEachElf(String line, List<Integer> sumCaloriesOfEachElf) {
        if (line.isEmpty()) {
            sumCaloriesOfEachElf.add(0);
        } else {
            int last = sumCaloriesOfEachElf.size() - 1;
            sumCaloriesOfEachElf.set(last, sumCaloriesOfEachElf.get(last) + Integer.parseInt(line));
        }
    }

    @Test
    public void assigment1() throws IOException {
        List<Integer> sumCaloriesOfEachElf = new ArrayList<Integer>() {{add(0);}};
        Utils.processLine("day1/input.txt", line -> calculateSumOfCaloriesForEachElf(line, sumCaloriesOfEachElf));

        int highestCarriedCalories = sumCaloriesOfEachElf.stream().max(Integer::compareTo).orElse(-1);
        System.out.println("Elf with highest carry calories count: " + highestCarriedCalories);
    }

    @Test
    public void assigment2() throws IOException {
        List<Integer> sumCaloriesOfEachElf = new ArrayList<Integer>() {{add(0);}};
        Utils.processLine("day1/input.txt", line -> calculateSumOfCaloriesForEachElf(line, sumCaloriesOfEachElf));

        sumCaloriesOfEachElf.sort(Integer::compareTo);
        Collections.reverse(sumCaloriesOfEachElf);

        int sumOfTopCarriers = sumCaloriesOfEachElf.subList(0, 3).stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of top 3 highest carriers: " + sumOfTopCarriers);
    }

}
