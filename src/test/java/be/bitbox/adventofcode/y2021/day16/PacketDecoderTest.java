package be.bitbox.adventofcode.y2021.day16;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PacketDecoderTest {

    @Test
    void testPacketCreation_literal() {
        var packetDecoder = new PacketDecoder("D2FE28");

        assertThat(packetDecoder.getPacket()).isEqualTo(new LiteralPacket(6, 2021));
        assertThat(packetDecoder.versionSum()).isEqualTo(6);
        assertThat(packetDecoder.calculate()).isEqualTo(2021L);
    }

    @Test
    void testPacketCreation_OperatorL0() {
        var packetDecoder = new PacketDecoder("38006F45291200");

        var operatorPacket = (OperatorPacket) packetDecoder.getPacket();
        assertThat(operatorPacket.getVersion()).isEqualTo(1);
        assertThat(operatorPacket.getTypeId()).isEqualTo(6);
        assertThat(operatorPacket.getPackets()).containsExactly(new LiteralPacket(6, 10), new LiteralPacket(2, 20));
        assertThat(packetDecoder.versionSum()).isEqualTo(9);
    }

    @Test
    void testPacketCreation_OperatorL1() {
        var packetDecoder = new PacketDecoder("EE00D40C823060");

        var operatorPacket = (OperatorPacket) packetDecoder.getPacket();
        assertThat(operatorPacket.getVersion()).isEqualTo(7);
        assertThat(operatorPacket.getTypeId()).isEqualTo(3);
        assertThat(operatorPacket.getPackets()).containsExactly(new LiteralPacket(2, 1), new LiteralPacket(4, 2), new LiteralPacket(1, 3));
        assertThat(packetDecoder.versionSum()).isEqualTo(14);
    }

    @Test
    void testPacketCreation_Other1() {
        var packetDecoder = new PacketDecoder("8A004A801A8002F478");

        assertThat(packetDecoder.versionSum()).isEqualTo(16);
    }

    @Test
    void testPacketCreation_Other2() {
        var packetDecoder = new PacketDecoder("620080001611562C8802118E34");

        assertThat(packetDecoder.versionSum()).isEqualTo(12);
    }

    @Test
    void testPacketCreation_Other3() {
        var packetDecoder = new PacketDecoder("C0015000016115A2E0802F182340");

        assertThat(packetDecoder.versionSum()).isEqualTo(23);
    }

    @Test
    void testPacketCreation_Other4() {
        var packetDecoder = new PacketDecoder("A0016C880162017C3686B18A3D4780");

        assertThat(packetDecoder.versionSum()).isEqualTo(31);
    }

    @Test
    void calculation_sum() {
        var packetDecoder = new PacketDecoder("C200B40A82");

        assertThat(packetDecoder.calculate()).isEqualTo(3);
    }

    @Test
    void calculation_multiply() {
        var packetDecoder = new PacketDecoder("04005AC33890");

        assertThat(packetDecoder.calculate()).isEqualTo(54);
    }

    @Test
    void calculation_min() {
        var packetDecoder = new PacketDecoder("880086C3E88112");

        assertThat(packetDecoder.calculate()).isEqualTo(7);
    }

    @Test
    void calculation_max() {
        var packetDecoder = new PacketDecoder("CE00C43D881120");

        assertThat(packetDecoder.calculate()).isEqualTo(9);
    }

    @Test
    void calculation_greater() {
        var packetDecoder = new PacketDecoder("D8005AC2A8F0");

        assertThat(packetDecoder.calculate()).isEqualTo(1);
    }

    @Test
    void calculation_lesser() {
        var packetDecoder = new PacketDecoder("F600BC2D8F");

        assertThat(packetDecoder.calculate()).isEqualTo(0);
    }

    @Test
    void calculation_equal() {
        var packetDecoder = new PacketDecoder("9C005AC2F8F0");

        assertThat(packetDecoder.calculate()).isEqualTo(0);
    }

    @Test
    void calculation_combined() {
        var packetDecoder = new PacketDecoder("9C0141080250320F1802104A08");

        assertThat(packetDecoder.calculate()).isEqualTo(1);
    }

    @Test
    void test_big() {
        var packetDecoder = new PacketDecoder(Util.readFileAsString("21_day16.txt"));

        assertThat(packetDecoder.versionSum()).isEqualTo(934);
        assertThat(packetDecoder.calculate()).isEqualTo(912901337844L);
    }
}