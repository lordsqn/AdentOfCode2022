package pl.lssystems.adventofcode.day10;

public class CPU {

    private int cycle = 0;
    private int registerX = 1;
    private int signalStrength = 0;
    CRT crt;

    public int getCycle() {
        return cycle;
    }

    public int getRegisterX() {
        return registerX;
    }

    public int getSummarySignalStrength() {
        return this.signalStrength;
    }

    public void parseInstruction(String instruction) {
        switch (instruction.substring(0, 4)) {
            case "noop": noop(); break;
            case "addx": addx(Integer.parseInt(instruction.split(" ")[1])); break;
        }
    }

    public void noop() {
        incrementCycle();
    }

    public void addx(int value) {
        incrementCycle();
        incrementCycle();
        registerX += value;
    }

    private void incrementCycle() {
        cycle++;
        afterCycleAction();
    }

    public boolean[] getCurrentSprite() {
        boolean[] sprite = new boolean[50];
        for (int i = 0; i < sprite.length; i++)
            if (i == registerX) {
                sprite[i-1] = true;
                sprite[i] = true;
                sprite[i+1] = true;
            }
        return sprite;
    }

    private void afterCycleAction() {
        if (crt != null) crt.printPixel(cycle, getCurrentSprite());
        if ((cycle + 20) % 40 == 0) {
            this.signalStrength += cycle * registerX;
        }
    }

    public void connectTo(CRT crt) {
        this.crt = crt;
    }

}
