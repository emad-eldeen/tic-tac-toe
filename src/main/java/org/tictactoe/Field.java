package org.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Field {
    private final char[][] field;
    final int size;
    public static final char EMPTY_CHAR = ' ';

    enum Diameter {
        Main,
        Secondary
    }

    Field(int size) {
        this.size = size;
        field = new char[size][size];
        initiateField();
    }

    private void initiateField() {
        for (int row = 0; row < size; row++) {
            Arrays.fill(field[row], EMPTY_CHAR);
        }
    }

    // get the field as a list of characters
    public List<Character> getFlat() {
        List<Character> flatField = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int colIndex = 0; colIndex < size; colIndex++) {
                flatField.add(field[rowIndex][colIndex]);
            }
        }
        return flatField;
    }

    /**
     * @return the rows of the field as a list of char list
     */
    public List<List<Character>> getRows() {
        List<List<Character>> rowChars = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            List<Character> row = new ArrayList<>();
            for (int colIndex = 0; colIndex < size; colIndex++) {
                row.add(field[rowIndex][colIndex]);
            }
            rowChars.add(row);
        }
        return rowChars;
    }

    /**
     * @return the columns of the field as a list of char list
     */
    public List<List<Character>> getColumns() {
        List<List<Character>> columnChars = new ArrayList<>();
        for (int colIndex = 0; colIndex < size; colIndex++) {
            List<Character> column = new ArrayList<>();
            for (int rowIndex = 0; rowIndex < size; rowIndex++) {
                column.add(field[rowIndex][colIndex]);
            }
            columnChars.add(column);
        }
        return columnChars;
    }

    /**
     * @return the diagonal cells of the field (main and secondary) as a list of char list
     */
    public List<List<Character>> getDiagonalCells() {
        List<List<Character>> diagonalChars = new ArrayList<>();
        List<Character> diagonal = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            for (int colIndex = 0; colIndex < size; colIndex++) {
                if (rowIndex == colIndex) {
                    diagonal.add(field[rowIndex][colIndex]);
                }
            }
        }
        diagonalChars.add(diagonal);
        diagonal = new ArrayList<>();
        int colIndex =  size - 1;
        for (int rowIndex = 0; rowIndex < size; rowIndex++) {
            diagonal.add(field[rowIndex][colIndex]);
            colIndex --;
        }
        diagonalChars.add(diagonal);
        return diagonalChars;
    }

    public List<Integer[]> getDiagonalCellsCoordinates(Diameter diameter) {
        List<Integer[]> diagonalCoordinates = new ArrayList<>();
        if (diameter == Diameter.Main) {
            for (int rowIndex = 0; rowIndex < size; rowIndex++) {
                for (int colIndex = 0; colIndex < size; colIndex++) {
                    if (colIndex == rowIndex) {
                        Integer[] arr = {rowIndex, colIndex};
                        diagonalCoordinates.add(arr);
                    }
                }
            }
        } else {
            int colIndex =  size - 1;
            for (int rowIndex = 0; rowIndex < size; rowIndex++) {
                Integer[] arr = {rowIndex, colIndex};
                diagonalCoordinates.add(arr);
                colIndex --;
            }
        }
        return diagonalCoordinates;
    }

    public char getCell(int rowIndex, int colIndex) {
        return field[rowIndex][colIndex];
    }

    public void setCell(int rowIndex, int colIndex, char charToSet) {
        field[rowIndex][colIndex] = charToSet;
    }

    /**
     * get the cells that pass a specific condition
     * @param conditionFunction the condition to pass
     * @return the indexes of the cells passing the condition
     */
    private List<Integer[]> getCellsPassingCondition(BiFunction<Integer,
            Integer, Boolean> conditionFunction) {
        List<Integer[]> coordinates = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (conditionFunction.apply(row, col)) {
                    Integer[] arr = new Integer[2];
                    arr[0] = row;
                    arr[1] = col;
                    coordinates.add(arr);
                }
            }
        }
        return coordinates;
    }

    private void printFieldRow(char[] row) {
        String formattedRow = Stream.iterate(0, x -> x + 1)
                .limit(size)
                .map(x -> String.valueOf(row[x]))
                .collect(Collectors.joining(" "));
        System.out.printf("| %s |%n", formattedRow);
    }
    private void printSeparator() {
        System.out.println("-".repeat(9));
    }
    public void print() {
        printSeparator();
        for(char[] row: field) {
            printFieldRow(row);
        }
        printSeparator();
    }

    public List<Integer[]> getEmptyCellsCoordinates() {
        return getCellsPassingCondition((rowIndex, colIndex)
                -> field[rowIndex][colIndex] == EMPTY_CHAR);
    }
}
