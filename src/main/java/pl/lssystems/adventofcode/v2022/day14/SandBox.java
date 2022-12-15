package pl.lssystems.adventofcode.v2022.day14;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SandBox {

    private final List<Element> elements = new LinkedList<>();
    private boolean newShelf = false;

    private Map<String, Element> elementsSorted;

    public List<Element> getElements() {
        return elements;
    }

    public Map<String, Element>  getSortedTree() {
        if (elementsSorted != null) return elementsSorted;

        elementsSorted = new TreeMap<>();
        for (Element element : elements)
            elementsSorted.put(element.getX() + "," + element.getY(), element);

        return elementsSorted;
    }

    public void newShelf() {
        newShelf = true;
    }

    public void addRocks(int x, int y) {
        if (newShelf) {
            elements.add(new Rock(x, y));
            newShelf = false;
        }
        // Extrapolate
        LinkedList<Element> e = (LinkedList<Element>) elements;
        boolean forward = x > e.getLast().getX() || y > e.getLast().getY();
        int distanceX = Math.abs(x - e.getLast().getX());
        int distanceY = Math.abs(y - e.getLast().getY());

        for (int i = 0; i < distanceX; i++)
            e.add(new Rock(forward ? e.getLast().getX() + 1 : e.getLast().getX() - 1 , y));
        for (int i = 0; i < distanceY; i++)
            e.add(new Rock(x, forward ? e.getLast().getY() + 1 : e.getLast().getY() - 1));
    }

    public boolean dropSand(int x, int y, int plainHeight) {
        Sand s = new Sand(x, y);
        int moves = 0;
        while (s.fall(elementsSorted, plainHeight))
            moves++;

        if (s.isStable()) {
            elements.add(s);
            elementsSorted.put(s.getX() + "," + s.getY(), s);
        }

        return s.isStable() && moves > 0;
    }

    public int getNumberOfSands() {
        return (int) elements.stream().filter(e -> e instanceof Sand).count();
    }

}
