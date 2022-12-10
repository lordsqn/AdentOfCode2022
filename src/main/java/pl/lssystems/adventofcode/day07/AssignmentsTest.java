package pl.lssystems.adventofcode.day07;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import pl.lssystems.adventofcode.utils.Utils;

import static pl.lssystems.adventofcode.day07.TreeNode.NodeType.*;

public class AssignmentsTest {

    Command command = null;

    @Test
    public void assignment1() {
        analyzeInput();
        System.out.println("Tree Structure: \n" + TreeNode.printTree());
        System.out.println("Total size of directories with size below 100000: " + findSizeBelowNRecursive(TreeNode.getRoot(), 100000));
    }

    @Test
    public void assignment2() {
        analyzeInput();
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

    public void analyzeInput() {
        Utils.processLine("day07/input.txt", line -> {
            if (line.startsWith("$")) command = (command == null) ? new Command(line) : command.recreate(line);
            else command.feedOutput(line);
        });
        command.recreate(null);
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
