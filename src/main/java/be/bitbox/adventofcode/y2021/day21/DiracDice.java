package be.bitbox.adventofcode.y2021.day21;

public class DiracDice {
    private int position1;
    private int position2;
    private int score1;
    private int score2;
    private boolean turnPlayer1;
    private int rolls;

    public DiracDice(int player1, int player2) {
        this.position1 = player1;
        this.position2 = player2;
        score1 = 0;
        score2 = 0;
        rolls = 0;
        turnPlayer1 = true;
    }

    public int rollDeterministicTillScore1000() {
        while (score1 < 1000 && score2 < 1000) {
            rollDeterministic();
        }

        if (score1 >= 1000) {
            return score2 * rolls;
        } else {
            return score1 * rolls;
        }
    }

    public void rollDeterministic() {
        int points = (++rolls + ++rolls + ++rolls);
        if (turnPlayer1) {
            position1 = (position1 + points) % 10;
            if (position1 == 0) {
                position1 = 10;
            }
            score1 += position1;
        } else {
            position2 = (position2 + points) % 10;
            if (position2 == 0) {
                position2 = 10;
            }
            score2 += position2;
        }
        turnPlayer1 = !turnPlayer1;
    }

    public Long playMadGame() {
        long[][][][][] madGame = new long[2][11][11][31][31];
        madGame[0][position1][position2][0][0] = 1;

        boolean foundOne = true;
        while (foundOne) {
            foundOne = false;
            for (int player = 0; player < 2; player++) {
                for (int pos1 = 0; pos1 < 11; pos1++) {
                    for (int pos2 = 0; pos2 < 11; pos2++) {
                        for (int score1 = 0; score1 < 21; score1++) {
                            for (int score2 = 0; score2 < 21; score2++) {
                                if (madGame[player][pos1][pos2][score1][score2] > 0) {
                                    foundOne = true;
                                    if (player == 0) {
                                        for (int roll = 3; roll < 10; roll++) {
                                            int newPos1 = (pos1 + roll) % 10;
                                            if (newPos1 == 0) {
                                                newPos1 = 10;
                                            }
                                            int newScore1 = score1 + newPos1;
                                            madGame[1][newPos1][pos2][newScore1][score2] += (multiplier(roll) * madGame[player][pos1][pos2][score1][score2]);
                                        }
                                    } else {
                                        for (int roll = 3; roll < 10; roll++) {
                                            int newPos2 = (pos2 + roll) % 10;
                                            if (newPos2 == 0) {
                                                newPos2 = 10;
                                            }
                                            int newScore2 = score2 + newPos2;
                                            madGame[0][pos1][newPos2][score1][newScore2] += (multiplier(roll) * madGame[player][pos1][pos2][score1][score2]);
                                        }
                                    }
                                    madGame[player][pos1][pos2][score1][score2] = 0;
                                }

                            }
                        }
                    }
                }
            }
        }

        long player1Wins = 0L;
        long player2Wins = 0L;

        for (int player = 0; player < 2; player++) {
            for (int pos1 = 0; pos1 < 11; pos1++) {
                for (int pos2 = 0; pos2 < 11; pos2++) {
                    for (int score1 = 0; score1 < 31; score1++) {
                        for (int score2 = 0; score2 < 31; score2++) {
                            if (score1 >= 21) {
                                player1Wins += madGame[player][pos1][pos2][score1][score2];
                            }
                            if (score2 >= 21) {
                                player2Wins += madGame[player][pos1][pos2][score1][score2];
                            }
                        }
                    }
                }
            }
        }
        return Math.max(player1Wins, player2Wins);
    }

    public static int multiplier(int rolls) {
        switch (rolls) {
            case 3:
            case 9:
                return 1;
            case 4:
            case 8:
                return 3;
            case 5:
            case 7:
                return 6;
            case 6:
                return 7;
            default:
                throw new IllegalArgumentException("unexpected rolls " + rolls);
        }
    }

    //region Getters
    public int getPosition1() {
        return position1;
    }

    public int getPosition2() {
        return position2;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public boolean isTurnPlayer1() {
        return turnPlayer1;
    }

    public int getRolls() {
        return rolls;
    }
    //endregion
}
