package pl.lssystems.adventofcode.v2022.day11;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Monkey {
    private final String name;
    private final LinkedList<BigInteger> holding = new LinkedList<>();
    private Pair<Operation, Object> operation;
    private int testDivider;
    private String positiveOutcomeMonkeyName;
    private String negativeOutcomeMonkeyName;

    private long inspectionCounter = 0;

    public Monkey(List<String> monkeyInfo) {
        name = monkeyInfo.get(0).substring("Monkey ".length(), monkeyInfo.get(0).length()-1);
        String items = monkeyInfo.get(1).substring("  Starting items: ".length());
        Arrays.stream(items.split(", ")).forEach(item -> addItem(new BigInteger(item)));

        String operation = monkeyInfo.get(2).substring("  Operation: new = old ".length());
        String[] factors = operation.split(" ");
        Object value = factors[1];
        try {
            value = Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {/* bu-hu */}
        setOperation(Operation.getOperationBySign(factors[0].charAt(0)), value);
        setTestDivider(Integer.parseInt(monkeyInfo.get(3).substring("  Test: divisible by ".length())));
        setPositiveOutcomeMonkeyName(monkeyInfo.get(4).substring("    If true: throw to monkey ".length()));
        setNegativeOutcomeMonkeyName(monkeyInfo.get(5).substring("    If false: throw to monkey ".length()));
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
