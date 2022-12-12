package pl.lssystems.adventofcode.v2022.day09;

public interface RopeElement {

    int getX();

    int getY();

    char getId();

    RopeElement getChild();

}