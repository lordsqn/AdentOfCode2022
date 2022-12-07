package pl.lssystems.adventofcode.day7;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import static pl.lssystems.adventofcode.day7.TreeNode.NodeType.*;

public class TreeNode {
    enum NodeType {DIR, FILE}
    private NodeType type;
    private String name;
    private long size = 0;
    private TreeNode parent;
    private List<TreeNode> children = new LinkedList<>();

    private static TreeNode root;
    private static TreeNode currentNode;

    public static TreeNode getRoot() {
        return (root == null) ? root = new TreeNode(null, DIR, "/", 0) : root;
    }

    public static TreeNode getCurrentNode() {
        return (currentNode == null) ? currentNode = getRoot() : currentNode;
    }

    public static void setCurrentNode(TreeNode node) {
        currentNode = node;
    }

    public static TreeNode goUp() {
        return currentNode = getCurrentNode().getParent() == null ? getRoot() : getCurrentNode().getParent();
    }

    public static TreeNode goDown(String name) {
        return currentNode = getCurrentNode().getChildren().stream().filter(node -> node.getName().equals(name)).findFirst().orElse(null);
    }

    TreeNode(TreeNode parent, NodeType type, String name, long size) {
        this.parent = parent;
        if (parent != null) {
            parent.addChild(this);
            parent.increaseSize(size);
        }
        this.type = type;
        this.name = name;
        this.size = size;
    }

    public TreeNode getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public NodeType getType() {
        return type;
    }

    public long getSize() {
        return size;
    }

    public void increaseSize(long factor) {
        size += factor;
        if (parent != null) parent.increaseSize(factor);
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    @Override
    public String toString() {
        return "- " + name + " ("+ type.toString().toLowerCase(Locale.ROOT) + ", " +
                (type.equals(FILE) ? "size=" : "totalSize=") + size + ")";
    }

    public static String printTree() {
        return printRecurse(getRoot(), 0);
    }

    private static String printRecurse(TreeNode node, int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(new String(new char[level * 2]).replace("\0", " "))
                .append(node.toString()).append("\n");
        for (TreeNode child : node.getChildren()) {
            sb.append(printRecurse(child, level + 1));
        }
        return sb.toString();
    }

}