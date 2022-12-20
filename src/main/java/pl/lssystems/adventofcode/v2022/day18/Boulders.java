package pl.lssystems.adventofcode.v2022.day18;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Boulders {
    private final Set<Position> droplets = new HashSet<>();

    public void addDroplet(int x, int y, int z) {
        droplets.add(new Position(x, y, z));
    }

    public long dropletSurfaceArea() {
        return droplets.stream().flatMap(s -> Arrays.stream(s.createNeighbours())).filter(loc -> !droplets.contains(loc)).count();
    }

    public int exteriorSurfaceArea() {
        Position minimum = droplets.stream().min(Comparator.comparingInt(o -> o.getX() + o.getY() + o.getZ())).orElseThrow(RuntimeException::new);
        Position firstAir = Arrays.stream(minimum.createNeighbours()).filter(s -> !droplets.contains(s)).findAny().orElseThrow(RuntimeException::new);

        LinkedList<Position> queue = new LinkedList<>();
        queue.add(firstAir);
        HashSet<Position> airBlocks = new HashSet<>();
        while (!queue.isEmpty()) {
            Position cur = queue.poll();
            airBlocks.add(cur);

            for (Position neighbor : cur.createNeighbours()) {
                if (airBlocks.contains(neighbor) || droplets.contains(neighbor) || queue.contains(neighbor) || shortestToOthers(droplets, neighbor) > 2)
                    continue;
                queue.add(neighbor);
            }
        }

        int surfaceArea = 0;
        for (Position c : airBlocks)
            for (Position d : c.createNeighbours())
                if (droplets.contains(d))
                    surfaceArea++;

        return surfaceArea;
    }

    private int shortestToOthers(Set<Position> droplets, Position pos) {
        return droplets.stream().map(x -> x.calculateDistance(pos)).min(Integer::compare).orElseThrow(RuntimeException::new);
    }

}
