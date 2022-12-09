package pl.lssystems.adventofcode.day9;

public interface RopeElement {

    int getX();

    int getY();

    char getId();

    RopeElement getChild();

}