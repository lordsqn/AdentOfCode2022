package pl.lssystems.adventofcode.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class Utils {

    @FunctionalInterface
    public interface Process {
        void process (String line);
    }

    public static void processLine(String path, Process process) throws IOException {
        Files.readAllLines(Paths.get(new File(Optional.ofNullable(Utils.class.getClassLoader().getResource(path))
                .orElseThrow(() -> new RuntimeException("File not found: " + path)).getPath()).getAbsolutePath()))
                .forEach(process::process);
    }

}
