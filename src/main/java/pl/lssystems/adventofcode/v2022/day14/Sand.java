package pl.lssystems.adventofcode.v2022.day14;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Sand extends Element {
    private boolean stable;

    public Sand(int x, int y) {
        super(x, y);
    }

    public boolean isStable() {
        return stable;
    }

    public boolean fall(Map<String, Element> elements, int plainHeight) {
        if (elements.containsKey(getX() + "," + (getY() + 1))) {
            if (elements.containsKey((getX() - 1) + "," + (getY() + 1))) { // miejsce z lewej
                if (elements.containsKey((getX() + 1) + "," + (getY() + 1))) { //miejsce z prawej
                    stable = true;
                    return false;
                } else {
                    x++; y++;
                    return true;
                }
            } else {
                x--; y++;
                return true;
            }
        } else {
            if (getY() + 1 > plainHeight - 1) {
                return false;
            } else {
                y++;
                return true;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sand sand = (Sand) o;
        return x == sand.x && y == sand.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
