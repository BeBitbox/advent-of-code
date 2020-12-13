package be.bitbox.adventofcode.y2020.day4;

import java.util.ArrayList;
import java.util.List;

public class PassportBatch {
    private final List<Passport> passports;

    public PassportBatch(List<String> lines) {
        passports = new ArrayList<>();

        var passportBuilder = Passport.PassportBuilder.aPassport();
        for (String line : lines) {
            line = line.trim();
            if (line.length() == 0) {
                passports.add(passportBuilder.build());
                passportBuilder = Passport.PassportBuilder.aPassport();
            } else {
                processLine(passportBuilder, line);
            }
        }
        passports.add(passportBuilder.build());
    }

    private void processLine(Passport.PassportBuilder passportBuilder, String line) {
        String[] parts = line.split(" ");
        for (String part : parts) {
            String[] keyValue = part.split(":");
            switch (keyValue[0]) {
                case "byr":
                    passportBuilder.withBirthYear(Integer.parseInt(keyValue[1]));
                    break;
                case "iyr":
                    passportBuilder.withIssueYear(Integer.parseInt(keyValue[1]));
                    break;
                case "eyr":
                    passportBuilder.withExpirationYear(Integer.parseInt(keyValue[1]));
                    break;
                case "hgt":
                    passportBuilder.withHeight(keyValue[1]);
                    break;
                case "hcl":
                    passportBuilder.withHairColor(keyValue[1]);
                    break;
                case "ecl":
                    passportBuilder.withEyeColor(keyValue[1]);
                    break;
                case "pid":
                    passportBuilder.withPassportID(keyValue[1]);
                    break;
                case "cid":
                    passportBuilder.withCountryID(Integer.parseInt(keyValue[1]));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public List<Passport> getPassports() {
        return passports;
    }
}
