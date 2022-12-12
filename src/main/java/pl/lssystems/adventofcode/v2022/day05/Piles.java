package pl.lssystems.adventofcode.v2022.day05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Piles {
    private List<Deque<Character>> piles = new ArrayList<>();

    public Piles(List<String> arrangement) {
        arrangement.forEach(line -> {
            int cursor = 1;
            while (cursor < line.length()) {
                char currentChar = line.charAt(cursor);
                int queueIndex = (cursor - 1) / 4;
                if (piles.size() <= queueIndex) piles.add(new ArrayDeque<>());
                if (Character.isAlphabetic(currentChar)) {
                    piles.get(queueIndex).add(currentChar);
                }
                cursor += 4;
            }
        });
    }

    public void processMove(String move) {
        String[] split = move.split(" ");
        int count = Integer.parseInt(split[1]);
        int from = Integer.parseInt(split[3])-1;
        int to = Integer.parseInt(split[5])-1;
        for (int i = 0; i < count; i++) {
            char buffor = piles.get(from).poll();
            piles.get(to).addFirst(buffor);
        }
        System.out.println(move);
        System.out.println(printPiles(piles));
    }

    public void processMoveWithNewCrane(String move) {
        String[] split = move.split(" ");
        int count = Integer.parseInt(split[1]);
        int from = Integer.parseInt(split[3])-1;
        int to = Integer.parseInt(split[5])-1;
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++)
            buffer[i] = piles.get(from).poll();

        for (int i = count-1; i >= 0; i--)
            piles.get(to).addFirst(buffer[i]);

        System.out.println(move);
        System.out.println(printPiles(piles));
    }

    public String printPiles(List<Deque<Character>> piles) {
        StringBuilder sb = new StringBuilder();
        int level = 1;
        while(true) {
            String marker = "";
            boolean breaker = true;
            for (Deque<Character> pile : piles) {
                Character[] list = pile.toArray(new Character[0]);
                if (pile.size() - level >= 0) {
                    breaker = false;
                    marker += "[" + list[pile.size() - level] + "] ";
                } else marker += "    ";
            }
            marker += "\n";
            if (breaker) break;
            sb.insert(0, marker);
            level++;
        }
        for (int i = 0; i < piles.size(); i++) {
            sb.append(" ").append(i+1).append("  ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Deque<Character> pile : piles) {
            res.append(pile.poll());
        }
        return res.toString();
    }

}
