package pl.lssystems.adventofcode.day11;

import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Monkey {
    private final String name;
    private final LinkedList<Integer> holding = new LinkedList<>();
    private Pair<Character, Object> operation;
    private int testDivider = 0;
    private String positiveOutcomeMonkeyName;
    private String negativeOutcomeMonkeyName;

    private int inspectionCounter = 0;

    public Monkey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getHoldingItems() {
        return holding.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    public void addItem(int item) {
        holding.add(item);
    }

    public int inspectItem() {
        int item = holding.pop();
        inspectionCounter++;
        return item;
    }

    public int getInspectionCount() {
        return inspectionCounter;
    }

    public Pair<Character, Object> getItemWorryFormula() {
        return operation;
    }

    public void throwItem(Monkey monkey, int item) {
        monkey.addItem(item);
    }

    protected void setOperation(char operation, Object value) {
        this.operation = Pair.of(operation, value);
    }

    protected int getTestDivider() {
        return this.testDivider;
    }

    protected void setTestDivider(int testDivider) {
        this.testDivider = testDivider;
    }

    public String getPositiveOutcomeMonkeyName() {
        return positiveOutcomeMonkeyName;
    }

    protected void setPositiveOutcomeMonkeyName(String positiveOutcomeMonkeyName) {
        this.positiveOutcomeMonkeyName = positiveOutcomeMonkeyName;
    }

    public String getNegativeOutcomeMonkeyName() {
        return negativeOutcomeMonkeyName;
    }

    protected void setNegativeOutcomeMonkeyName(String negativeOutcomeMonkeyName) {
        this.negativeOutcomeMonkeyName = negativeOutcomeMonkeyName;
    }

    @Override
    public String toString() {
        return "Monkey " + getName() + ":\n" +
               "  Starting items: " + getHoldingItems() + "\n" +
               "  Operation: new = old " + operation.getLeft() + " " + operation.getRight() + "\n" +
               "  Test: divisible by " + testDivider + "\n" +
               "    If true: throw to monkey " + positiveOutcomeMonkeyName + "\n" +
               "    If false: throw to monkey " + negativeOutcomeMonkeyName + "\n";
    }

}
