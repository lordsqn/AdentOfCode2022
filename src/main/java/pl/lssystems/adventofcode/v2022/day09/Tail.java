package pl.lssystems.adventofcode.v2022.day09;

public class Tail implements RopeElement {
    private int x = 0;
    private int y = 0;

    private RopeElement parent;
    private final Tail child;
    private final char id;

    private static boolean trackHead = true;

    public Tail(char id) {
        this(id, null);
    }

    public Tail(char id, Tail child) {
        this.id = id;
        this.child = child;
        if (this.child != null)
            this.child.setParent(this);
        Move.addMove(Direction.UP, 0, 0); // Start position
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getId() {
        return id;
    }

    public void setParent(RopeElement parent) {
        this.parent = parent;
    }

    @Override
    public RopeElement getChild() {
        return child;
    }

    public static void setTrackHead(boolean trackHead) {
        Tail.trackHead = trackHead;
    }

    public void updatePosition(Direction direction) {
        double offsetX = (double) x - parent.getX();
        double offsetY = (double) y - parent.getY();
        int distanceX = (int) Math.sqrt(Math.pow(offsetX, 2));
        int distanceY = (int) Math.sqrt(Math.pow(offsetY, 2));
        Direction course = direction;
        if (distanceX > 1) course = offsetX > 0 ? Direction.LEFT : Direction.RIGHT;
        if (distanceY > 1) course = offsetY > 0 ? Direction.DOWN : Direction.UP;
        if (distanceX > 1 || distanceY > 1) {
            switch (course) {
                case UP: y++; break;
                case DOWN: y--; break;
                case LEFT: x--; break;
                case RIGHT: x++; break;
            }
            switch (course) {
                case UP: case DOWN:
                    if (x - parent.getX() > 0) x--;
                    if (x - parent.getX() < 0) x++;
                    break;
                case LEFT: case RIGHT:
                    if (y - parent.getY() > 0) y--;
                    if (y - parent.getY() < 0) y++;
                    break;
            }
            if (trackHead) Move.addMove(course, x, y);
            if (child == null) {
                if (!trackHead) Move.addMove(course, x, y);
                return;
            }
            child.updatePosition(course);
        }
    }

}
