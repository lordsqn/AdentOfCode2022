package pl.lssystems.adventofcode.v2022.day12;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Grid {
    private final List<Point> directions = new ArrayList<Point>() {{
        add(new Point(0, 1));
        add(new Point(0, -1));
        add(new Point(1, 0));
        add(new Point(-1, 0));
    }};

    char[][] grid;

    Point startPosition = null;
    Point targetPosition = null;

    boolean setPosition = true;

    PriorityQueue<PointTrack> queue = new PriorityQueue<>(Comparator.comparingInt(PointTrack::getSteps));

    public Grid(char[][] grid) {
        this.grid = grid;
        findStartAndTargetPositions();
    }

    private void findStartAndTargetPositions() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'S') {
                    startPosition = new Point(x, y);
                    grid[y][x] = 'a';
                }
                if (grid[y][x] == 'E') {
                    targetPosition = new Point(x, y);
                    queue.add(new PointTrack(targetPosition, 0));
                    grid[y][x] = 'z';
                }
            }
        }
    }

    public void setPosition(boolean setPosition) {
        this.setPosition = setPosition;
    }

    public int findShortestPath() {
        Set<Point> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            PointTrack state = queue.poll();

            if (visited.contains(state.getPosition()))
                continue;

            if (setPosition ? state.getPosition().equals(startPosition) : grid[state.getPosition().y][state.getPosition().x] == 'a')
                return state.getSteps();

            visited.add(state.getPosition());

            for (Point direction : directions) {
                Point next = new Point(state.getPosition().x + direction.x, state.getPosition().y + direction.y);

                if (next.x < 0 || next.x >= grid[0].length || next.y < 0 ||
                        next.y >= grid.length || visited.contains(next) ||
                        grid[next.y][next.x] < grid[state.getPosition().y][state.getPosition().x] - 1)
                    continue;

                queue.add(new PointTrack(next, state.getSteps() + 1));
            }
        }
        return -1;
    }



}
