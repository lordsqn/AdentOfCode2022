package pl.lssystems.adventofcode.v2022.day19;

import org.junit.jupiter.api.Test;

import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {
    Factory factory = new Factory();

    @Test @TaskInput
    public void assignment1() {
        Input.processLinesWithPatternAsInt(
                "Blueprint ([0-9]+): "
                        + "Each ore robot costs ([0-9]+) ore. "
                        + "Each clay robot costs ([0-9]+) ore. "
                        + "Each obsidian robot costs ([0-9]+) ore and ([0-9]+) clay. "
                        + "Each geode robot costs ([0-9]+) ore and ([0-9]+) obsidian.", line ->
                        factory.addBlueprint(line[0],line[1],line[2],line[3],line[4],line[5],line[6]));

        int qualityLevels = 0;
        for (Blueprint bp : factory.getBlueprints())
            qualityLevels += factory.applyBlueprint(bp, 24) * bp.getId();

        System.out.println("Blueprint quality score: " + qualityLevels);
    }

    @Test @TaskInput
    public void assignment2() {
        Input.processLinesWithPatternAsInt(
                "Blueprint ([0-9]+): "
                        + "Each ore robot costs ([0-9]+) ore. "
                        + "Each clay robot costs ([0-9]+) ore. "
                        + "Each obsidian robot costs ([0-9]+) ore and ([0-9]+) clay. "
                        + "Each geode robot costs ([0-9]+) ore and ([0-9]+) obsidian.", line ->
                        factory.addBlueprint(line[0],line[1],line[2],line[3],line[4],line[5],line[6]));

        int product = 1;
        for (int i = 0; i < Math.min(factory.getBlueprints().size(), 3); i++)
            product *= factory.applyBlueprint(factory.getBlueprints().get(i), 32);

        System.out.println("Maximal Geode Product: " + product);
    }

}