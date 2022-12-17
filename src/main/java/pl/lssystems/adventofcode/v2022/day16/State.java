package pl.lssystems.adventofcode.v2022.day16;

import java.util.HashSet;
import java.util.Set;

public class State {
    private final int minutes;
    private final String currentValveId;
    private final long currentPressure;
    private final Set<String> visited;

    public State(int minutes, String currentValveId, long currentPressure, Set<String> visited) {
        this.minutes = minutes;
        this.currentValveId = currentValveId;
        this.currentPressure = currentPressure;
        this.visited = visited;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getCurrentValveId() {
        return currentValveId;
    }

    public long getCurrentPressure() {
        return currentPressure;
    }

    public Set<String> getVisited() {
        return visited;
    }

    public State moveToAndOpen(String moveTo, int costs, long targetFlowrate) {
        Set<String> visited = new HashSet<>(this.visited);
        visited.add(currentValveId);
        return new State(minutes - costs - 1, moveTo, currentPressure + targetFlowrate * (minutes - costs - 1), visited);
    }

}