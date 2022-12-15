package pl.lssystems.adventofcode.v2022.day14;

import java.util.Objects;

public abstract class Element {
    protected int x;
    protected int y;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return x == element.x && y == element.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
