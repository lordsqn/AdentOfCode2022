package pl.lssystems.adventofcode.v2022.day19;

import java.util.Objects;

public class State {
    private final int minute;
    private final Resources resources;
    private final Robots robots;
    
    public State(int minute) {
        this(minute, new Resources(), new Robots());
    }

    public State(int minute, Resources resources, Robots robots) {
        this.minute = minute;
        this.resources = resources;
        this.robots = robots;
    }

    public int getMinute() {
        return minute;
    }

    public Resources getResources() {
        return resources;
    }

    public Robots getRobots() {
        return robots;
    }

    public State getNext() {
        return new State(minute - 1, new Resources(resources.getOre() + robots.getOre(),
                resources.getClay() + robots.getClay(),
                resources.getObsidian() + robots.getObsidian(),
                resources.getGeode() + robots.getGeode()
        ), robots);
    }

    public State buyOreRobot(final Blueprint blueprint) {
        return new State(minute, new Resources(resources.getOre() - blueprint.getOreRobotOreCost(),
                resources.getClay(),
                resources.getObsidian(),
                resources.getGeode()
        ), robots.addOreRobot());
    }

    public State buyClayRobot(final Blueprint blueprint) {
        return new State(minute, new Resources(resources.getOre() - blueprint.getClayRobotOreCost(),
                resources.getClay(),
                resources.getObsidian(),
                resources.getGeode()
        ), robots.addClayRobot());
    }

    public State buyObsidianRobot(final Blueprint blueprint) {
        return new State(minute, new Resources(resources.getOre() - blueprint.getObsidianRobotOreCost(),
                resources.getClay() - blueprint.getObsidianRobotClayCost(),
                resources.getObsidian(),
                resources.getGeode()
        ), robots.addObsidianRobot());
    }

    public State buyGeodeRobot(final Blueprint blueprint) {
        return new State(minute, new Resources(resources.getOre() - blueprint.getGeodeRobotOreCost(),
                resources.getClay(),
                resources.getObsidian() - blueprint.getGeodeRobotObsidianCost(),
                resources.getGeode()
        ), robots.addGeodeRobot());
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        State state = (State) o;
        return minute == state.minute && Objects.equals(resources, state.resources) && Objects.equals(
                robots, state.robots);
    }

    @Override public int hashCode() {
        return Objects.hash(minute, resources, robots);
    }

    @Override public String toString() {
        return "State{" +
                "minute=" + minute +
                ", resources=" + resources +
                ", robots=" + robots +
                '}';
    }

}
