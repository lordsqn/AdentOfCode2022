package pl.lssystems.adventofcode;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Input {

    public static String readAsString() {
        return new String(readAsBytes());
    }

    public static byte[] readAsBytes() {
        try {
            return Files.readAllBytes(getInputPath());
        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    public static char[] readAsChars() {
        byte[] bytes = readAsBytes();
        char[] chars = new char[bytes.length];
        for (int i = 0; i < bytes.length; i++)
            chars[i] = (char) bytes[i];
        return chars;
    }

    public static int[] readAsInts() {
        char[] chars = readAsChars();
        int[] ints = new int[chars.length];
        for (int i = 0; i < chars.length; i++)
            ints[i] = Integer.parseInt(chars[i] + "");
        return ints;
    }

    public static String[] readAsStrings() {
        char[] chars = readAsChars();
        String[] strings = new String[chars.length];
        for (int i = 0; i < chars.length; i++)
            strings[i] = chars[i] + "";
        return strings;
    }

    public static List<String> readLines() {
        try {
            return Files.readAllLines(getInputPath());
        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<char[]> readLinesAsChars() {
        return readLines().stream().map(String::toCharArray).collect(Collectors.toList());
    }

    public static List<int[]> readLinesAsInts() {
        return readLines().stream().map(line -> Arrays.stream(line.split("")).mapToInt(Integer::parseInt).toArray()).collect(Collectors.toList());
    }

    public static List<String> readStringWithDelimiter(String delimiter) {
        return Arrays.stream(readAsString().split(delimiter, -1)).collect(Collectors.toList());
    }

    public static List<String[]> readLinesWithDelimiter(String delimiter) {
        return readLines().stream().map(line -> line.split(delimiter, -1)).collect(Collectors.toList());
    }

    public static List<Integer[]> readLinesWithDelimiterAsInt(String delimiter) {
        return readLines().stream().map(line -> line.split(delimiter, -1)).map(i -> Arrays.stream(i)
                .map(Integer::valueOf).collect(Collectors.toList()).toArray(new Integer[0])).collect(Collectors.toList());
    }

    public static List<List<String>> readLinesSetsByEmptyLineDelimiter() {
        List<List<String>> lineSets = new ArrayList<List<String>>() {{add(new ArrayList<>());}};
        for (String readAsLine : readLines())
            if (readAsLine.isEmpty()) lineSets.add(new ArrayList<>());
            else lineSets.get(lineSets.size() - 1).add(readAsLine);

        return lineSets;
    }

    public static List<List<String>> readLinesSetsByLineDelimiter(String delimiterLineContains) {
        List<List<String>> lineSets = new ArrayList<List<String>>() {{add(new ArrayList<>());}};
        for (String readAsLine : readLines())
            if (readAsLine.equals(delimiterLineContains)) lineSets.add(new ArrayList<>());
            else lineSets.get(lineSets.size() - 1).add(readAsLine);

        return lineSets;
    }

    @FunctionalInterface
    public interface Process<T> {
        void process(T input);
    }

    public static void processSignsAsBytes(Process<Byte> process) {
        byte[] bytes = readAsBytes();
        for (byte aByte : bytes)
            process.process(aByte);
    }

    public static void processSignsAsChars(Process<Character> process) {
        char[] chars = readAsChars();
        for (char aChar : chars)
            process.process(aChar);
    }

    public static void processSignsAsInts(Process<Integer> process) {
        Arrays.stream(readAsInts()).forEach(process::process);
    }

    public static void processSignsAsStrings(Process<String> process) {
        Arrays.stream(readAsStrings()).forEach(process::process);
    }

    public static void processChars(Process<char[]> process) {
        process.process(readAsChars());
    }

    public static void processInts(Process<int[]> process) {
        process.process(readAsInts());
    }

    public static void processString(Process<String> process) {
        process.process(readAsString());
    }


    public static void processLines(Process<String> process) {
        readLines().forEach(process::process);
    }

    public static void processLinesAsChars(Process<char[]> process) {
        readLinesAsChars().forEach(process::process);
    }

    public static void processLinesAsInts(Process<int[]> process) {
        readLinesAsInts().forEach(process::process);
    }

    public static void processStringWithDelimiter(String delimiter, Process<String> process) {
        readStringWithDelimiter(delimiter).forEach(process::process);
    }

    public static void processLinesWithDelimiter(String delimiter, Process<String[]> process) {
        readLinesWithDelimiter(delimiter).forEach(process::process);
    }
    public static void processLinesWithDelimiterAsInt(String delimiter, Process<Integer[]> process) {
        readLinesWithDelimiterAsInt(delimiter).forEach(process::process);
    }

    public static void processLinesSetsByEmptyLineDelimiter(Process<List<String>> process) {
        readLinesSetsByEmptyLineDelimiter().forEach(process::process);
    }

    public static void processLinesSetsByLineDelimiter(String delimiterLineContains, Process<List<String>> process) {
        readLinesSetsByLineDelimiter(delimiterLineContains).forEach(process::process);
    }

    private static Path getInputPath() throws ClassNotFoundException, NoSuchMethodException {
        StackTraceElement caller = null;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String packageName = Input.class.getName().substring(0, Input.class.getName().indexOf(Input.class.getSimpleName())-1);
        for (int i = stackTrace.length-1; i >= 0; i--)
            if (stackTrace[i].getClassName().startsWith(packageName)) {
                caller = stackTrace[i];
                break;
            }
        if (caller == null) throw new RuntimeException("Could not find caller class");
        String callerClass = caller.getClassName();
        String callerMethod = caller.getMethodName();
        Class<?> clazz = Class.forName(callerClass);
        Method method = clazz.getMethod(callerMethod);
        boolean hasTaskInput = Arrays.stream(method.getDeclaredAnnotations())
                .anyMatch(a -> a.annotationType().equals(TaskInput.class));

        String[] classPackages = callerClass.split("\\.");
        String year = classPackages[classPackages.length-3];
        String day = classPackages[classPackages.length-2];
        String inputType = hasTaskInput ? "input.txt" : "test.txt";
        String inputPath = year + "/" + day + "/" + inputType;

        return Paths.get(new File(Optional.ofNullable(Input.class.getClassLoader().getResource(inputPath))
                .orElseThrow(() -> new RuntimeException("File not found: " + inputPath)).getPath()).getAbsolutePath());
    }

}
