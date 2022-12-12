package pl.lssystems.adventofcode.v2022.day08;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        Forest forest = new Forest(Input.readLinesAsInts());

        int visibleTrees = 0;
        for (int y = 0; y < forest.getHeight(); y++)
            for (int x = 0; x < forest.getWidth(); x++)
                if (forest.checkVisibility(x, y)) visibleTrees++;

        System.out.println("Total visible trees: " + visibleTrees);
    }

    @Test @TaskInput
    public void assignment2() {
        Forest forest = new Forest(Input.readLinesAsInts());

        List<Integer> treeScore = new ArrayList<>();
        for (int y = 0; y < forest.getHeight(); y++)
            for (int x = 0; x < forest.getWidth(); x++)
                treeScore.add(forest.calculateScenicScore(x, y));

        System.out.println("Max scenic score: " + treeScore.stream().max(Integer::compareTo).orElse(-1));
    }

}
