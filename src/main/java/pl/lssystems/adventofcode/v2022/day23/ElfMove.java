package pl.lssystems.adventofcode.v2022.day23;

import java.util.Objects;

public class ElfMove {
    private final Point elf;
    private final Point next;

    public ElfMove(Point elf, Point next) {
        this.elf = elf;
        this.next = next;
    }

    public Point getElf() {
        return elf;
    }

    public Point getNext() {
        return next;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ElfMove elfMove = (ElfMove) o;
        return elf.equals(elfMove.elf) && next.equals(elfMove.next);
    }

    @Override public int hashCode() {
        return Objects.hash(elf, next);
    }

    @Override public String toString() {
        return "ElfMove{" +
                "elf=" + elf +
                ", next=" + next +
                '}';
    }

}
