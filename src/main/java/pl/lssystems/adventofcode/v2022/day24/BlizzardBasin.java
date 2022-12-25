package pl.lssystems.adventofcode.v2022.day24;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlizzardBasin {
    private final BlizzardMap blizzardMap;
    private final Point start;
    private final Point end;

    public BlizzardBasin(List<String> input) {
        List<Point> leftBlizzards  = new ArrayList<>();
        List<Point> rightBlizzards = new ArrayList<>();
        List<Point> upBlizzards    = new ArrayList<>();
        List<Point> downBlizzards  = new ArrayList<>();
        int xMax = input.get(0).length() - 1;
        int yMax = input.size() - 1;

        for (int y = 1; y < yMax; y++) {
            String line = input.get(y);
            for (int x = 1; x < xMax; x++) {
                switch (line.charAt(x)) {
                    case '>': rightBlizzards.add(new Point(x, y)); break;
                    case '<': leftBlizzards.add(new Point(x, y)); break;
                    case '^': upBlizzards.add(new Point(x, y)); break;
                    case 'v': downBlizzards.add(new Point(x, y)); break;
                }
            }
        }

        blizzardMap = new BlizzardMap(leftBlizzards, rightBlizzards, upBlizzards, downBlizzards, xMax, yMax);

        start = new Point(1, 0);
        end   = new Point(blizzardMap.getXMax() - 1, blizzardMap.getYMax());
    }

    public BlizzardMap doMove(BlizzardMap blizzardMap) {
        List<Point> newLeftBlizzards  = new ArrayList<>();
        List<Point> newRightBlizzards = new ArrayList<>();
        List<Point> newUpBlizzards    = new ArrayList<>();
        List<Point> newDownBlizzards  = new ArrayList<>();

        for (Point leftBlizzard : blizzardMap.getLeftBlizzards()) {
            Point next = leftBlizzard.add(Point.ARRAY_LEFT);
            if (next.getX() == 0)
                next = new Point(blizzardMap.getXMax() - 1, leftBlizzard.getY());

            newLeftBlizzards.add(next);
        }
        for (Point rightBlizzard : blizzardMap.getRightBlizzards()) {
            Point next = rightBlizzard.add(Point.ARRAY_RIGHT);
            if (next.getX() == blizzardMap.getXMax())
                next = new Point(1, rightBlizzard.getY());

            newRightBlizzards.add(next);
        }
        for (Point upBlizzard : blizzardMap.getUpBlizzards()) {
            Point next = upBlizzard.add(Point.ARRAY_UP);
            if (next.getY() == 0)
                next = new Point(upBlizzard.getX(), blizzardMap.getYMax() - 1);

            newUpBlizzards.add(next);
        }
        for (Point downBlizzard : blizzardMap.getDownBlizzards()) {
            Point next = downBlizzard.add(Point.ARRAY_DOWN);
            if (next.getY() == blizzardMap.getYMax())
                next = new Point(downBlizzard.getX(), 1);

            newDownBlizzards.add(next);
        }

        return new BlizzardMap(newLeftBlizzards, newRightBlizzards, newUpBlizzards, newDownBlizzards, blizzardMap.getXMax(), blizzardMap.getYMax());
    }

    public int run(int stage) {
        BlizzardMap currentBlizzardMap = blizzardMap;
        Set<Position> positions = new HashSet<>();
        positions.add(new Position(start, stage));
        int steps = 0;

        while (true) {
            steps++;
            currentBlizzardMap = doMove(currentBlizzardMap);

            Set<Point> blizzards = currentBlizzardMap.getAllBlizzards();
            Set<Position> nextPositions = new HashSet<>();

            for (Position position : positions) {
                List<Point> neighbors = new ArrayList<>(position.getPoint().getNeighbours());
                neighbors.add(position.getPoint());

                for (Point neighbor : neighbors) {
                    if (neighbor.equals(start) || neighbor.equals(end)) {
                        nextPositions.add(new Position(neighbor, position.getStage()));
                        continue;
                    }
                    if (neighbor.getX() <= 0 ||
                            neighbor.getX() >= blizzardMap.getXMax() ||
                            neighbor.getY() <= 0 ||
                            neighbor.getY() >= blizzardMap.getYMax() ||
                            blizzards.contains(neighbor)) {
                        continue;
                    }
                    nextPositions.add(new Position(neighbor, position.getStage()));
                }
            }

            positions = new HashSet<>();

            for (Position position : nextPositions) {
                if (position.getStage() == 2 && position.getPoint().equals(end)) {
                    positions.clear();
                    positions.add(new Position(end, 1));
                    break;
                }
                if (position.getStage() == 1 && position.getPoint().equals(start)) {
                    positions.clear();
                    positions.add(new Position(start, 0));
                    break;
                }
                if (position.getStage() == 0 && position.getPoint().equals(end))
                    return steps;

                positions.add(position);
            }
        }
    }

}
