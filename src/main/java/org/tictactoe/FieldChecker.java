package org.tictactoe;

import java.util.List;

public class FieldChecker {

    public static boolean cellsContainEmptyCell(List<Character> cells) {
        return cells.stream().anyMatch(x -> x == Field.EMPTY_CHAR);
    }
    public static boolean cellsHaveTwoMatchingChars(List<Character> cells, char charToMatch) {
        long count = cells.stream().filter(c -> c == charToMatch).count();
        return count == 2;
    }


    // whether the provided cells are matching
    public static boolean matchingNonEmptyCells(List<Character> cells) {
        boolean matching = true;
        char charToMatch = cells.get(0);
        if (charToMatch == Field.EMPTY_CHAR) {
            matching = false;
        } else {
            for(char cell: cells) {
                if (cell != charToMatch) {
                    matching = false;
                    break;
                }
            }
        }
        return matching;
    }

    // whether there are three matching vertically
    public static char verticalWinner(Field field) {
        char winner = Field.EMPTY_CHAR;
        for (List<Character> column: field.getColumns())
            if (matchingNonEmptyCells(column)) {
                winner = column.get(0);
                break;
            }
        return winner;
    }
    // whether there are three matching horizontally
    public static char horizontalWinner(Field field) {
        char winner = Field.EMPTY_CHAR;
        for (List<Character> row: field.getRows()) {
            if (matchingNonEmptyCells(row)) {
                winner = row.get(0);
                break;
            }
        }
        return winner;
    }
    // whether there are three matching diagonally
    public static char diagonalWinner(Field field) {
        char winner = Field.EMPTY_CHAR;
        for (List<Character> cellsToCheck: field.getDiagonalCells()) {
            if (matchingNonEmptyCells(cellsToCheck)) {
                winner = cellsToCheck.get(0);
                break;
            }
        }
        return winner;
    }

    /**
     * get the index of an empty cell from a list of cells
     * @param cells the list of cells to search in
     * @return the index of the empty cell
     */
    public static int getEmptyCellIndex(List<Character> cells) {
        for (int i = 0; i < cells.size(); i++) {
            if (cells.get(i) == Field.EMPTY_CHAR) {
                return i;
            }
        }
        throw new RuntimeException("No empty cells found!");
    }
}

