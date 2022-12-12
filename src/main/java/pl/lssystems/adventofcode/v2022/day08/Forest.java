package pl.lssystems.adventofcode.v2022.day08;

import java.util.List;

import static pl.lssystems.adventofcode.v2022.day08.Direction.*;

public class Forest {

    Tree[][] map;

    public Forest(int[][] input) {
        this.map = new Tree[input.length][input[0].length];
        for (int y = 0; y < input.length; y++)
            for (int x = 0; x < input[y].length; x++)
                this.map[y][x] = new Tree(x, y, input[y][x]);
    }

    public int getWidth() {
        return map[0].length;
    }

    public int getHeight() {
        return map.length;
    }

    public Tree getTree(int x, int y) {
        return map[y][x];
    }

    public int[] getView(Direction d, int x, int y) {
        int[] view = new int[d.isForward() ?
                    (!d.isOpposite() ? y : x) :
                    (d.isOpposite() ? getHeight()-1-y : getWidth()-1-x)];
        switch (d) {
            case NORTH: //Top
                for (int i = 0; i < y; i++)
                    view[i] = map[(y-1)-i][x].getHeight();
                break;
            case WEST: //Left
                for (int i = 0; i < x; i++)
                    view[i] = map[y][(x-1)-i].getHeight();
                break;
            case SOUTH: //Bottom
                for (int i = 0; i < view.length; i++)
                    view[i] = map[(y+1)+i][x].getHeight();
                break;
            case EAST: //Right
                for (int i = 0; i < view.length; i++)
                    view[i] = map[y][(x+1)+i].getHeight();
                break;
        }
        return view;
    }

    public boolean checkVisibility(int x, int y) {
        Tree tree = getTree(x,y);
        tree.checkVisibility(NORTH, getView(NORTH, x, y));
        tree.checkVisibility(SOUTH, getView(SOUTH, x, y));
        tree.checkVisibility(EAST, getView(EAST, x, y));
        tree.checkVisibility(WEST, getView(WEST, x, y));
        System.out.println(tree);
        return tree.isVisibleFromAnySide();
    }

    public int calculateScenicScore(int x, int y) {
        Tree tree = getTree(x,y);
        checkVisibility(x,y);
        return tree.getViewDistance(NORTH) * tree.getViewDistance(SOUTH) * tree.getViewDistance(EAST) * tree.getViewDistance(WEST);
    }

}
