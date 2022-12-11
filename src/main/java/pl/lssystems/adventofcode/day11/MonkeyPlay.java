package pl.lssystems.adventofcode.day11;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class MonkeyPlay {
    int worryLevel = 0;

    private final Map<String, Monkey> monkeys = new LinkedHashMap<>();

    boolean playLogging = false;
    boolean roundSummary = false;
    boolean gameSummary = true;

    public void addMonkeyToPlay(Monkey monkey) {
        monkeys.put(monkey.getName(), monkey);
    }

    public List<Monkey> getMonkeys() {
        return new ArrayList<>(monkeys.values());
    }

    public void startPlay(int rounds) {
        for (int round = 1; round <= rounds; round++) { /* rounds */                      if (playLogging) System.out.println("Start round 1");
            for (Monkey monkey : monkeys.values()) {   /* turns */                        if (playLogging) System.out.println("Monkey " + monkey.getName() + ":");
                while(true) {
                    try {
                        int itemValue = monkey.inspectItem();                             if (playLogging) System.out.println("  Monkey inspects an item with a worry level of " + itemValue);

                        Pair<Character, Object> operation = monkey.getItemWorryFormula();

                        char worryOperator = operation.getKey();
                        int worryFactor = decodeWorryFactor(operation.getValue(), itemValue);
                        switch (worryOperator) {
                            case '+': worryLevel = itemValue + worryFactor;               if (playLogging) System.out.println("    Worry level is increased by " + worryFactor + " to " + worryLevel + ".");
                                break;
                            case '*': worryLevel = itemValue * worryFactor;               if (playLogging) System.out.println("    Worry level is multiplied by " + worryFactor + " to " + worryLevel + ".");
                                break;
                        }
                        worryLevel =  worryLevel / 3;                                     if (playLogging) System.out.println("    Monkey gets bored with item. Worry level is divided by 3 to " + worryLevel + ".");

                        boolean testResult = worryLevel%monkey.getTestDivider() == 0;     if (playLogging) System.out.println("    Current worry level is " + (testResult ? "" : "not ") + "divisible by " + monkey.getTestDivider() + ".");
                        String nextMonkeyName = testResult ? monkey.getPositiveOutcomeMonkeyName() : monkey.getNegativeOutcomeMonkeyName();

                        monkey.throwItem(monkeys.get(nextMonkeyName), worryLevel);        if (playLogging) System.out.println("    Item with worry level " + worryLevel + " is thrown to monkey " + nextMonkeyName + ".");
                    } catch (NoSuchElementException e) {
                        break;
                    }
                }
            }
            if (roundSummary) {
                System.out.println("After round " + round + ", the monkeys are holding items with these worry levels:");
                for (Monkey monkey : monkeys.values()) {
                    System.out.println("Monkey " + monkey.getName() + ": " + monkey.getHoldingItems());
                }
            }
        }
        if (gameSummary)
            for (Monkey monkey : monkeys.values())
                System.out.println("Monkey " + monkey.getName() + " inspected items " + monkey.getInspectionCount() + " times.");
    }

    public int getMonkeyBusiness() {
        int[] topMonkeys = new int[2];
        Collection<Monkey> group = monkeys.values();
        for (int i = 0; i < topMonkeys.length; i++) {
            Monkey highest = Collections.max(group, Comparator.comparing(Monkey::getInspectionCount));
            topMonkeys[i] = highest.getInspectionCount();
            group.remove(highest);
        }
        return topMonkeys[0] * topMonkeys[1];
    }

    private int decodeWorryFactor(Object worryFactor, int itemValue) {
        int factor = 0;
        if (worryFactor instanceof Integer) {
            factor = (Integer) worryFactor;
        } else if (worryFactor instanceof String) {
            if (worryFactor.equals("old"))
                factor = itemValue;
        }
        return factor;
    }

}
