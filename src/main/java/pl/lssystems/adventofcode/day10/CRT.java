package pl.lssystems.adventofcode.day10;

public class CRT {
    CPU cpu;

    short lineWidth = 40;
    short crtHeight = 6;
    short currentLine = -1; // on the beginning of each new frame counter is increment to 0
    char[][] frame = new char[crtHeight][lineWidth];

    public CRT(CPU cpu) {
        this.cpu = cpu;
        cpu.connectTo(this);
    }

    public void printPixel(int cycle, boolean[] currentSprite) {
        cycle--; // sync cycle with frame index
        short pixelIndex = (short) (cycle % 40);
        if (pixelIndex == 0) currentLine++;
        if (currentLine == crtHeight) currentLine = 0;

        frame[currentLine][pixelIndex] = currentSprite[pixelIndex] ? '#' : '.';
    }

    public String renderFrame() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < crtHeight; i++) {
            for (int j = 0; j < lineWidth; j++)
                sb.append(frame[i][j]);
            sb.append("\n");
        }
        return sb.toString();
    }

}
