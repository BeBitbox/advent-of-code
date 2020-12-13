package be.bitbox.adventofcode.y2020.day4;

import java.util.List;
import java.util.Objects;

public class Passport {
    public static final List<String> VALID_EYE_COLORS = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    private int birthYear;
    private int issueYear;
    private int expirationYear;
    private String height;
    private String hairColor;
    private String eyeColor;
    private String passportID;
    private int countryID;

    boolean isValid() {
        return birthYear != 0
                && issueYear != 0
                && expirationYear != 0
                && height != null
                && hairColor != null
                && eyeColor != null
                && passportID != null;
    }

    public boolean isValid2() {
        return 1920 <= birthYear && birthYear <= 2002
                && 2010 <= issueYear && issueYear <= 2020
                && 2020 <= expirationYear && expirationYear <= 2030
                && validHeight2()
                && validateHairColor()
                && eyeColor != null && VALID_EYE_COLORS.contains(eyeColor)
                && validPassportId(passportID);
    }

    private boolean validPassportId(String passportID) {
        if (passportID == null || passportID.length() != 9) {
            return false;
        }
        try {
            Integer.parseInt(passportID);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateHairColor() {
        return hairColor != null
                && hairColor.length() == 7
                && hairColor.startsWith("#")
                && hairColor.substring(1).matches("[0-9a-f]*");
    }

    private boolean validHeight2() {
        if (height == null) {
            return false;
        }
        if (height.contains("cm")) {
            int heightCM = Integer.parseInt(height.substring(0, height.indexOf("cm")));
            return 150 <= heightCM && heightCM <= 193;
        }
        if (height.contains("in")) {
            int heightIn = Integer.parseInt(height.substring(0, height.indexOf("in")));
            return 59 <= heightIn && heightIn <= 76;
        }
        return false;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getIssueYear() {
        return issueYear;
    }

    public void setIssueYear(int issueYear) {
        this.issueYear = issueYear;
    }

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passport passport = (Passport) o;
        return birthYear == passport.birthYear && issueYear == passport.issueYear && expirationYear == passport.expirationYear && countryID == passport.countryID && Objects.equals(height, passport.height) && Objects.equals(hairColor, passport.hairColor) && Objects.equals(eyeColor, passport.eyeColor) && Objects.equals(passportID, passport.passportID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportID, countryID);
    }

    @Override
    public String toString() {
        return "Passport{" +
                "birthYear=" + birthYear +
                ", issueYear=" + issueYear +
                ", expirationYear=" + expirationYear +
                ", height='" + height + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", passportID='" + passportID + '\'' +
                ", countryID=" + countryID +
                '}';
    }

    public static final class PassportBuilder {
        private int birthYear;
        private int issueYear;
        private int expirationYear;
        private String height;
        private String hairColor;
        private String eyeColor;
        private String passportID;
        private int countryID;

        private PassportBuilder() {
        }

        public static PassportBuilder aPassport() {
            return new PassportBuilder();
        }

        public PassportBuilder withBirthYear(int birthYear) {
            this.birthYear = birthYear;
            return this;
        }

        public PassportBuilder withIssueYear(int issueYear) {
            this.issueYear = issueYear;
            return this;
        }

        public PassportBuilder withExpirationYear(int expirationYear) {
            this.expirationYear = expirationYear;
            return this;
        }

        public PassportBuilder withHeight(String height) {
            this.height = height;
            return this;
        }

        public PassportBuilder withHairColor(String hairColor) {
            this.hairColor = hairColor;
            return this;
        }

        public PassportBuilder withEyeColor(String eyeColor) {
            this.eyeColor = eyeColor;
            return this;
        }

        public PassportBuilder withPassportID(String passportID) {
            this.passportID = passportID;
            return this;
        }

        public PassportBuilder withCountryID(int countryID) {
            this.countryID = countryID;
            return this;
        }

        public Passport build() {
            Passport passport = new Passport();
            passport.setBirthYear(birthYear);
            passport.setIssueYear(issueYear);
            passport.setExpirationYear(expirationYear);
            passport.setHeight(height);
            passport.setHairColor(hairColor);
            passport.setEyeColor(eyeColor);
            passport.setPassportID(passportID);
            passport.setCountryID(countryID);
            return passport;
        }
    }
}
