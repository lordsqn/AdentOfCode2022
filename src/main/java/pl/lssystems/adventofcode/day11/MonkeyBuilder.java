package pl.lssystems.adventofcode.day11;

import java.math.BigInteger;
import java.util.Arrays;

public class MonkeyBuilder {
    private static final String nameID = "Monkey ";
    private static final String itemsID = "  Starting items: ";
    private static final String operationID = "  Operation: new = old ";
    private static final String testID = "  Test: divisible by ";
    private static final String positiveOutcomeID = "    If true: throw to monkey ";
    private static final String negativeOutcomeID = "    If false: throw to monkey ";
    private String name;
    private String items;
    private String operation;
    private String test;
    private String positiveOutcome;
    private String negativeOutcome;

    public void parseLine(String line) {
        if (line.startsWith(nameID))
            name = line.substring(nameID.length(), line.length()-1);
        if (line.startsWith(itemsID))
            items = line.substring(itemsID.length());
        if (line.startsWith(operationID))
            operation = line.substring(operationID.length());
        if (line.startsWith(testID))
            test = line.substring(testID.length());
        if (line.startsWith(positiveOutcomeID))
            positiveOutcome = line.substring(positiveOutcomeID.length());
        if (line.startsWith(negativeOutcomeID))
            negativeOutcome = line.substring(negativeOutcomeID.length());
    }

    public Monkey build() {
        Monkey monkey = new Monkey(name);
        Arrays.stream(items.split(", ")).forEach(item -> monkey.addItem(new BigInteger(item)));

        String[] factors = operation.split(" ");
        Object value = factors[1];
        try {
            value = Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {/* bu-hu */}

        monkey.setOperation(Operation.getOperationBySign(factors[0].charAt(0)), value);
        monkey.setTestDivider(Integer.parseInt(test));
        monkey.setPositiveOutcomeMonkeyName(positiveOutcome);
        monkey.setNegativeOutcomeMonkeyName(negativeOutcome);
        return monkey;
    }

}
