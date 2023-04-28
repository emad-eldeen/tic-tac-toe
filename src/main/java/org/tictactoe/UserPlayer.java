package org.tictactoe;

import java.util.Scanner;

public class UserPlayer implements Player {
    private final Scanner scanner = new Scanner(System.in);
    private final char playerChar;

    UserPlayer(char playerChar) {
        this.playerChar = playerChar;
    }
    @Override
    public void makeAMove(Game game) {
        boolean validUserInput = false;
        while (!validUserInput) {
            try {
                System.out.print("Enter next move coordinates:");
                String userInput = scanner.nextLine();
                int[] coordinates = UserInputParser.parseCoordinates(userInput);
                // reaching this line means valid input
                validUserInput = true;
                game.setMove(coordinates[0], coordinates[1], playerChar);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
