package pl.lssystems.adventofcode.v2022.day13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class PacketOps {

    public static int compareCounter = 0;
    public static final List<Packet> packetList = new ArrayList<>();

    public static int compare(Packet left, Packet right) {
        compareCounter++;
        return left.isInRightOrderComparedTo(right);
    }

    public static int getCompareCounter() {
        return compareCounter;
    }

    public static void compileAndCollect(String line) {
        packetList.add(compile(line));
    }

    public static List<Packet> getSortedCollection() {
        packetList.sort((a, b) -> b.isInRightOrderComparedTo(a));
        return packetList;
    }

    public static Packet compile(String line) {
        return compilePacketRecurse(line.chars().mapToObj(i -> (char) i).collect(Collectors.toCollection(LinkedList::new)));
    }

    private static Packet compilePacketRecurse(Queue<Character> chars) {
        Packet list = new Packet(new ArrayList<>());
        chars.poll();

        while (!chars.isEmpty() && !chars.peek().equals(']')) {
            if (chars.peek().equals('[')) {
                list.getList().add(compilePacketRecurse(chars));
                continue;
            }
            if (chars.peek().equals(',')) {
                chars.poll();
                continue;
            }
            StringBuilder numberString = new StringBuilder();

            while (!chars.isEmpty() && chars.peek() >= '0' && chars.peek() <= '9')
                numberString.append(chars.poll());

            list.getList().add(new Packet(Integer.parseInt(numberString.toString())));
        }
        chars.poll();
        return list;
    }

}
