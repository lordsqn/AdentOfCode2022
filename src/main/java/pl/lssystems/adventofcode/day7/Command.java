package pl.lssystems.adventofcode.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static pl.lssystems.adventofcode.day7.TreeNode.*;

public class Command {
    enum CommandType {CD, LS}

    private CommandType type;
    private final List<String> arguments = new ArrayList<>();
    private final List<List<String>> output = new LinkedList<>();

    public Command(String line) {
        String[] factors = line.split(" ");
        switch (factors[1]) {
            case "cd": type = CommandType.CD; break;
            case "ls": type = CommandType.LS; break;
        }
        if (factors.length > 2)
            arguments.addAll(Arrays.asList(factors).subList(2, factors.length));
    }

    public void feedOutput(String line) {
        output.add(Arrays.asList(line.split(" ")));
    }

    public Command recreate(String nextCommand) {
        if (type.equals(CommandType.CD)) {
            String arg = arguments.get(0);
            switch (arg) {
                case "/":
                    TreeNode.setCurrentNode(TreeNode.getRoot());
                    break;
                case "..":
                    TreeNode.goUp();
                    break;
                default:
                    TreeNode.goDown(arg);
                    break;
            }
        }
        if (type.equals(CommandType.LS)) {
            for (List<String> elem : output) {
                if (elem.get(0).equals("dir")) {
                    new TreeNode(TreeNode.getCurrentNode(), NodeType.DIR, elem.get(1), 0);
                } else {
                    new TreeNode(TreeNode.getCurrentNode(), NodeType.FILE, elem.get(1), Long.parseLong(elem.get(0)));
                }
            }
        }
        return nextCommand == null ? null : new Command(nextCommand);
    }

    @Override
    public String toString() {
        return "Command{" +
                "type=" + type +
                ", arguments=" + arguments +
                ", output=" + output +
                '}';
    }

}
