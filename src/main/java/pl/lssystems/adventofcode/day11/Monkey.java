package pl.lssystems.adventofcode.day11;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Monkey {
    private final String name;
    private final LinkedList<BigInteger> holding = new LinkedList<>();
    private Pair<Operation, Object> operation;
    private int testDivider;
    private String positiveOutcomeMonkeyName;
    private String negativeOutcomeMonkeyName;

    private long inspectionCounter = 0;

    public Monkey(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getHoldingItems() {
        return holding.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }

    public void addItem(BigInteger item) {
        holding.add(item);
    }

    public BigInteger inspectItem() {
        BigInteger item = holding.pop();
        inspectionCounter++;
        return item;
    }

    public long getInspectionCount() {
        return inspectionCounter;
    }

    public Pair<Operation, Object> getItemWorryFormula() {
        return operation;
    }

    public void throwItem(Monkey monkey, BigInteger item) {
        monkey.addItem(item);
    }

    protected void setOperation(Operation operation, Object value) {
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
