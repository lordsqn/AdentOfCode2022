package pl.lssystems.adventofcode.v2022.day22;

public enum Turn {
    LEFT, RIGHT, FULL, NONE;

    static Turn fromChar(char charAt) {
        switch (charAt) {
            case 'L': return LEFT;
            case 'R': return RIGHT;
            case 'F': return FULL;
            case 'N': return NONE;
        }
        return NONE;
    }

}
