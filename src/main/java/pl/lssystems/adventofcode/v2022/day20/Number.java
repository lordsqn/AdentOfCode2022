package pl.lssystems.adventofcode.v2022.day20;

import java.util.Objects;

public class Number {
    private final long value;
    private final int index;

    public Number(long value, int index) {
        this.value = value;
        this.index = index;
    }

    public long getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Number that = (Number) o;
        return value == that.value && index == that.index;
    }

    @Override public int hashCode() {
        return Objects.hash(value, index);
    }

    @Override public String toString() {
        return "IndexedNumber{" +
                "value=" + value +
                ", index=" + index +
                '}';
    }

}
