package pl.lssystems.adventofcode.v2022.day14;

import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

public class AssignmentsTest {

    @Test @TaskInput
    public void assignment1() {
        SandBox sb = new SandBox();
        Input.processLinesWithDelimiter(" -> ", line -> {
            sb.newShelf();
            for (String s : line) {
                String[] xy = s.split(",");
                sb.addRocks(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            }
        });
        sb.getSortedTree();
        Plain p = Plain.get();
        p.generatePlane(sb.getElements());
        System.out.println(p);
        while (sb.dropSand(500, 0, p.getPlainHeight())) {}
        p.generatePlane(sb.getElements());
        System.out.println(p);
        System.out.println("Number of sands: " + sb.getNumberOfSands());
    }

    @Test @TaskInput
    public void assignment2() {
        SandBox sb = new SandBox();
        Input.processLinesWithDelimiter(" -> ", line -> {
            sb.newShelf();
            for (String s : line) {
                String[] xy = s.split(",");
                sb.addRocks(Integer.parseInt(xy[0]), Integer.parseInt(xy[1]));
            }
        });
        Plain p = Plain.get();
        p.generatePlane(sb.getElements());
        int floorDistance = p.getPlainHeight()+1;
        sb.newShelf();
        sb.addRocks(0, floorDistance);
        sb.addRocks(p.getPlainWidth()+500, floorDistance);
        p.generatePlane(sb.getElements());
        sb.getSortedTree();
        System.out.println(p);
        while (sb.dropSand(500, 0, p.getPlainHeight())) {}
        p.generatePlane(sb.getElements());
        System.out.println(p);
        System.out.println("Number of sands: " + sb.getNumberOfSands());
    }

}
