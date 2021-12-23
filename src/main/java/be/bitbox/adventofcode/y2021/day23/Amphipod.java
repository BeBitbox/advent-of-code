package be.bitbox.adventofcode.y2021.day23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Amphipod {
    private char[] hallway = new char[11];
    private static final boolean[] HALLWAY_POS = new boolean[] {true, true, false, true, false, true, false, true, false, true, true};
    private char[] room1 = new char[4];
    private char[] room2 = new char[4];
    private char[] room3 = new char[4];
    private char[] room4 = new char[4];
    private final boolean part1;
    private int energy;

    public Amphipod(char[] rooms) {
        Arrays.fill(hallway, '.');
        if (rooms.length < 9) {
            room1[0] = rooms[0];
            room1[1] = rooms[1];
            room2[0] = rooms[2];
            room2[1] = rooms[3];
            room3[0] = rooms[4];
            room3[1] = rooms[5];
            room4[0] = rooms[6];
            room4[1] = rooms[7];
            part1 = true;
        } else {
            room1[0] = rooms[0];
            room1[1] = rooms[1];
            room1[2] = rooms[2];
            room1[3] = rooms[3];
            room2[0] = rooms[4];
            room2[1] = rooms[5];
            room2[2] = rooms[6];
            room2[3] = rooms[7];
            room3[0] = rooms[8];
            room3[1] = rooms[9];
            room3[2] = rooms[10];
            room3[3] = rooms[11];
            room4[0] = rooms[12];
            room4[1] = rooms[13];
            room4[2] = rooms[14];
            room4[3] = rooms[15];
            part1 = false;
        }
        energy = 0;
    }

    public Amphipod(Amphipod other) {
        System.arraycopy(other.hallway, 0, hallway, 0, 11);
        System.arraycopy(other.room1, 0, room1, 0, 4);
        System.arraycopy(other.room2, 0, room2, 0, 4);
        System.arraycopy(other.room3, 0, room3, 0, 4);
        System.arraycopy(other.room4, 0, room4, 0, 4);
        energy = other.energy;
        part1 = other.part1;
    }


    public List<Amphipod> determineNextPossibleMoves() {
        List<Amphipod> list = new ArrayList<>();

        list.addAll(getAmphipodsFromRoom(Amphipod::getRoom1, 2));
        list.addAll(getAmphipodsFromRoom(Amphipod::getRoom2, 4));
        list.addAll(getAmphipodsFromRoom(Amphipod::getRoom3, 6));
        list.addAll(getAmphipodsFromRoom(Amphipod::getRoom4, 8));

        for (int i = 0; i < 11; i++) {
            if (hallway[i] != '.') {
                int j = i - 1;
                while (j >= 0) {
                    if (hallway[j] == '.') {
                        createHallwayAmphipods(list, i, j);
                    } else {
                        break;
                    }
                    j--;
                }
                j = i + 1;
                while (j < 11) {
                    if (hallway[j] == '.') {
                        createHallwayAmphipods(list, i, j);
                    } else {
                        break;
                    }
                    j++;
                }
            }
        }
        return list;
    }

    private boolean containsAllPointsOrChars(char[] room, char expected) {
        return room[0] == '.' &&
                IntStream.range(1, maxIndex())
                        .allMatch(i -> room[i] == '.' || room[i] == expected);
    }

    private int getIndex(char[] room) {
        for (int i = 1; i < maxIndex(); i++) {
            if (room[i] != '.') {
                return i - 1;
            }
        }
        return maxIndex() - 1;
    }

    private void createHallwayAmphipods(List<Amphipod> list, int i, int j) {
        if (j == 2 && hallway[i] == 'A' && containsAllPointsOrChars(room1, 'A')) {
            var amphipod = new Amphipod(this);
            amphipod.hallway[i] = '.';

            int index = getIndex(room1);
            amphipod.room1[index] = hallway[i];
            int moves = Math.abs(j - i) + 1 + index;

            amphipod.energy += energyOfAnimal(hallway[i]) * moves;
            list.add(amphipod);
        }
        if (j == 4 && hallway[i] == 'B' && containsAllPointsOrChars(room2, 'B')) {
            var amphipod = new Amphipod(this);
            amphipod.hallway[i] = '.';

            int index = getIndex(room2);
            amphipod.room2[index] = hallway[i];
            int moves = Math.abs(j - i) + 1 + index;

            amphipod.energy += energyOfAnimal(hallway[i]) * moves;
            list.add(amphipod);
        }
        if (j == 6 && hallway[i] == 'C' && containsAllPointsOrChars(room3, 'C')) {
            var amphipod = new Amphipod(this);
            amphipod.hallway[i] = '.';

            int index = getIndex(room3);
            amphipod.room3[index] = hallway[i];
            int moves = Math.abs(j - i) + 1 + index;

            amphipod.energy += energyOfAnimal(hallway[i]) * moves;
            list.add(amphipod);
        }
        if (j == 8 && hallway[i] == 'D' && containsAllPointsOrChars(room4, 'D')) {
            var amphipod = new Amphipod(this);
            amphipod.hallway[i] = '.';

            int index = getIndex(room4);
            amphipod.room4[index] = hallway[i];
            int moves = Math.abs(j - i) + 1 + index;

            amphipod.energy += energyOfAnimal(hallway[i]) * moves;
            list.add(amphipod);
        }
    }

    int maxIndex() {
        return part1 ? 2 : 4;
    }

    private List<Amphipod> getAmphipodsFromRoom(Function<Amphipod, char[]> roomSupplier, int roomHallwayIndex) {
        List<Amphipod> list = new ArrayList<>();

        var room = roomSupplier.apply(this);
        if (!roomComplete(room)) {

            int index = 0;
            while (index < maxIndex()) {
                if (room[index] != '.') {
                    break;
                }
                index++;
            }
            if (index >= maxIndex()) {
                return List.of();
            }
            int i = roomHallwayIndex;
            while (i >= 0) {
                if (hallway[i] == '.') {
                    if (HALLWAY_POS[i]) {
                        list.add(getAmphipod(roomSupplier, roomHallwayIndex, index, i));
                    }
                } else {
                    break;
                }
                i--;
            }
            i = roomHallwayIndex;
            while (i < 11) {
                if (hallway[i] == '.') {
                    if (HALLWAY_POS[i]) {
                        list.add(getAmphipod(roomSupplier, roomHallwayIndex, index, i));
                    }
                } else {
                    break;
                }
                i++;
            }
        }

        return list;
    }

    private Amphipod getAmphipod(Function<Amphipod, char[]> roomSupplier, int roomHallwayIndex, int index, int i) {
        var amphipod = new Amphipod(this);
        var animal = roomSupplier.apply(amphipod)[index];
        amphipod.hallway[i] = animal;
        roomSupplier.apply(amphipod)[index] = '.';
        amphipod.energy += energyOfAnimal(animal) * (index + Math.abs(roomHallwayIndex - i) + 1);
        return amphipod;
    }

    public int energyOfAnimal(char animal) {
        switch (animal) {
            case 'A':
                return 1;
            case 'B':
                return 10;
            case 'C':
                return 100;
            case 'D':
                return 1000;
        }
        throw new IllegalArgumentException();
    }

    public char[] getRoom1() {
        return room1;
    }

    public char[] getRoom2() {
        return room2;
    }

    public char[] getRoom3() {
        return room3;
    }

    public char[] getRoom4() {
        return room4;
    }

    private boolean roomComplete(char[] room) {
        if (part1) {
            return room[0] != '.' && room[0] == room[1];
        } else {
            return room[0] != '.' && room[0] == room[1] && room[1] == room[2] && room[2] == room[3];
        }
    }

    public boolean isComplete() {
        return roomComplete(room1) && roomComplete(room2) && roomComplete(room3) && roomComplete(room4);
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public String toString() {
        return "#############" + System.lineSeparator() +
                "#" + String.valueOf(hallway) + "#" + System.lineSeparator() +
                "###" + room1[0] + "#" + room2[0] + "#" + room3[0] + "#" + room4[0] + "###" + System.lineSeparator() +
                roomDetails() +
                "  ######### " + energy + System.lineSeparator();
    }

    private String roomDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < maxIndex(); i++) {
            stringBuilder.append("  #").append(room1[i]).append("#").append(room2[i]).append("#").append(room3[i]).append("#").append(room4[i]).append("#").append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amphipod amphipod = (Amphipod) o;
        return Arrays.equals(hallway, amphipod.hallway) && Arrays.equals(room1, amphipod.room1) && Arrays.equals(room2, amphipod.room2) && Arrays.equals(room3, amphipod.room3) && Arrays.equals(room4, amphipod.room4);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(hallway);
        result = 31 * result + Arrays.hashCode(room1);
        result = 31 * result + Arrays.hashCode(room2);
        result = 31 * result + Arrays.hashCode(room3);
        result = 31 * result + Arrays.hashCode(room4);
        return result;
    }
}
