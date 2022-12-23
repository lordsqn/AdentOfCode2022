package pl.lssystems.adventofcode.v2022.day20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Groove {
    private List<Number> original;
    private List<Number> numbers;

    public Groove(List<String> lines) {
        List<Long> input = lines.stream().mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        this.original = IntStream.range(0, input.size()).mapToObj(i -> new Number(input.get(i), i)).collect(Collectors.toList());
        this.numbers = new ArrayList<>(original);
    }

    public void multiplyByEncryptionKey(long encryptionKey) {
        this.original = original.stream().map(number -> new Number(number.getValue() * encryptionKey, number.getIndex())).collect(Collectors.toList());
        this.numbers = new ArrayList<>(original);
    }

    public void mix() {
        for (Number number : original) {
            int currentIndex = numbers.indexOf(number);
            long nextIndex = number.getValue() + currentIndex;

            numbers.remove(currentIndex);

            numbers.add((int) Math.floorMod(nextIndex, numbers.size()), number);
        }
    }

    public long getCoordinates() {
        int indexOfZero = numbers.indexOf(numbers.stream()
                .filter(num -> num.getValue() == 0)
                .findFirst()
                .orElseThrow(RuntimeException::new));

        return numbers.get((indexOfZero + 1000) % numbers.size()).getValue() +
                numbers.get((indexOfZero + 2000) % numbers.size()).getValue() +
                numbers.get((indexOfZero + 3000) % numbers.size()).getValue();
    }

}
