package pl.lssystems.adventofcode.v2022.day19;

import java.util.Objects;

public class Blueprint {
    private final int id;
    private final int oreRobotOreCost;
    private final int clayRobotOreCost;
    private final int obsidianRobotOreCost;
    private final int obsidianRobotClayCost;
    private final int geodeRobotOreCost;
    private final int geodeRobotObsidianCost;

    public Blueprint(int id, int oreRobotOreCost, int clayRobotOreCost, int obsidianRobotOreCost, int obsidianRobotClayCost, int geodeRobotOreCost, int geodeRobotObsidianCost) {
        this.id = id;
        this.oreRobotOreCost = oreRobotOreCost;
        this.clayRobotOreCost = clayRobotOreCost;
        this.obsidianRobotOreCost = obsidianRobotOreCost;
        this.obsidianRobotClayCost = obsidianRobotClayCost;
        this.geodeRobotOreCost = geodeRobotOreCost;
        this.geodeRobotObsidianCost = geodeRobotObsidianCost;
    }

    public int getId() {
        return id;
    }

    public int getOreRobotOreCost() {
        return oreRobotOreCost;
    }

    public int getClayRobotOreCost() {
        return clayRobotOreCost;
    }

    public int getObsidianRobotOreCost() {
        return obsidianRobotOreCost;
    }

    public int getObsidianRobotClayCost() {
        return obsidianRobotClayCost;
    }

    public int getGeodeRobotOreCost() {
        return geodeRobotOreCost;
    }

    public int getGeodeRobotObsidianCost() {
        return geodeRobotObsidianCost;
    }

    public int maxOreReq() {
        return Math.max(oreRobotOreCost, Math.max(clayRobotOreCost, Math.max(obsidianRobotOreCost, geodeRobotOreCost)));
    }

    public int maxClayReq() {
        return obsidianRobotClayCost;
    }

    public int maxObsidianReq() {
        return geodeRobotObsidianCost;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Blueprint blueprint = (Blueprint) o;
        return id == blueprint.id && oreRobotOreCost == blueprint.oreRobotOreCost
                && clayRobotOreCost == blueprint.clayRobotOreCost
                && obsidianRobotOreCost == blueprint.obsidianRobotOreCost
                && obsidianRobotClayCost == blueprint.obsidianRobotClayCost
                && geodeRobotOreCost == blueprint.geodeRobotOreCost
                && geodeRobotObsidianCost == blueprint.geodeRobotObsidianCost;
    }

    @Override public int hashCode() {
        return Objects.hash(id, oreRobotOreCost, clayRobotOreCost, obsidianRobotOreCost, obsidianRobotClayCost,
                geodeRobotOreCost, geodeRobotObsidianCost);
    }

    @Override public String toString() {
        return "Blueprint{" +
                "id=" + id +
                ", oreRobotOreCost=" + oreRobotOreCost +
                ", clayRobotOreCost=" + clayRobotOreCost +
                ", obsidianRobotOreCost=" + obsidianRobotOreCost +
                ", obsidianRobotClayCost=" + obsidianRobotClayCost +
                ", geodeRobotOreCost=" + geodeRobotOreCost +
                ", geodeRobotObsidianCost=" + geodeRobotObsidianCost +
                '}';
    }

}
