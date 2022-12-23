package pl.lssystems.adventofcode.v2022.day21;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MonkeyMatch {
    private final Map<String, Long> monkeyStore = new HashMap<>();
    private final Map<String, String> monkeys;

    public MonkeyMatch(List<String[]> monkeys) {
        this.monkeys = monkeys.stream().collect(Collectors.toMap(split -> split[0], split -> split[1]));
    }

    public Long monkeyRootYells() {
        return evalMonkey("root", monkeys);
    }

    public Long rootsEqualityTest() {
        monkeys.put("humn", null);

        String[] parts = monkeys.get("root").split(" ");
        String left  = parts[0];
        String right = parts[2];

        evalMonkey(left, monkeys);
        evalMonkey(right, monkeys);

        String monkey = monkeyStore.get(left) != null ? right : left;
        Long value = monkeyStore.get(left) != null ? monkeyStore.get(left) : monkeyStore.get(right);

        while (!monkey.equals("humn")) {
            String[] currentParts  = monkeys.get(monkey).split(" ");
            String currentLeft     = currentParts[0];
            String currentOperator = currentParts[1];
            String currentRight    = currentParts[2];

            if (monkeyStore.get(currentLeft) == null) {
                monkey = currentLeft;
                value = reverseCalculateLeft(currentOperator, value, monkeyStore.get(currentRight));
            } else {
                monkey = currentRight;
                value = reverseCalculateRight(currentOperator, value, monkeyStore.get(currentLeft));
            }
        }
        return value;
    }

    public Long evalMonkey(String monkey, Map<String, String> monkeys) {
        if (monkeyStore.containsKey(monkey))
            return monkeyStore.get(monkey);

        String value = monkeys.get(monkey);

        if (value == null) {
            monkeyStore.put(monkey, null);
            return null;
        }

        Long longValue;
        try {longValue = Long.parseLong(value);}
        catch (NumberFormatException e) {
            longValue = null;
        }

        if (longValue != null) {
            monkeyStore.put(monkey, longValue);
            return longValue;
        }

        String[] parts = value.split(" ");
        String operator = parts[1];
        Long left = evalMonkey(parts[0], monkeys);
        Long right = evalMonkey(parts[2], monkeys);

        if (left == null || right == null) {
            monkeyStore.put(monkey, null);
            return null;
        }

        longValue = calculate(operator, left, right);
        monkeyStore.put(monkey, longValue);

        return longValue;
    }

    public Long calculate(String operator, long left, long right) {
        long result = 0;
        switch (operator) {
            case "+": result = left + right; break;
            case "-": result = left - right; break;
            case "*": result = left * right; break;
            case "/": result = left / right; break;
        }
        return result;
    }

    public Long reverseCalculateLeft(String operator, long value, long operand) {
        long result = 0;
        switch (operator) {
            case "+": result = value - operand; break;
            case "-": result = value + operand; break;
            case "*": result = value / operand; break;
            case "/": result = value * operand; break;
        }
        return result;
    }

    public Long reverseCalculateRight(String operator, long value, long operand) {
        long result = 0;
        switch (operator) {
            case "+": result = value - operand; break;
            case "-": result = operand - value; break;
            case "*": result = value / operand; break;
            case "/": result = operand / value; break;
        }
        return result;
    }
}
