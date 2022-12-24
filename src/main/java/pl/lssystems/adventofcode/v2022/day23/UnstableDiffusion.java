package pl.lssystems.adventofcode.v2022.day23;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UnstableDiffusion {
    private final Point[][] directions = {
            {Point.ARRAY_UP, Point.ARRAY_UP.add(Point.ARRAY_LEFT), Point.ARRAY_UP.add(Point.ARRAY_RIGHT)},
            {Point.ARRAY_DOWN, Point.ARRAY_DOWN.add(Point.ARRAY_LEFT), Point.ARRAY_DOWN.add(Point.ARRAY_RIGHT)},
            {Point.ARRAY_LEFT, Point.ARRAY_LEFT.add(Point.ARRAY_UP), Point.ARRAY_LEFT.add(Point.ARRAY_DOWN)},
            {Point.ARRAY_RIGHT, Point.ARRAY_RIGHT.add(Point.ARRAY_UP), Point.ARRAY_RIGHT.add(Point.ARRAY_DOWN)},
    };
    private final List<Point> elves;

    public UnstableDiffusion(char[][] input) {
        this.elves = convertToPoints(input);
    }

    private List<Point> convertToPoints(char[][] map) {
        List<Point> elves = new ArrayList<>();
        for (int y = 0; y < map.length; y++)
            for (int x = 0; x < map[y].length; x++)
                if (map[y][x] == '#')
                    elves.add(new Point(x, y));
        return elves;
    }

    public int countEmptyRectangles(int maxRounds) {
        for (int round = 0; round < maxRounds; round++)
            couldDoRound(round, elves);

        int xMin = elves.stream().mapToInt(Point::getX).min().orElse(-1);
        int xMax = elves.stream().mapToInt(Point::getX).max().orElse(-1);
        int yMin = elves.stream().mapToInt(Point::getY).min().orElse(-1);
        int yMax = elves.stream().mapToInt(Point::getY).max().orElse(-1);

        int emptyCount = 0;

        for (int y = yMin; y <= yMax; y++)
            for (int x = xMin; x <= xMax; x++)
                if (!elves.contains(new Point(x, y)))
                    emptyCount++;

        return emptyCount;
    }

    public int countRoundsWithStillElves() {
        int round = 0;
        while (couldDoRound(round++, elves)) {/* loop */}
        return round;
    }

    private boolean couldDoRound(int round, Collection<Point> elves) {
        boolean didChange = false;

        List<ElfMove> proposedMoves = new ArrayList<>();
        List<Point> elvesWithNeighbors = elves.stream().filter(elf -> {
            for (Point neighbor : elf.getAdjacentIterable())
                if (elves.contains(neighbor))
                    return true;
            return false;
        }).collect(Collectors.toList());

        for (Point elf : elvesWithNeighbors) {
            for (int i = 0; i < directions.length; i++) {
                Point[] direction = directions[(i + round) % directions.length];

                if (!elves.contains(elf.add(direction[0])) && !elves.contains(elf.add(direction[1])) && !elves.contains(elf.add(direction[2]))) {
                    proposedMoves.add(new ElfMove(elf, elf.add(direction[0])));
                    break;
                }
            }
        }

        List<Point> proposedMovesNextList = proposedMoves.stream().map(ElfMove::getNext).collect(Collectors.toList());
        for (ElfMove proposedMove : proposedMoves) {
            if (Collections.frequency(proposedMovesNextList, proposedMove.getNext()) == 1) {
                elves.remove(proposedMove.getElf());
                elves.add(proposedMove.getNext());
                didChange = true;
            }
        }

        return didChange;
    }

}
