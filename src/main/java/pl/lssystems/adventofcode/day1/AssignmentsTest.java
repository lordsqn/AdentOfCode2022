package pl.lssystems.adventofcode.day1;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AssignmentsTest {

    public List<Integer> calculateSumOfCaloriesForEachElf(List<String> list) {
        List<Integer> sums = new ArrayList<Integer>() {{add(0);}};
        list.forEach(line -> {
            if (line.isEmpty()) sums.add(0);
            else sums.set(sums.size()-1, sums.get(sums.size()-1) + Integer.parseInt(line));
        });
        return sums;
    }

    @Test
    public void assignment1() {
        List<Integer> sumCaloriesOfEachElf = Utils.process("day1/input.txt", this::calculateSumOfCaloriesForEachElf);
        int highestCarriedCalories = sumCaloriesOfEachElf.stream().max(Integer::compareTo).orElse(-1);
        System.out.println("Elf with highest carry calories count: " + highestCarriedCalories);
    }

    @Test
    public void assignment2() {
        List<Integer> sumCaloriesOfEachElf = Utils.process("day1/input.txt", this::calculateSumOfCaloriesForEachElf);

        sumCaloriesOfEachElf.sort(Integer::compareTo);
        Collections.reverse(sumCaloriesOfEachElf);

        int sumOfTopCarriers = sumCaloriesOfEachElf.subList(0, 3).stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum of top 3 highest carriers: " + sumOfTopCarriers);
    }

}
