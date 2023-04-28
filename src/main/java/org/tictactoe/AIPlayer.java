package org.tictactoe;

import java.util.List;
import java.util.Random;

public class AIPlayer implements Player {
    private final Level level;
    private final char playerChar;
    private final char otherPlayerChar;
    private final int[] nextMoveCoordinates = new int[2];

    public enum Level {
        Easy,
        Medium
    }

    AIPlayer(char playerChar, char otherPlayerChar, Level level) {
        this.playerChar = playerChar;
        this.otherPlayerChar = otherPlayerChar;
        this.level = level;
    }


    public void makeAMove(Game game) {
        System.out.printf("Making move level \"%s\"%n", level.name());
        this.planMove(game);
        game.setMove(nextMoveCoordinates[0], nextMoveCoordinates[1], playerChar);
    }

    private void pickARandomEmptyCell(List<Integer[]> emptyCellsCoordinates) {
        Random random = new Random();
        Integer[] chosenCoordinates = emptyCellsCoordinates.get(
                random.nextInt(emptyCellsCoordinates.size()));
        nextMoveCoordinates[0] = chosenCoordinates[0];
        nextMoveCoordinates[1] = chosenCoordinates[1];
    }

    private void planMove(Game game) {
        switch (level) {
            case Easy -> pickARandomEmptyCell(game.field.getEmptyCellsCoordinates());
            case Medium -> {
                // check for two in a row with this player char
                if (checkForTwoNonEmpty(game.field, playerChar)) {
                    break;
                }
                // check for two in a row with the other player char
                if (checkForTwoNonEmpty(game.field, otherPlayerChar)) {
                    break;
                }
                pickARandomEmptyCell(game.field.getEmptyCellsCoordinates());
            }
        }
    }

    private boolean checkForTwoNonEmpty(Field field, char charToChek) {
        // check rows
        List<List<Character>> rows = field.getRows();
        for (int rowIndex = 0; rowIndex < field.size; rowIndex++) {
            List<Character> row = rows.get(rowIndex);
            if (FieldChecker.cellsContainEmptyCell(row)
                    && FieldChecker.cellsHaveTwoMatchingChars(row, charToChek)) {
                nextMoveCoordinates[0] = rowIndex;
                nextMoveCoordinates[1] = FieldChecker.getEmptyCellIndex(row);
                return true;
            }
        }

        // check columns
        List<List<Character>> columns = field.getColumns();
        for (int colIndex = 0; colIndex < field.size; colIndex++) {
            List<Character> col = columns.get(colIndex);
            if (FieldChecker.cellsContainEmptyCell(col)
                    && FieldChecker.cellsHaveTwoMatchingChars(col, charToChek)) {
                nextMoveCoordinates[1] = colIndex;
                nextMoveCoordinates[0] = FieldChecker.getEmptyCellIndex(col);
                return true;
            }
        }

        // check diagonally
        List<List<Character>> diameters = field.getDiagonalCells();
        List<Character> mainDiameter = diameters.get(0);
        if (FieldChecker.cellsContainEmptyCell(mainDiameter)
                && FieldChecker.cellsHaveTwoMatchingChars(mainDiameter, charToChek)) {
            Integer[] chosenCell = getDiameterEmptyCellCoordinates(field, Field.Diameter.Main, mainDiameter);
            nextMoveCoordinates[0] = chosenCell[0];
            nextMoveCoordinates[1] = chosenCell[1];
            return true;
        }
        List<Character> secondaryDiameter = diameters.get(1);
        if (FieldChecker.cellsContainEmptyCell(secondaryDiameter)
                && FieldChecker.cellsHaveTwoMatchingChars(secondaryDiameter, charToChek)) {
            Integer[] chosenCell = getDiameterEmptyCellCoordinates(field, Field.Diameter.Secondary, secondaryDiameter);
            nextMoveCoordinates[0] = chosenCell[0];
            nextMoveCoordinates[1] = chosenCell[1];
            return true;
        }

        // not found
        return false;
    }

    private Integer[] getDiameterEmptyCellCoordinates(Field field, Field.Diameter diameter,
                                                      List<Character> diameterCells) {
        int cellIndex = FieldChecker.getEmptyCellIndex(diameterCells);
        return field.getDiagonalCellsCoordinates(diameter).get(cellIndex);
    }

}
