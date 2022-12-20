package pl.lssystems.adventofcode.v2022.day17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CaveTetris {
    private final Set<Position> rocksInCave = new HashSet<>();
    private final boolean[] jetPattern;
    private final Map<Set<Position>, Position> cache = new HashMap<>();
    private boolean cycleFound = false;
    private long rockCount = 0;
    private long heightFromCycleRepeat = 0;
    private final List<Set<Position>> rocks = new ArrayList<Set<Position>>() {{
        add(new HashSet<Position>() {{ // horizontal line
            add(new Position(0, 0));
            add(new Position(1, 0));
            add(new Position(2, 0));
            add(new Position(3, 0));
        }});
        add(new HashSet<Position>() {{ // plus
            add(new Position(1, 0));
            add(new Position(0, 1));
            add(new Position(1, 1));
            add(new Position(2, 1));
            add(new Position(1, 2));
        }});
        add(new HashSet<Position>() {{ // reverse L
            add(new Position(0, 0));
            add(new Position(1, 0));
            add(new Position(2, 0));
            add(new Position(2, 1));
            add(new Position(2, 2));
        }});
        add(new HashSet<Position>() {{ // vertical line
            add(new Position(0, 0));
            add(new Position(0, 1));
            add(new Position(0, 2));
            add(new Position(0, 3));
        }});
        add(new HashSet<Position>() {{ // square
            add(new Position(0, 0));
            add(new Position(0, 1));
            add(new Position(1, 0));
            add(new Position(1, 1));
        }});
    }};

    public CaveTetris(String moves) {
        prepareCave();
        this.jetPattern = compilePattern(moves);
    }

    private void prepareCave() {
        for (int i = 0; i < 7; i++)
            rocksInCave.add(new Position(i, -1));
    }

    private boolean[] compilePattern(String moves) {
        boolean[] jetPattern = new boolean[moves.length()];
        IntStream.range(0, moves.length()).forEach(i -> jetPattern[i] = moves.charAt(i) == '>');
        return jetPattern;
    }

    public long run(long rockTarget) {
        int jetCounter = 0;

        while (rockCount < rockTarget) {
            Set<Position> now = new HashSet<>(rocks.get((int) (rockCount % 5)));
            long maxY = rocksInCave.stream().map(Position::getY).max(Long::compare).orElse(-1L);
            now = now.stream().map(x -> x.add(2, maxY + 4)).collect(Collectors.toSet());

            boolean solutionFound = false;
            while (!solutionFound) {
                now = jetPattern[jetCounter % jetPattern.length] ? moveRight(now) : moveLeft(now);
                jetCounter++;
                solutionFound = moveDown(now, rockTarget);
                now = now.stream().map(Direction.DOWN::translate).collect(Collectors.toSet());
            }

            rockCount++;
        }

        return (rocksInCave.stream().map(Position::getY).max(Long::compare).orElseThrow(RuntimeException::new) + heightFromCycleRepeat + 1);
    }

    private boolean moveDown(Set<Position> state, long rockTarget) {
        for (Position c : state) {
            if (rocksInCave.contains(Direction.DOWN.translate(c))) {
                rocksInCave.addAll(state);
                long curHeight = rocksInCave.stream().map(Position::getY).max(Long::compare).orElse(-1L);
                long currMaxY = rocksInCave.stream().map(Position::getY).max(Long::compare).orElse(-1L);
                Set<Position> cacheKey = rocksInCave.stream()
                        .filter(x -> currMaxY - x.getY() <= 30)
                        .map(x -> new Position(x.getX(), currMaxY - x.getY()))
                        .collect(Collectors.toSet());
                if (!cycleFound && cache.containsKey(cacheKey)) {
                    Position info = cache.get(cacheKey);
                    long oldTime = info.getX();
                    long oldHeight = info.getY();
                    long cycleLength = (rockCount - oldTime);
                    long cycleHeightChange = curHeight - oldHeight;
                    long numCycles = (rockTarget - rockCount) / cycleLength;
                    heightFromCycleRepeat = cycleHeightChange * numCycles;
                    rockCount += numCycles * cycleLength;
                    cycleFound = true;
                } else {
                    Position info = new Position(rockCount, curHeight);
                    cache.put(cacheKey, info);
                }
                return true;
            }
        }
        return false;
    }

    private Set<Position> moveLeft(Set<Position> state) {
        long lowestX = state.stream().map(Position::getX).min(Long::compare).orElse(-1L);
        Set<Position> tentativeLeft = state.stream().map(Direction.LEFT::translate)
                .collect(Collectors.toSet());
        return lowestX > 0 && tentativeLeft.stream().noneMatch(rocksInCave::contains) ? tentativeLeft : state;
    }

    private Set<Position> moveRight(Set<Position> state) {
        long highestX = state.stream().map(Position::getX).max(Long::compare).orElse(-1L);
        Set<Position> tentativeRight = state.stream().map(Direction.RIGHT::translate)
                .collect(Collectors.toSet());
        return highestX < 6 && tentativeRight.stream().noneMatch(rocksInCave::contains) ?  tentativeRight : state;
    }

}