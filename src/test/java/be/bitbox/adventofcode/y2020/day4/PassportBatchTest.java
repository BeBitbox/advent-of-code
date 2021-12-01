package be.bitbox.adventofcode.y2020.day4;

import be.bitbox.adventofcode.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static be.bitbox.adventofcode.y2020.day4.Passport.PassportBuilder.aPassport;
import static org.assertj.core.api.Assertions.assertThat;

class PassportBatchTest {

    @Test
    void testSinglePassport() {
        var passports = new PassportBatch(validPassportExample()).getPassports();

        assertThat(passports).containsExactly(fixedValidPassport());
    }

    @Test
    void testPassportExample() {
        var passports = new PassportBatch(getExamplePasswordText()).getPassports();

        assertThat(passports).contains(
                fixedValidPassport(),
                aPassport()
                        .withBirthYear(1929)
                        .withCountryID(350)
                        .withExpirationYear(2023)
                        .withEyeColor("amb")
                        .withHairColor("#cfa07d")
                        .withPassportID("028048884")
                        .withIssueYear(2013)
                        .build(),
                aPassport()
                        .withBirthYear(1931)
                        .withExpirationYear(2024)
                        .withEyeColor("brn")
                        .withHairColor("#ae17e1")
                        .withPassportID("760753108")
                        .withIssueYear(2013)
                        .withHeight("179cm")
                        .build(),
                aPassport()
                        .withExpirationYear(2025)
                        .withEyeColor("brn")
                        .withHairColor("#cfa07d")
                        .withPassportID("166559648")
                        .withIssueYear(2011)
                        .withHeight("59in")
                        .build()
                );
    }

    @Test
    void testPassportExampleValid() {
        var passports = new PassportBatch(getExamplePasswordText()).getPassports();

        assertThat(passports.get(0).isValid()).isTrue();
        assertThat(passports.get(1).isValid()).isFalse();
        assertThat(passports.get(2).isValid()).isTrue();
        assertThat(passports.get(3).isValid()).isFalse();
    }

    @Test
    void testPassportFileValid() {
        var passports = new PassportBatch(Util.readFileAsStringList("day4.txt")).getPassports();

        var validPassports = passports.stream()
                .filter(Passport::isValid)
                .count();

        assertThat(validPassports).isEqualTo(196L);
    }

    @Test
    void testSinglePassportValid2() {
        var passport = new PassportBatch(validPassportExample()).getPassports().get(0);

        assertThat(passport.isValid2()).isTrue();

        // birth year
        passport.setBirthYear(1919);
        assertThat(passport.isValid2()).isFalse();
        passport.setBirthYear(1920);
        assertThat(passport.isValid2()).isTrue();
        passport.setBirthYear(2003);
        assertThat(passport.isValid2()).isFalse();
        passport.setBirthYear(2002);
        assertThat(passport.isValid2()).isTrue();

        // issue year
        passport.setIssueYear(2009);
        assertThat(passport.isValid2()).isFalse();
        passport.setIssueYear(2010);
        assertThat(passport.isValid2()).isTrue();
        passport.setIssueYear(2021);
        assertThat(passport.isValid2()).isFalse();
        passport.setIssueYear(2020);
        assertThat(passport.isValid2()).isTrue();

        // experiation year
        passport.setExpirationYear(2019);
        assertThat(passport.isValid2()).isFalse();
        passport.setExpirationYear(2020);
        assertThat(passport.isValid2()).isTrue();
        passport.setExpirationYear(2031);
        assertThat(passport.isValid2()).isFalse();
        passport.setExpirationYear(2030);
        assertThat(passport.isValid2()).isTrue();

        // height CM
        passport.setHeight(null);
        assertThat(passport.isValid2()).isFalse();
        passport.setHeight("149cm");
        assertThat(passport.isValid2()).isFalse();
        passport.setHeight("150cm");
        assertThat(passport.isValid2()).isTrue();
        passport.setHeight("194cm");
        assertThat(passport.isValid2()).isFalse();
        passport.setHeight("193cm");
        assertThat(passport.isValid2()).isTrue();

        // height Inch
        passport.setHeight("58in");
        assertThat(passport.isValid2()).isFalse();
        passport.setHeight("59in");
        assertThat(passport.isValid2()).isTrue();
        passport.setHeight("77in");
        assertThat(passport.isValid2()).isFalse();
        passport.setHeight("76in");
        assertThat(passport.isValid2()).isTrue();

        // hair color
        passport.setHairColor(null);
        assertThat(passport.isValid2()).isFalse();
        passport.setHairColor("#123abz");
        assertThat(passport.isValid2()).isFalse();
        passport.setHairColor("#aa12fe");
        assertThat(passport.isValid2()).isTrue();
        passport.setHairColor("#aA12fe");
        assertThat(passport.isValid2()).isFalse();
        passport.setHairColor("123abc");
        assertThat(passport.isValid2()).isFalse();
        passport.setHairColor("#12345");
        assertThat(passport.isValid2()).isFalse();
        passport.setHairColor("#123456");
        assertThat(passport.isValid2()).isTrue();

        // eye color
        passport.setEyeColor(null);
        assertThat(passport.isValid2()).isFalse();
        passport.setEyeColor("wat");
        assertThat(passport.isValid2()).isFalse();
        passport.setEyeColor("hzl");
        assertThat(passport.isValid2()).isTrue();

        // passport id
        passport.setPassportID(null);
        assertThat(passport.isValid2()).isFalse();
        passport.setPassportID("0123456789");
        assertThat(passport.isValid2()).isFalse();
        passport.setPassportID("00000A001");
        assertThat(passport.isValid2()).isFalse();
        passport.setPassportID("000000001");
        assertThat(passport.isValid2()).isTrue();
    }

    @Test
    void testPassportFileValid2() {
        var passports = new PassportBatch(Util.readFileAsStringList("day4.txt")).getPassports();

        var validPassports = passports.stream()
                .filter(Passport::isValid2)
                .count();

        assertThat(validPassports).isEqualTo(114L);
    }

    private List<String> getExamplePasswordText() {
        return List.of(
        "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd      ",
        "byr:1937 iyr:2017 cid:147 hgt:183cm             ",
        "                                                ",
        "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 ",
        "hcl:#cfa07d byr:1929                            ",
        "                                                ",
        "hcl:#ae17e1 iyr:2013                            ",
        "eyr:2024                                        ",
        "ecl:brn pid:760753108 byr:1931                  ",
        "hgt:179cm                                       ",
        "                                                ",
        "hcl:#cfa07d eyr:2025 pid:166559648              ",
        "iyr:2011 ecl:brn hgt:59in                       ");
    }

    private Passport fixedValidPassport() {
        return aPassport()
                .withBirthYear(1937)
                .withCountryID(147)
                .withExpirationYear(2020)
                .withEyeColor("gry")
                .withHairColor("#fffffd")
                .withHeight("183cm")
                .withPassportID("860033327")
                .withIssueYear(2017)
                .build();
    }

    private List<String> validPassportExample() {
        return List.of("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd",
                "byr:1937 iyr:2017 cid:147 hgt:183cm");
    }
}