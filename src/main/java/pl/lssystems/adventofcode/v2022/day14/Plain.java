package pl.lssystems.adventofcode.v2022.day14;

import java.util.List;

public class Plain {
    private static Plain instance = null;
    private int maxXPos;
    private int maxYPos;
    private int offsetX;
    private int offsetY;
    private char[][] plain;

    private Plain() {}

    public static Plain get() {
        return instance = instance == null ? new Plain() : instance;
    }

    public void generatePlane(List<Element> elements) {
        calculatePlane(elements);
        for (Element el : elements) {
            if (el instanceof Rock)
                drawOnPlane(el.getY(), el.getX(), '#');
            if (el instanceof Sand && ((Sand)el).isStable())
                drawOnPlane(el.getY(), el.getX(), 'o');
            if (el instanceof Sand && !((Sand)el).isStable())
                drawOnPlane(el.getY(), el.getX(), '+');
        }
    }

    public int getPlainHeight() {
        return plain.length;
    }

    public int getPlainWidth() {
        return plain[0].length;
    }

    private void calculatePlane(List<Element> rocks) {
        for (Element e : rocks) {
            if (e.getX() >= 0) maxXPos = Math.max(maxXPos, e.getX() + 1);
            if (e.getY() >= 0) maxYPos = Math.max(maxYPos, e.getY() + 1);
            if (e.getX() < 0) offsetX = Math.min(offsetX, e.getX());
            if (e.getY() < 0) offsetY = Math.min(offsetY, e.getY());
        }

        int width = Math.abs(maxXPos) + Math.abs(offsetX);
        int height = Math.abs(maxYPos) + Math.abs(offsetY);
        this.plain = new char[height][width];
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                this.plain[y][x] = '.';
    }

    private void drawOnPlane(int Y, int X, char id) {
        for (int y = 0; y < plain.length; y++)
            for (int x = 0; x < plain[y].length; x++)
                if ((offsetY >= 0 && offsetX >= 0 && (offsetY + Y == offsetY + y) && (offsetX + X == offsetX + x)) || // Quarter A  // B|A
                    (offsetY >= 0 && offsetX < 0 && (offsetY + Y == offsetY + y) && (Math.abs(offsetX) + X == x)) ||  // Quarter B  //--+--
                    (offsetY < 0 && offsetX >= 0 && (Math.abs(offsetY) + Y == y) && (offsetX + X == offsetX + x)) ||  // Quarter C  // C|D
                    (offsetY < 0 && offsetX < 0 && (Math.abs(offsetY) + Y == y) && (Math.abs(offsetX) + X == x)))     // Quarter D
                    plain[y][x] = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < plain.length; y++) {
            sb.append(String.format("%-3d ", y));
            for (int x = 120; x < plain[0].length; x++)
                sb.append(plain[y][x]);
            sb.append("\n");
        }
        return sb.toString();
    }

}
