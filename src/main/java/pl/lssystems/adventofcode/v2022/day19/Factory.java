package pl.lssystems.adventofcode.v2022.day19;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Factory {
    private final List<Blueprint> blueprints = new ArrayList<>();

    public void addBlueprint(int id, int oreRobotOreCost, int clayRobotOreCost, int obsidianRobotOreCost, int obsidianRobotClayCost, int geodeRobotOreCost, int geodeRobotObsidianCost) {
        blueprints.add(new Blueprint(id, oreRobotOreCost, clayRobotOreCost, obsidianRobotOreCost, obsidianRobotClayCost, geodeRobotOreCost, geodeRobotObsidianCost));
    }

    public List<Blueprint> getBlueprints() {
        return blueprints;
    }

    public int applyBlueprint(final Blueprint bp, final int minutes) {
        ArrayDeque<State> stateQueue = new ArrayDeque<>(Collections.singletonList(new State(minutes)));
        Set<State> stateSeen  = new HashSet<>();

        int bestGeodes = 0;

        while (!stateQueue.isEmpty()) {
            State state = stateQueue.pop();
            bestGeodes = Math.max(bestGeodes, state.getResources().getGeode());
            int maxPossibleGeodes = state.getResources().getGeode() + state.getRobots().getGeode() * state.getMinute() + triangular(state.getMinute());

            if (state.getMinute() == 0 || maxPossibleGeodes <= bestGeodes)
                continue;

            int oreRobots      = Math.min(state.getRobots().getOre(), bp.maxOreReq());
            int clayRobots     = Math.min(state.getRobots().getClay(), bp.maxClayReq());
            int obsidianRobots = Math.min(state.getRobots().getObsidian(), bp.maxObsidianReq());
            int maxOreReq      = state.getMinute() * bp.maxOreReq() - oreRobots * (state.getMinute() - 1);
            int maxClayReq     = state.getMinute() * bp.maxClayReq() - clayRobots * (state.getMinute() - 1);
            int maxObsidianReq = state.getMinute() * bp.maxObsidianReq() - obsidianRobots * (state.getMinute() - 1);

            State flattenState = new State(state.getMinute(),
                    new Resources(Math.min(state.getResources().getOre(), maxOreReq),
                            Math.min(state.getResources().getClay(), maxClayReq),
                            Math.min(state.getResources().getObsidian(), maxObsidianReq),
                            state.getResources().getGeode()
                    ),
                    new Robots(oreRobots, clayRobots, obsidianRobots, state.getRobots().getGeode())
            );

            if (stateSeen.contains(flattenState))
                continue;

            stateSeen.add(flattenState);

            State nextState = flattenState.getNext();

            stateQueue.push(nextState);

            if (flattenState.getResources().getOre() >= bp.getOreRobotOreCost())
                stateQueue.push(nextState.buyOreRobot(bp));

            if (flattenState.getResources().getOre() >= bp.getClayRobotOreCost())
                stateQueue.push(nextState.buyClayRobot(bp));

            if (flattenState.getResources().getOre() >= bp.getObsidianRobotOreCost() && flattenState.getResources().getClay() >= bp.getObsidianRobotClayCost())
                stateQueue.push(nextState.buyObsidianRobot(bp));

            if (flattenState.getResources().getOre() >= bp.getGeodeRobotOreCost() && flattenState.getResources().getObsidian() >= bp.getGeodeRobotObsidianCost())
                stateQueue.push(nextState.buyGeodeRobot(bp));
        }

        return bestGeodes;
    }

    private int triangular(final int n) {
        return n * (n - 1) / 2;
    }

}
