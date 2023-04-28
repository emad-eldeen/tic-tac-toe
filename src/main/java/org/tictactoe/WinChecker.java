package org.tictactoe;

public class WinChecker {

    private char winner;

    public char getWinner() {
        return winner;
    }

    public boolean thereIsAWinner(Field field) {
        return checkVertically(field) ||
                checkHorizontally(field) ||
                checkDiagonally(field);
    }

    private boolean checkVertically(Field field) {
        char potentialWinner = FieldChecker.verticalWinner(field);
        if (potentialWinner != Field.EMPTY_CHAR) {
            winner = potentialWinner;
            return true;
        }
        return false;
    }

    private boolean checkHorizontally(Field field) {
        char potentialWinner = FieldChecker.horizontalWinner(field);
        if (potentialWinner != Field.EMPTY_CHAR) {
            winner = potentialWinner;
            return true;
        }
        return false;
    }

    private boolean checkDiagonally(Field field) {
        char potentialWinner = FieldChecker.diagonalWinner(field);
        if (potentialWinner != Field.EMPTY_CHAR) {
            winner = potentialWinner;
            return true;
        }
        return false;
    }
}
