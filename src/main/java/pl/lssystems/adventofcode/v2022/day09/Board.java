package pl.lssystems.adventofcode.v2022.day09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private static Board instance = null;
    private int maxXPos;
    private int maxYPos;
    private int offsetX;
    private int offsetY;
    private final long delay;
    private char[][] board;

    private static boolean drawEveryFrame = false;

    public static Board get() {
        return instance = instance == null ? new Board(0) : instance;
    }

    public static void drawEveryFrame() {
        drawEveryFrame = true;
    }

    private Board(long delay) {
        this.delay = delay;
    }

    public Board drawFinalFrame(RopeElement elem) {
        drawEveryFrame = true;
        update(elem);
        return this;
    }

    public void update(RopeElement elem) {
        calculatePlane(elem);

        if (drawEveryFrame) {
            for (Move m : Move.getUniqueMovesRecord())
                drawOnBoard(m.getY(), m.getX(), '#');

            List<RopeElement> elems = new ArrayList<>();
            RopeElement e = elem;
            while(true) {
                elems.add(e);
                if (e.getChild() == null) break;
                e = e.getChild();
            }
            Collections.reverse(elems);
            for (RopeElement el : elems)
                drawOnBoard(el.getY(), el.getX(), el.getId());

            System.out.println(this);
        }
    }

    private void calculatePlane(RopeElement elem) {
        RopeElement e = elem;
        while(true) {
            if (e.getX() >= 0) maxXPos = Math.max(maxXPos, e.getX()+1);
            if (e.getY() >= 0) maxYPos = Math.max(maxYPos, e.getY()+1);
            if (e.getX() < 0) offsetX = Math.min(offsetX, e.getX());
            if (e.getY() < 0) offsetY = Math.min(offsetY, e.getY());
            if (e.getChild() == null) break;
            e = e.getChild();
        }
        int width = Math.abs(maxXPos) + Math.abs(offsetX);
        int height = Math.abs(maxYPos) + Math.abs(offsetY);
        board = new char[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                this.board[y][x] = '.';
                if (x == Math.abs(offsetX) && y == Math.abs(offsetY)) board[y][x] = 's';
            }
    }

    private void drawOnBoard(int Y, int X, char id) {
        for (int y = 0; y < board.length; y++)
            for (int x = 0; x < board[y].length; x++)
                if ((offsetY >= 0 && offsetX >= 0 && (offsetY + Y == offsetY + y) && (offsetX + X == offsetX + x)) || // Quarter A  // B|A
                    (offsetY >= 0 && offsetX < 0 && (offsetY + Y == offsetY + y) && (Math.abs(offsetX) + X == x)) ||  // Quarter B  //--+--
                    (offsetY < 0 && offsetX >= 0 && (Math.abs(offsetY) + Y == y) && (offsetX + X == offsetX + x)) ||  // Quarter C  // C|D
                    (offsetY < 0 && offsetX < 0 && (Math.abs(offsetY) + Y == y) && (Math.abs(offsetX) + X == x)))     // Quarter D
                    board[y][x] = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = board.length-1; y >= 0; y--) {
            for (int x = 0; x < board[0].length; x++)
                sb.append(board[y][x]);
            sb.append("\n");
        }
        if (delay != 0) {
            try {Thread.sleep(500);}
            catch (InterruptedException e) {throw new RuntimeException(e);}
        }
        return sb.toString();
    }

}
