package pl.lssystems.adventofcode.v2022.day22;

public enum Tile {
    EMPTY, OPEN, WALL;

    static Tile fromChar(final char charAt) {
        switch (charAt) {
            case '.': return OPEN;
            case '#': return WALL;
        }
        return EMPTY;
    }

    @Override
    public String toString() {
        switch (this) {
            case EMPTY: return " ";
            case OPEN: return ".";
            case WALL: return "#";
        }
        return " ";
    }

}
