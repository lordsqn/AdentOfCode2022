package pl.lssystems.adventofcode.v2022.day19;

import java.util.Objects;

public class Robots {
    private final int ore;
    private final int clay;
    private final int obsidian;
    private final int geode;

    public Robots() {
        this(1, 0, 0, 0);
    }

    public Robots(int ore, int clay, int obsidian, int geode) {
        this.ore = ore;
        this.clay = clay;
        this.obsidian = obsidian;
        this.geode = geode;
    }

    public int getOre() {
        return ore;
    }

    public int getClay() {
        return clay;
    }

    public int getObsidian() {
        return obsidian;
    }

    public int getGeode() {
        return geode;
    }

    Robots addOreRobot() {
        return new Robots(ore + 1, clay, obsidian, geode);
    }

    Robots addClayRobot() {
        return new Robots(ore, clay + 1, obsidian, geode);
    }

    Robots addObsidianRobot() {
        return new Robots(ore, clay, obsidian + 1, geode);
    }

    Robots addGeodeRobot() {
        return new Robots(ore, clay, obsidian, geode + 1);
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Robots robots = (Robots) o;
        return ore == robots.ore && clay == robots.clay && obsidian == robots.obsidian && geode == robots.geode;
    }

    @Override public int hashCode() {
        return Objects.hash(ore, clay, obsidian, geode);
    }

    @Override public String toString() {
        return "Robots{" +
                "ore=" + ore +
                ", clay=" + clay +
                ", obsidian=" + obsidian +
                ", geode=" + geode +
                '}';
    }

}
