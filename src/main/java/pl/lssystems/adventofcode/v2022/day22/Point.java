package pl.lssystems.adventofcode.v2022.day22;

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
    public static final Point ORIGIN = new Point();

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