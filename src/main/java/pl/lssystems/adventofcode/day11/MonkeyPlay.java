package pl.lssystems.adventofcode.day11;

import org.apache.commons.lang3.tuple.Pair;

import java.math.BigInteger;
import java.util.*;

public class MonkeyPlay {
    BigInteger worryLevel = new BigInteger("0");
    boolean worryDivider = true;
    private final Map<String, Monkey> monkeys = new LinkedHashMap<>();

    boolean playSummary = false;
    boolean roundSummary = false;
    boolean gameSummary = true;

    public void addMonkeyToPlay(Monkey monkey) {
        monkeys.put(monkey.getName(), monkey);
    }

    public void startPlay(int rounds) {
        long commonPrime = calculateCommonPrime();
        for (int round = 1; round <= rounds; round++) { /* rounds */                     if (playSummary) System.out.println("Start round " + round);
            for (Monkey monkey : monkeys.values()) {   /* turns */                       if (playSummary) System.out.println("Monkey " + monkey.getName() + ":");
                while(true) {
                    try {
                        BigInteger itemValue = monkey.inspectItem().remainder(BigInteger.valueOf(commonPrime));
                                                                                         if (playSummary) System.out.println("  Monkey inspects an item with a worry level of " + itemValue + ".");
                        Operation worryOperator = monkey.getItemWorryFormula().getKey();
                        BigInteger worryFactor = decodeWorryFactor(monkey.getItemWorryFormula().getValue(), itemValue);
                        switch (worryOperator) {
                            case ADD: worryLevel = itemValue.add(worryFactor);           if (playSummary) System.out.println("    Worry level increases by " + worryFactor + " to " + worryLevel + ".");
                                break;
                            case MULTIPLY: worryLevel = itemValue.multiply(worryFactor); if (playSummary) System.out.println("    Worry level is multiplied by " + worryFactor + " to " + worryLevel + ".");
                                break;
                        }
                        if (worryDivider) {
                            worryLevel = worryLevel.divide(BigInteger.valueOf(3));       if (playSummary) System.out.println("    Monkey gets bored with item. Worry level is divided by 3 to " + worryLevel + ".");
                        }
                        boolean testResult = worryLevel.remainder(BigInteger.valueOf(monkey.getTestDivider())).equals(BigInteger.valueOf(0));
                                                                                         if (playSummary) System.out.println("    Current worry level is " + (testResult ? "" : "not ") + "divisible by " + monkey.getTestDivider() + ".");
                        String nextMonkeyName = testResult ? monkey.getPositiveOutcomeMonkeyName() : monkey.getNegativeOutcomeMonkeyName();
                        monkey.throwItem(monkeys.get(nextMonkeyName), worryLevel);       if (playSummary) System.out.println("    Item with worry level " + worryLevel + " is thrown to monkey " + nextMonkeyName + ".");
                    } catch (NoSuchElementException e) {
                        break;
                    }
                }
            }
            if (roundSummary) {
                System.out.println("After round " + round + ", the monkeys are holding items with these worry levels:");
                for (Monkey monkey : monkeys.values())
                    System.out.println("Monkey " + monkey.getName() + ": " + monkey.getHoldingItems());
            }
        }
        if (gameSummary)
            for (Monkey monkey : monkeys.values())
                System.out.println("Monkey " + monkey.getName() + " inspected items " + monkey.getInspectionCount() + " times.");
    }

    public long calculateCommonPrime() {
        return monkeys.values().stream().mapToLong(Monkey::getTestDivider).reduce(1, (a, b) -> a * b);
    }

    public void setWorryDivider(boolean worryDivider) {
        this.worryDivider = worryDivider;
    }

    public void printPlaySummary() {
        this.playSummary = true;
    }

    public void printRoundSummary() {
        this.roundSummary = true;
    }

    public void printGameSummary() {
        this.gameSummary = true;
    }

    public String getMonkeyBusiness() {
        long[] topMonkeys = new long[2];
        Collection<Monkey> group = monkeys.values();
        for (int i = 0; i < topMonkeys.length; i++) {
            Monkey highest = Collections.max(group, Comparator.comparing(Monkey::getInspectionCount));
            topMonkeys[i] = highest.getInspectionCount();
            group.remove(highest);
        }
        return BigInteger.valueOf(topMonkeys[0]).multiply(BigInteger.valueOf(topMonkeys[1])).toString();
    }

    private BigInteger decodeWorryFactor(Object worryFactor, BigInteger itemValue) {
        BigInteger factor = new BigInteger("0");
        if (worryFactor instanceof Integer) {
            factor = new BigInteger(String.valueOf(worryFactor));
        } else if (worryFactor instanceof String) {
            if (worryFactor.equals("old"))
                factor = itemValue;
        }
        return factor;
    }

}
