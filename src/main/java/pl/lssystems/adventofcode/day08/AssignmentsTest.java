package pl.lssystems.adventofcode.day08;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AssignmentsTest {

    Forest forest;

    @Test
    public void assignment1() {
        forest = new Forest(Utils.process("day08/input.txt", l -> l));

        int visibleTrees = 0;
        for (int y = 0; y < forest.getHeight(); y++)
            for (int x = 0; x < forest.getWidth(); x++)
                if (forest.checkVisibility(x, y)) visibleTrees++;

        System.out.println("Total visible trees: " + visibleTrees);
    }

    @Test
    public void assignment2() {
        forest = new Forest(Utils.process("day08/input.txt", l -> l));

        List<Integer> treeScore = new ArrayList<>();
        for (int y = 0; y < forest.getHeight(); y++)
            for (int x = 0; x < forest.getWidth(); x++)
                treeScore.add(forest.calculateScenicScore(x, y));

        System.out.println("Max scenic score: " + treeScore.stream().max(Integer::compareTo).orElse(-1));
    }

}
