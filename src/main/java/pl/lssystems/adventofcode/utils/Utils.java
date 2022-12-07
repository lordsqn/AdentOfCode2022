package pl.lssystems.adventofcode.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Utils {

    @FunctionalInterface
    public interface Process {
        void process (String line);
    }

    @FunctionalInterface
    public interface ProcessList<T> {
        List<T> process (List<String> list);
    }

    public static void processLine(String path, Process process) {
        process(path, list -> {
            list.forEach(process::process);
            return null;
        });
    }

    public static <T> List<T> process(String path, ProcessList<T> process) {
        try {
            List<String> list = Files.readAllLines(Paths.get(new File(Optional.ofNullable(Utils.class.getClassLoader().getResource(path))
                    .orElseThrow(() -> new RuntimeException("File not found: " + path)).getPath()).getAbsolutePath()));
            return process.process(list);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}
