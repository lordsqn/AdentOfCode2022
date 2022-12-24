package pl.lssystems.adventofcode.v2022.day23;

import java.util.Iterator;

public class Point {
    private final int x;
    private final int y;

    public static final Point ARRAY_UP    = new Point(0, -1);
    public static final Point ARRAY_DOWN  = new Point(0, 1);
    public static final Point ARRAY_LEFT  = new Point(-1, 0);
    public static final Point ARRAY_RIGHT = new Point(1, 0);

    public static final Point UP     = new Point(0, 1);
    public static final Point DOWN   = new Point(0, -1);
    public static final Point LEFT   = new Point(-1, 0);
    public static final Point RIGHT  = new Point(1, 0);

    public Point() {
        this(0, 0);
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point add(final Point other) {
        return new Point(x + other.x, y + other.y);
    }

    public final Iterable<Point> getAdjacentIterable() {
        return () -> new Iterator<Point>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < 8;
            }

            @Override
            public Point next() {
                switch (index++) {
                    case 0: return add(UP).add(LEFT);
                    case 1: return add(UP);
                    case 2: return add(UP).add(RIGHT);
                    case 3: return add(LEFT);
                    case 4: return add(RIGHT);
                    case 5: return add(DOWN).add(LEFT);
                    case 6: return add(DOWN);
                    case 7: return add(DOWN).add(RIGHT);
                }
                return null;
            }
        };
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Point point = (Point) obj;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

}