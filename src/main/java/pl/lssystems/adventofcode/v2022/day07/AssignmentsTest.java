package pl.lssystems.adventofcode.v2022.day07;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.Input;
import pl.lssystems.adventofcode.TaskInput;

import static pl.lssystems.adventofcode.v2022.day07.TreeNode.NodeType.*;

public class AssignmentsTest {

    Command command = null;

    @Test @TaskInput
    public void assignment1() {
        Input.processLines(line -> {
            if (line.startsWith("$")) command = (command == null) ? new Command(line) : command.recreate(line);
            else command.feedOutput(line);
        });
        command.recreate(null);
        System.out.println("Tree Structure: \n" + TreeNode.printTree());
        System.out.println("Total size of directories with size below 100000: " + findSizeBelowNRecursive(TreeNode.getRoot(), 100000));
    }

    @Test @TaskInput
    public void assignment2() {
        Input.processLines(line -> {
            if (line.startsWith("$")) command = (command == null) ? new Command(line) : command.recreate(line);
            else command.feedOutput(line);
        });
        command.recreate(null);
        long capacity = 70000000;
        long occupied = TreeNode.getRoot().getSize();
        long required = 30000000;
        long freeSpace = capacity - occupied;
        long toRetrieve = required - freeSpace;

        System.out.println("Tree Structure: \n" + TreeNode.printTree());

        System.out.println(toRetrieve < 0 ?
                "There is enough space to download update." :
                "Best file to delete to get exact amount of space for update: " +
                        findSmallestSizeDeltaRecursive(TreeNode.getRoot(), toRetrieve, Pair.of(capacity, TreeNode.getRoot())).getValue().toString());
    }

    public long findSizeBelowNRecursive(TreeNode node, long size) {
        long sizeSum = 0;
        for (TreeNode child : node.getChildren()) {
            sizeSum += (child.getType().equals(DIR) && child.getSize() < size) ? child.getSize() : 0;
            sizeSum += findSizeBelowNRecursive(child, size);
        }
        return sizeSum;
    }

    public Pair<Long, TreeNode> findSmallestSizeDeltaRecursive(TreeNode node, long toRetrieve, Pair<Long,TreeNode> smallestDelta) {
        long spaceUnallocated = toRetrieve - node.getSize();
        if (spaceUnallocated < 0 && Math.abs(spaceUnallocated) < smallestDelta.getKey())
            smallestDelta = Pair.of(Math.abs(spaceUnallocated), node);

        for (TreeNode child : node.getChildren())
            if (child.getType().equals(DIR))
                smallestDelta = findSmallestSizeDeltaRecursive(child, toRetrieve, smallestDelta);

        return smallestDelta;
    }

}
