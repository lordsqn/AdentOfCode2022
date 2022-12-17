package pl.lssystems.adventofcode.v2022.day16;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class Volcano {
    private final List<Valve> valves = new ArrayList<>();
    private Map<String, Valve> filteredValves;
    private Map<Route, Integer> costs;

    public void addValve(Valve valve) {
        valves.add(valve);
    }

    public void calculateCosts() {
        this.filteredValves = filterValves(valves);
        this.costs = calculateCosts(valves);
    }

    private Map<String, Valve> filterValves(List<Valve> valves) {
        return valves.stream().filter(valve -> valve.getFlow() > 0 || valve.getId().equals("AA")).collect(toMap(Valve::getId, v -> v));
    }

    private Map<Route, Integer> calculateCosts(List<Valve> valves) {
        Map<Route, Integer> costs = new HashMap<>();

        for (Valve valve : valves) {
            valve.getRoutes().forEach(target -> costs.put(new Route(valve.getId(), target), 1));
        }

        for (Valve via : valves) {
            for (Valve from : valves) {
                for (Valve to : valves) {
                    if (from == to || from == via || to == via) {
                        continue;
                    }

                    Route part1 = new Route(from.getId(), via.getId());
                    Route part2 = new Route(via.getId(), to.getId());

                    if (costs.containsKey(part1) && costs.containsKey(part2)) {
                        Route direct = new Route(from.getId(), to.getId());

                        int viaCosts = costs.get(part1) + costs.get(part2);
                        if (viaCosts < costs.getOrDefault(direct, Integer.MAX_VALUE)) {
                            costs.put(direct, viaCosts);
                        }
                    }
                }
            }
        }

        List<String> irrelevantValves = valves.stream().filter(valve -> valve.getFlow() == 0 && !valve.getId().equals("AA")).map(Valve::getId).collect(Collectors.toList());
        List<Route> irrelevantRoutes = costs.keySet().stream().filter(route -> irrelevantValves.contains(route.getFrom()) || irrelevantValves.contains(route.getTo())).collect(Collectors.toList());
        irrelevantRoutes.forEach(costs::remove);

        return costs;
    }

    public long maximumPressureRelease() {
        return maximumPressureRelease(new State(30, "AA", 0, new HashSet<>())).getCurrentPressure();
    }

    private State maximumPressureRelease(State initialState) {
        LinkedList<State> todo = new LinkedList<>();
        todo.add(initialState);

        State max = null;

        while (!todo.isEmpty()) {
            State state = todo.poll();

            List<State> newStates = costs.keySet().stream().filter(route -> route.getFrom().equals(state.getCurrentValveId()))
                    .filter(route -> !state.getVisited().contains(route.getTo()))
                    .filter(route -> costs.get(route) + 1 <= state.getMinutes())
                    .map(route -> state.moveToAndOpen(route.getTo(), costs.get(route), filteredValves.get(route.getTo()).getFlow()))
                    .collect(Collectors.toList());

            if (newStates.size() == 0) {
                if (max == null || state.getCurrentPressure() > max.getCurrentPressure()) {
                    max = state;
                }
            }
            todo.addAll(newStates);
        }

        return max;
    }

    public long maximumPressureReleaseWithElephantHelp() {
        HashSet<String> targets = new HashSet<>(filteredValves.keySet());
        targets.remove("AA");

        return StreamSupport
                .stream(Spliterators
                        .spliteratorUnknownSize(new Combination<>(targets, targets.size() / 2), 0), true)
                .mapToLong(valvesToVisit -> {
                    Set<String> otherValvesToVisit = targets.stream().filter(x -> !valvesToVisit.contains(x)).collect(toSet());
                    State myValves = maximumPressureRelease(new State(26, "AA", 0, new HashSet<>(valvesToVisit)));
                    State elephantValves = maximumPressureRelease(new State(26, "AA", 0, new HashSet<>(otherValvesToVisit)));
                    return myValves.getCurrentPressure() + elephantValves.getCurrentPressure();
                }).max().orElse(0);
    }

}