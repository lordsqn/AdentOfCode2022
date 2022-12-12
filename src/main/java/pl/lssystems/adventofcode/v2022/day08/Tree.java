package pl.lssystems.adventofcode.v2022.day08;

import java.util.HashMap;
import java.util.Map;

public class Tree {
    private final int x;
    private final int y;
    private final int height;

    private final Map<Direction, Boolean> visibility = new HashMap<>();
    private final Map<Direction, Integer> viewDistance = new HashMap<>();

    protected Tree(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisibleFromAnySide() {
        return visibility.values().stream().anyMatch(b -> b);
    }

    public void checkVisibility(Direction direction, int[] lineOfSight) {
        boolean isHighest = true;
        for (int i = 0; i < lineOfSight.length; i++)
            if (lineOfSight[i] >= getHeight()) {
                isHighest = false;
                this.viewDistance.put(direction, i+1);
                break;
            }

        if (!this.viewDistance.containsKey(direction))
            this.viewDistance.put(direction, lineOfSight.length);

        this.visibility.put(direction, isHighest);
    }

    public int getViewDistance(Direction direction) {
        return viewDistance.get(direction);
    }

    @Override
    public String toString() {
        return "Tree at x:" + getX() +",y:" + getY() +" height:" + getHeight() +" visibility=" + visibility + " viewDistance=" + viewDistance;
    }
}
