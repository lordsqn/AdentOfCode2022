package pl.lssystems.adventofcode.day09;

public interface RopeElement {

    int getX();

    int getY();

    char getId();

    RopeElement getChild();

}