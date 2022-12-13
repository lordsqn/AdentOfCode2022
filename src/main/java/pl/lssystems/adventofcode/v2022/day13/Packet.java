package pl.lssystems.adventofcode.v2022.day13;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Packet {

    enum Type {LIST, NUMBER}
    private final Type type;
    private List<Packet> list;
    private int integer;

    public Packet(List<Packet> list) {
        this.type = Type.LIST;
        this.list = list;
    }

    public Packet(int number) {
        this.type = Type.NUMBER;
        this.integer = number;
    }

    public List<Packet> getList() {
        return list;
    }

    public int isInRightOrderComparedTo(Packet packet) {
        if (this.type == Type.LIST && packet.type == Type.LIST) {
            int i = 0, thisSize = this.list.size(), itemSize = packet.list.size();

            while (true) {
                if (thisSize > i && itemSize > i) {
                    int result = this.list.get(i).isInRightOrderComparedTo(packet.list.get(i));
                    if (result != 0)  return result;
                }
                else if (thisSize == itemSize) return 0;
                else if (thisSize == i) return 1;
                else if (itemSize == i) return -1;
                i++;
            }
        }

        if (this.type == Type.NUMBER && packet.type == Type.NUMBER)
            return Integer.compare(packet.integer, this.integer);

        Packet left = this.type == Type.NUMBER ? new Packet(Collections.singletonList(this)) : this;
        Packet right = packet.type == Type.NUMBER ? new Packet(Collections.singletonList(packet)) : packet;

        return left.isInRightOrderComparedTo(right);
    }

    public String toString() {
        return type == Type.NUMBER ? String.valueOf(integer) : list.stream().map(Packet::toString).collect(Collectors.joining(",", "[", "]"));
    }
}