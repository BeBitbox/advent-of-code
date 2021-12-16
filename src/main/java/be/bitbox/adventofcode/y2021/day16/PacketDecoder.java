package be.bitbox.adventofcode.y2021.day16;

import be.bitbox.adventofcode.Tuple;

import java.util.ArrayList;
import java.util.List;

public class PacketDecoder {

    private final Packet packet;

    public PacketDecoder(String input) {
        var remaining = hexToBin(input);

        var packetTuple = createPacket(remaining);
        packet = packetTuple.x;
    }

    private Tuple<Packet, String> createPacket(String binaryString) {
        int i = 0;
        int version = Integer.parseInt(binaryString.substring(i, i + 3), 2);
        i += 3;
        int typeId = Integer.parseInt(binaryString.substring(i, i + 3), 2);
        i += 3;

        if (typeId == 4) {
            StringBuilder buffer = new StringBuilder();
            boolean lastInt = true;
            while (i < binaryString.length() && lastInt) {
                buffer.append(binaryString, i + 1, i + 5);
                if (binaryString.charAt(i) == '0') {
                    lastInt = false;
                }
                i += 5;
            }
            var remaining = i < binaryString.length() ? binaryString.substring(i) : "";
            return new Tuple<>(new LiteralPacket(version, Long.parseLong(buffer.toString(), 2)), remaining);
        } else {
            List<Packet> list = new ArrayList<>();
            var lengthTypeId = binaryString.charAt(i);
            i++;
            if (lengthTypeId == '0') {
                int length = Integer.parseInt(binaryString.substring(i, i + 15), 2);
                i += 15;

                var packetString = binaryString.substring(i, i + length);
                while (packetString.length() > 6) {
                    var packetStringTuple = createPacket(packetString);
                    list.add(packetStringTuple.x);
                    packetString = packetStringTuple.y;
                }
                i += length;
            } else {
                int number = Integer.parseInt(binaryString.substring(i, i + 11), 2);
                i += 11;

                var packetString = binaryString.substring(i);
                int originalLength = packetString.length();
                int counter = 1;
                while (counter <= number) {
                    var packetStringTuple = createPacket(packetString);
                    list.add(packetStringTuple.x);
                    packetString = packetStringTuple.y;
                    counter++;
                }
                i += (originalLength - packetString.length());
            }
            var remaining = i < binaryString.length() ? binaryString.substring(i) : "";
            return new Tuple<>(new OperatorPacket(version, typeId, list), remaining);
        }
    }

    public int versionSum() {
        return packet.getVersionSum();
    }

    public long calculate() {
        return packet.calculate();
    }

    private String hexToBin(String hex) {
        hex = hex.replaceAll("0", "0000");
        hex = hex.replaceAll("1", "0001");
        hex = hex.replaceAll("2", "0010");
        hex = hex.replaceAll("3", "0011");
        hex = hex.replaceAll("4", "0100");
        hex = hex.replaceAll("5", "0101");
        hex = hex.replaceAll("6", "0110");
        hex = hex.replaceAll("7", "0111");
        hex = hex.replaceAll("8", "1000");
        hex = hex.replaceAll("9", "1001");
        hex = hex.replaceAll("A", "1010");
        hex = hex.replaceAll("B", "1011");
        hex = hex.replaceAll("C", "1100");
        hex = hex.replaceAll("D", "1101");
        hex = hex.replaceAll("E", "1110");
        hex = hex.replaceAll("F", "1111");
        return hex;
    }

    public Packet getPacket() {
        return packet;
    }
}
