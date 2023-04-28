package org.tictactoe;

public class MoveValidator {
    /**
     * checks whether a move is valid
     * @param rowIndex row of the move
     * @param colIndex column of the move
     * @param field game field
     */
    public static boolean moveIsValid(int rowIndex, int colIndex,
                                      Field field, int fieldSize) {
        if (!coordinatesAreValid(rowIndex, colIndex, fieldSize)) {
            throw new IllegalArgumentException(
                    String.format("Coordinates should be from 1 to %d!", fieldSize));
        }
        if (cellIsOccupied(rowIndex, colIndex, field)) {
            throw new IllegalArgumentException("This cell is occupied! Choose another one!");
        }
        return true;
    }

    // whether the coordinates are within the field
    private static boolean coordinatesAreValid(int rowIndex, int colIndex, int fieldSize) {
        return rowIndex >= 0 && rowIndex < fieldSize
                && colIndex >= 0 && colIndex < fieldSize;
    }

    // whether the cell is already filled
    private static boolean cellIsOccupied(int rowIndex, int colIndex, Field field) {
        return field.getCell(rowIndex, colIndex) != Field.EMPTY_CHAR;
    }
}
