package pl.lssystems.adventofcode.v2022.day19;

import java.util.Objects;

public class Resources {
    private final int ore;
    private final int clay;
    private final int obsidian;
    private final int geode;

    public Resources() {
        this(0, 0, 0, 0);
    }

    public Resources(int ore, int clay, int obsidian, int geode) {
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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Resources resource = (Resources) o;
        return ore == resource.ore && clay == resource.clay && obsidian == resource.obsidian && geode == resource.geode;
    }

    @Override public int hashCode() {
        return Objects.hash(ore, clay, obsidian, geode);
    }

    @Override public String toString() {
        return "Resource{" +
                "ore=" + ore +
                ", clay=" + clay +
                ", obsidian=" + obsidian +
                ", geode=" + geode +
                '}';
    }

}
