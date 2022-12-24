package pl.lssystems.adventofcode.v2022.day22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonkeyMap {
    int xMax;
    int yMax;
    private final Tile[][] board;
    private final List<Instruction> instructions;
    private Point position;
    private Point direction;

    public MonkeyMap(List<String> input) {
        xMax  = input.get(10).length() + 2;
        yMax  = input.size();
        board = new Tile[yMax][xMax];
        fillBoard(board, input, yMax);
        instructions = getInstructions(input, yMax);
        position  = new Point(input.get(0).indexOf('.') + 1, 1);
        direction = Point.ARRAY_RIGHT;
    }

    private void fillBoard(Tile[][] board, List<String> input, int yMax) {
        Arrays.fill(board[0], Tile.EMPTY);
        for (int y = 1; y < yMax - 1; y++) {
            Arrays.fill(board[y], Tile.EMPTY);

            for (int x = 0; x < input.get(y - 1).length(); x++)
                board[y][x + 1] = Tile.fromChar(input.get(y - 1).charAt(x));
        }
        Arrays.fill(board[yMax - 1], Tile.EMPTY);
    }


    private List<Instruction> getInstructions(final List<String> input, final int yMax) {
        Pattern instructionsPattern = Pattern.compile("(?<steps>\\d+)(?<turn>[L|R]?)");
        Matcher matcher = instructionsPattern.matcher(input.get(yMax - 1));
        List<Instruction> instructions = new ArrayList<>();

        while (matcher.find())
            instructions.add(new Instruction(
                    Integer.parseInt(matcher.group("steps")),
                    Turn.fromChar(matcher.group("turn").isEmpty() ? 'N' : matcher.group("turn").charAt(0))
            ));

        return instructions;
    }

    public void calculatePosition() {
        for (Instruction instruction : instructions) {
            for (int i = 0; i < instruction.getSteps(); i++) {
                Tile next = board[position.getY() + direction.getY()][position.getX() + direction.getX()];
                Point wrap = new Point();

                if (next == Tile.EMPTY) {
                    if (direction.equals(Point.ARRAY_RIGHT)) {
                        int x = 0;
                        while (board[position.getY()][x] == Tile.EMPTY) x++;
                        wrap = new Point(x, position.getY());
                    } else if (direction.equals(Point.ARRAY_LEFT)) {
                        int x = xMax - 1;
                        while (board[position.getY()][x] == Tile.EMPTY) x--;
                        wrap = new Point(x, position.getY());
                    } else if (direction.equals(Point.ARRAY_DOWN)) {
                        int y = 0;
                        while (board[y][position.getX()] == Tile.EMPTY) y++;
                        wrap = new Point(position.getX(), y);
                    } else if (direction.equals(Point.ARRAY_UP)) {
                        int y = yMax - 1;
                        while (board[y][position.getX()] == Tile.EMPTY) y--;
                        wrap = new Point(position.getX(), y);
                    }
                    next = board[wrap.getY()][wrap.getX()];
                }

                if (next == Tile.WALL)
                    break;

                assert next == Tile.OPEN;

                if (wrap.equals(Point.ORIGIN)) {
                    position = position.add(direction);
                } else {
                    position = wrap;
                }
            }

            direction = turn(direction, instruction.getTurn());
        }
    }

    public void calculatePositionAlt() {
        for (Instruction instruction : instructions) {
            for (int i = 0; i < instruction.getSteps(); i++) {
                Tile next = board[position.getY() + direction.getY()][position.getX() + direction.getX()];
                Orientation wrap = new Orientation(new Point(), direction);

                if (next == Tile.EMPTY) {
                    wrap = getWrap(new Orientation(position, direction));
                    next = board[wrap.getPosition().getY()][wrap.getPosition().getX()];
                }

                if (next == Tile.WALL)
                    break;

                assert next == Tile.OPEN;

                if (wrap.getPosition().equals(Point.ORIGIN)) {
                    position = position.add(direction);
                } else {
                    position = wrap.getPosition();
                    direction = wrap.getDirection();
                }
            }

            direction = turn(direction, instruction.getTurn());
        }
    }

    private Orientation getWrap(Orientation orientation) {
        int x = orientation.getPosition().getX();
        int y = orientation.getPosition().getY();
        Point direction = orientation.getDirection();

        if (direction == Point.ARRAY_RIGHT) {
            if (x == 150) return new Orientation(new Point(100, 151 - y), turn(direction, Turn.FULL));
            if (x == 100) {
                if (y >= 51 && y <= 100) return new Orientation(new Point(100 + (y - 50), 50), turn(direction, Turn.LEFT));
                if (y >= 101 && y <= 150) return new Orientation(new Point(150, 51 - (y - 100)), turn(direction, Turn.FULL));
            }
            if (x == 50) return new Orientation(new Point(50 + (y - 150), 150), turn(direction, Turn.LEFT));
        }
        if (direction == Point.ARRAY_LEFT) {
            if (x == 51) {
                if (y >= 1 && y <= 50) return new Orientation(new Point(1, 151 - y), turn(direction, Turn.FULL));
                if (y >= 51 && y <= 100) return new Orientation(new Point(y - 50, 101), turn(direction, Turn.LEFT));
            }
            if (x == 1) {
                if (y >= 101 && y <= 150) return new Orientation(new Point(51, 1 + (150 - y)), turn(direction, Turn.FULL));
                if (y >= 151 && y <= 200) return new Orientation(new Point(50 + (y - 150), 1), turn(direction, Turn.LEFT));
            }
        }
        if (direction == Point.ARRAY_DOWN) {
            if (y == 50) return new Orientation(new Point(100, x - 50), turn(direction, Turn.RIGHT));
            if (y == 150) return new Orientation(new Point(50, x + 100), turn(direction, Turn.RIGHT));
            if (y == 200) return new Orientation(new Point(x + 100, 1), turn(direction, Turn.NONE));
        }
        if (direction == Point.ARRAY_UP) {
            if (y == 1) {
                if (x >= 51 && x <= 100) return new Orientation(new Point(1, x + 100), turn(direction, Turn.RIGHT));
                if (x >= 101 && x <= 150) return new Orientation(new Point(x - 100, 200), turn(direction, Turn.NONE));
            }
            if (y == 101) return new Orientation(new Point(51, x + 50), turn(direction, Turn.RIGHT));
        }
        throw new IllegalArgumentException("Unknown wrap: " +orientation);
    }

    public int getPassword() {
        int facing = 0;

        if (direction.equals(Point.ARRAY_DOWN)) facing = 1;
        else if (direction.equals(Point.ARRAY_LEFT)) facing = 2;
        else if (direction.equals(Point.ARRAY_UP)) facing = 3;

        return 1000 * position.getY() + 4 * position.getX() + facing;
    }

    private Point turn(Point direction, Turn turn) {
        if (turn == Turn.NONE) return direction;
        if (direction.equals(Point.ARRAY_UP)) {
            if (turn == Turn.FULL) return Point.ARRAY_DOWN;
            return turn == Turn.LEFT ? Point.ARRAY_LEFT : Point.ARRAY_RIGHT;
        }
        if (direction.equals(Point.ARRAY_DOWN)) {
            if (turn == Turn.FULL) return Point.ARRAY_UP;
            return turn == Turn.LEFT ? Point.ARRAY_RIGHT : Point.ARRAY_LEFT;
        }
        if (direction.equals(Point.ARRAY_LEFT)) {
            if (turn == Turn.FULL) return Point.ARRAY_RIGHT;
            return turn == Turn.LEFT ? Point.ARRAY_DOWN : Point.ARRAY_UP;
        }
        if (direction.equals(Point.ARRAY_RIGHT)) {
            if (turn == Turn.FULL) return Point.LEFT;
            return turn == Turn.LEFT ? Point.ARRAY_UP : Point.ARRAY_DOWN;
        }
        throw new IllegalArgumentException("Unknown direction: " + direction);
    }

}
